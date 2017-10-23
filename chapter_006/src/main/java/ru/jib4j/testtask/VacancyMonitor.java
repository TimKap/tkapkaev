package ru.jib4j.testtask;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import ru.jib4j.testtask.database.VacancyDB;
import ru.jib4j.testtask.vacancy.IHandler;
import ru.jib4j.testtask.vacancy.Vacancy;
import ru.jib4j.testtask.vacancy.VacancyHandler;
import ru.jib4j.testtask.webextractor.VacancySQLRU;
import ru.jib4j.testtask.webextractor.WEBExtractor;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;
import java.util.Date;
import java.util.Calendar;


/**
 * Class VacancyCollector описывает сбор Java вакансий с сайта sql.ru  в базу данных.
 * Задача разбита на три подзадачи:
 * 1. сбор информации по вакансиям с веб страницы и преобразование ее в объекты вакансий (VacancySQLRU)
 * 2. фильтрация вакансий и определение условия дальнейшего поиска вакансий (VacancyHandler)
 * 3. помещение вакансий в базу данных (VacancyDB)
 * Внутренний класс UpdateTask объединяет описанные выше подзадачи, обеспечивая собновление базы вакансий.
 * Метод
 * @author  Timur kapkaev (timur,kap@yandex.ru)
 * @version $ID$
 * @since 18.10.2017
 */
public class VacancyMonitor {
    /** собиратель вакансий с веб-сайта. */
    private final WEBExtractor<Vacancy> extractor;
    /** база данных вакансий. */
    private final VacancyDB vacancyDB;
    /** вспомогательный обработчик вакансий. */
    private final IHandler<List<Vacancy>> handler;
    /** дата последнего обновления. */
    private Date lastUpdateDate;
    /** состояние первого запуска.*/
    private boolean firstLoad = true;
    /** период [с] обновленя базы данных.*/
    private long updatePeriod = 60;

    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(VacancyMonitor.class);

    /**
     * Конструктор класса VacancyCollector.
     * @param extractor - собиратель вакансий
     * @param vacancyDB - база данных вакансий
     * @param handler - вспомогательный обработчик вакансий
     * @param lastUpdateDate - дата последнего обновления
     * */
    public VacancyMonitor(WEBExtractor<Vacancy> extractor, IHandler<List<Vacancy>> handler,  VacancyDB vacancyDB, Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
        this.extractor = extractor;
        this.handler = handler;
        this.vacancyDB = vacancyDB;
    }


