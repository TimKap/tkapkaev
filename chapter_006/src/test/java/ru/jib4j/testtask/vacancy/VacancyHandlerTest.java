package ru.jib4j.testtask.vacancy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class VacancyHandlerTest содержит тесты к VacancyHandler.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 20.10.2017
 */
public class VacancyHandlerTest {
    /**
     * Тест для метода filter.
     * */
    @Test
    public void whenFilterVacanciesThenGetValidVacancies() {
        Calendar thresholdDate = Calendar.getInstance();
        thresholdDate.set(2017, 9, 20, 12, 0, 0);
        VacancyHandler handler = new VacancyHandler(thresholdDate.getTime(), "java", "script");


        Calendar validDate = Calendar.getInstance();
        validDate.set(2017, 9, 20, 23, 0, 0);

        List<Vacancy> vacancies = new ArrayList<>();
        vacancies.add(new Vacancy("C++ Developer", "Tom", validDate.getTime()));
        vacancies.add(new Vacancy("Java script", "Ann", validDate.getTime()));
        vacancies.add(new Vacancy("Java developer", "Gek", validDate.getTime()));

        Calendar invalidDate = Calendar.getInstance();
        invalidDate.set(2017, 9, 20, 9, 0, 0);

        vacancies.add(new Vacancy("Java developer", "Ted", invalidDate.getTime()));

        List<Vacancy> expected = new ArrayList<>(Arrays.asList(new Vacancy("Java developer", "Gek", validDate.getTime())));

        List<Vacancy> result = handler.filter(vacancies);

        assertThat(result, is(expected));

    }

    /**
     * Тест для isContinue.
     * */
    @Test
    public void whenVacanciesHasOldVacancyThenStopProcess() {
        Calendar thresholdDate = Calendar.getInstance();
        thresholdDate.set(2017, 9, 20, 12, 0, 0);
        VacancyHandler handler = new VacancyHandler(thresholdDate.getTime(), "java", "script");


        Calendar validDate = Calendar.getInstance();
        validDate.set(2017, 9, 20, 23, 0, 0);

        List<Vacancy> vacancies = new ArrayList<>();
        vacancies.add(new Vacancy("C++ Developer", "Tom", validDate.getTime()));
        vacancies.add(new Vacancy("Java script", "Ann", validDate.getTime()));
        vacancies.add(new Vacancy("Java developer", "Gek", validDate.getTime()));

        Calendar invalidDate = Calendar.getInstance();
        invalidDate.set(2017, 9, 20, 9, 0, 0);

        vacancies.add(new Vacancy("Java developer", "Ted", invalidDate.getTime()));

        boolean result = handler.isContinue(vacancies);

        assertThat(result, is(false));

    }

}
