package ru.jib4j.testtask.vacancy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class VacancyHandler - вспомогательный класс для работы с вакансиями.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 16.10.2017
 */
public class VacancyHandler implements IHandler<List<Vacancy>> {

    /** дата последнего обновления. */
    private Date lastUpdateDate;
    /** название вакансии. */
    private final String vacancyTitle;
    /** запретное слово в названии вакансии. */
    private final String forbiddenWord;
    /**
     * Конструктор класса VacancyHandler.
     * @param lastUpdateDate - дата последнего обновления
     * @param vacancyTitle - название вакансии
     * @param forbiddenWord - запртное слово в названии вакансии
     * */
    public VacancyHandler(Date lastUpdateDate, String vacancyTitle, String forbiddenWord) {
        this.lastUpdateDate = lastUpdateDate;
        this.vacancyTitle = vacancyTitle;
        this.forbiddenWord = forbiddenWord;
    }

    /**
     * Фильтрует список вакансий по дате обновления и названию вакансии.
     * @param vacancies - список вакансий.
     * @return отфильтрованный список вакансий
     * */
    @Override
    public List<Vacancy> filter(List<Vacancy> vacancies) {
        ArrayList<Vacancy> validVacancies = new ArrayList<>();
        for (Vacancy vacancy : vacancies) {
            String title = vacancy.getTitle().toLowerCase();
            if (title.contains(vacancyTitle) && !title.contains(forbiddenWord) && vacancy.getDate().after(lastUpdateDate)) {
                validVacancies.add(vacancy);
            }
        }
        return validVacancies;
    }

    /**
     * Определяет необходимость дальнейшей обработки веб-страниц с вакансиями.
     * @param vacancies - вакансии
     * @return true, если дальнейшая обработка данных возможна
     * */
    @Override
    public boolean isContinue(Object vacancies) {
        List<Vacancy> vac = (List<Vacancy>) vacancies;
        return !vac.isEmpty() && !hasOldVacancy(lastUpdateDate, vac);
    }


    /**
     * Проверяетналичия вакансии с датой обновления старше заданной.
     * @param date - заданная дата
     * @param vacancies - список вакансий
     * @return true, если список содержит вакансию с датой обновления старше указанной
     * */
    private boolean hasOldVacancy(Date date, List<Vacancy> vacancies) {
        boolean isContain = false;
        for (Vacancy vacancy : vacancies) {
            if (vacancy.getDate().before(date)) {
                isContain = true;
                break;
            }
        }
        return isContain;
    }


}