    /**
     * Создание базового VacancyMonitor поддерживающего файл с настройками.
     * @return объект VacancyMonitor
     * @throws Exception - ошибка создания объекта VacancyMonitor.
     * */
    public static VacancyMonitor defaultVacancyMonitor() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("settings.xml"));
        Element root = doc.getDocumentElement();
        WEBExtractor<Vacancy> extractor = new VacancySQLRU();
        Date lastUpdateDate = new Date();
        IHandler<List<Vacancy>> handler = new VacancyHandler(lastUpdateDate, getTagValue("vacancyTitle", root), getTagValue("forbiddenWord", root));
        VacancyDB vacancyDB = new VacancyDB(getTagValue("url", root), getTagValue("user", root), getTagValue("password", root));

        VacancyMonitor monitor = new VacancyMonitor(extractor, handler, vacancyDB, lastUpdateDate);
        long updatePeriod = Long.valueOf(getTagValue("updatePeriod", root));
        monitor.setUpdatePeriod(updatePeriod);
        return monitor;
    }

    /**
     * Возвращает значение тега с заданным именем.
     * @param name - название тега
     * @param root - родительский тег
     * @return значение тега
     * */
    private static String getTagValue(String name, Element root) {
        return root.getElementsByTagName(name).item(0).getTextContent();
    }

    /**
     * Возвращает период обновления базы данных.
     * @return период[с] обновления базы данных
     * */
    public long getUpdatePeriod() {
        return updatePeriod;
    }

    /**
     * Задает период обновления базы данных.
     * @param updatePeriod - период [с] обновления базы данных
     * */
    public void setUpdatePeriod(long updatePeriod) {
        this.updatePeriod = updatePeriod;
    }


    /**
     * Запуск задачи переодического обновления базы данных с вакансиями..
     * */
    public void periodicVacancyUpdate() {
        TimerTask updateTask = new UpdateTask();
        Timer timer = new Timer(false);
        timer.scheduleAtFixedRate(updateTask, 0, updatePeriod * 1000);
    }


    /**
     * Class UpdateTask описывает задачу обновления базы данных вакансиями загруженных с веб-сайта.
     * @author Timur Kapkaev (timur.kap@yandex.ru)
     * @version $ID$
     * @since 19.10.2017
     * */
    private class UpdateTask extends TimerTask {
        /**
         * Задача обновления базы данных вакансиями, загруженных с веб-сайта.
         * */
        @Override
        public void run() {
            try {
                LOGGER.info("Vacancies start update...");
                if (vacancyDB.tryConnect()) {
                    vacancyDB.createTables("vacancies");
                    Calendar curDate = Calendar.getInstance();
                    /* задается дата, последнего обновления*/
                    if (firstLoad) {
                        int curYear = curDate.get(Calendar.YEAR);
                        Calendar date = Calendar.getInstance();
                        date.set(curYear, 0, 1, 0, 0, 0);
                        lastUpdateDate.setTime(date.getTime().getTime());
                        firstLoad = false;
                    }
                    /* обновление базы данных с вакансиями*/
                    if (updateVacancies()) {
                        lastUpdateDate.setTime(curDate.getTime().getTime());
                        DateFormat df = new SimpleDateFormat("dd.MM.yyyy  'at' HH:mm:ss z");
                        LOGGER.info(String.format("Last update date: %s", df.format(lastUpdateDate)));
                    }

                }
            } catch (SQLException e) {
                LOGGER.error("Update database task problems", e);
            } finally {
                try {
                    vacancyDB.close();
                } catch (SQLException e) {
                    LOGGER.error("Can't close database connection", e);
                }
                LOGGER.info("Finish update");
            }
        }

        /**
         * Обновление базы данных с вакансиями.
         * Проверка обновлений начинается с первой веб-страницы.
         * @return true, при удачном обнговлении базы данных с вакансиями
         * */
        public boolean updateVacancies() {
            boolean isContinue = true;
            boolean tryUpdate = true;
            boolean result = false;
            int page = 0;
            try {
                /* очистить буфер вакансий */
                vacancyDB.clearBufer();
                for (page = 1; isContinue; page++) {
                    tryUpdate = true;
                    for (int attempt = 0; attempt < 3 & tryUpdate; attempt++) {
                        /* выполняется попытка записи вакансий в буфер*/
                        try {
                            /* получить вакансии с веб-страницы*/
                            List<Vacancy> pageVacancies = getVacanciesFromPage(page);
                            /* проверить необходимость обработки следующей веб-страницы*/
                            isContinue = handler.isContinue(pageVacancies);
                            /* отфильтровать ваканси, полученные с  веб-страницы */
                            List<Vacancy> validVacancies = handler.filter(pageVacancies);
                            /* добавить в буфер вакансий */
                            vacancyDB.insertToBufer(validVacancies);
                            tryUpdate = false;
                        } catch (IOException | SQLException e) {
                            LOGGER.error("Can't update database");
                        }
                    }                    /* остановить обновление в случае превышения числа попыток */
                    isContinue &= !tryUpdate;
                }
                /*переместить вакансии из буфера в основную таблицу */
                if (!tryUpdate) {
                    vacancyDB.updateMainTableFromBufer();
                    result = true;
                }
            } catch (SQLException e) {
                LOGGER.error("Can't clear vacancies buffer", e);
            } finally {
                LOGGER.info(String.format("Has been checked %d pages", page > 0 ? page - 1 : 0));
            }
            return result;
        }

        /**
         * Возвращает вакансии с заданной страницы.
         * @param page - номер страницы
         * @return вакансии, поолученные с заданной веб-страницы
         * @throws IOException - ошибка формирования страницы
         * */
        private List<Vacancy> getVacanciesFromPage(int page) throws IOException {
            return extractor.extract(extractor.webPageRepresentation(page));
        }
    }
}
