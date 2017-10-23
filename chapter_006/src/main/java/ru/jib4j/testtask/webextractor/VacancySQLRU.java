package ru.jib4j.testtask.webextractor;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.jib4j.testtask.vacancy.Vacancy;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class VacancySQLRU возвращает вакансии с веб-майта sql.ru.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 15.10.2017
 */
public class VacancySQLRU extends WEBExtractor<Vacancy> {
    /** шаблон даты.*/
    private static final String DATE = "((\\d+)\\s+([а-яА-Я]{3})\\s+(\\d+)|сегодня|вчера),\\s+";
    /** шаблон времени.*/
    private static final String TIME = "((\\d+):(\\d+))";
    /** массив констант (месяцев). */
    private static final String[] MONTHS = new String[]{"янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сен", "окт", "ноя", "дек" };
    /**
     * Конструктор класса VacancyExtractor.
     * */
    public VacancySQLRU() {
        super("http://www.sql.ru/forum/job-offers/%d");
    }

    /**
     * Возвращает вакансии со страницы.
     * @param webPageRepresentation - представление веб-страницы
     * @return список данных извлеченных с веб-страницы
     * */
    @Override
    public List<Vacancy> extract(Document webPageRepresentation) {
        ArrayList<Vacancy> vacancies = new ArrayList<>();
        Element table = webPageRepresentation.select(".forumTable").first();
        Date curDate = new Date();
        if (table != null) {
            Elements records = table.select("tr");
            if (records != null) {
                for (Element record : records) {
                    Vacancy vacancy = createVacancy(record, curDate);
                    if (vacancy != null) {
                        vacancies.add(vacancy);
                    }
                }
            }
        }
        return vacancies;
    }

    /**
     * Возвращает вакансию из записи web страницы.
     * @param record - запись с web страницы
     * @param curDate - текущее дата и время на момент формирования списка вакансий
     * @return вакансия (null, если вакансию не созжана)
     * */
    private Vacancy createVacancy(Element record, Date curDate) {
        Element title = record.select("td.postslisttopic").select("a").first();
        if (title != null &&  !record.select("td.postslisttopic").first().ownText().contains("Важно:")) {
            Element author = record.select("td.altCol").select("a").first();
            Element date = record.select("td[style][class=altCol]").first();
            return new Vacancy(title.ownText(), author.ownText(), toDate(date.ownText(), curDate));
        } else {
            return null;
        }
    }


    /**
     * Конвертирует строку заданного формата в дату.
     * @param  strDate - дата в формате строки
     * @param curDate - текущая дата
     * @return  дата
     * */
   private Date toDate(String strDate, Date curDate) {

        Pattern pattern = Pattern.compile(DATE + TIME);
        Matcher matcher = pattern.matcher(strDate);


       Calendar calendar = new GregorianCalendar();
        while (matcher.find()) {

            calendar.setTime(curDate);

            int hour = Integer.valueOf(matcher.group(6));
            int minutes = Integer.valueOf(matcher.group(7));

            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minutes);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);


            if (!matcher.group(1).equals("вчера") && !matcher.group(1).equals("сегодня")) {
                int day = Integer.valueOf(matcher.group(2));
                calendar.set(Calendar.DATE, day);
                String month = matcher.group(3);
                int i;
                for (i = 0; i < 12; i++) {
                    if (month.equals(MONTHS[i])) {
                        break;
                    }
                }
                calendar.set(Calendar.MONTH, i);
                int year  = Integer.valueOf(matcher.group(4)) + 2000;
                calendar.set(Calendar.YEAR, year);
            } else {
                if (matcher.group(1).equals("вчера")) {
                    calendar.add(Calendar.DATE, -1);
                }
            }

        }
        return calendar.getTime();
    }

}
