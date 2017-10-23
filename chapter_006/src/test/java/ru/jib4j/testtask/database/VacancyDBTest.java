package ru.jib4j.testtask.database;

import org.junit.Test;
import ru.jib4j.testtask.vacancy.Vacancy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.exparity.hamcrest.date.DateMatchers.within;

/**
 * Class VacancyDBTest содержит тесты к VacancyDB.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 20.10.2017
 */
public class VacancyDBTest {
    /** имя тестовой таблицы с вакансиями. */
    private static final String VACANCY_TABLE = "vacancies_test";
    /** url базы данных. */
    private static final String URL = "jdbc:postgresql://localhost:5432/work_test";
    /** имя пользователя. */
    private static final String USER = "postgres";
    /** пароль. */
    private static final String PASSWORD = "1";


    /**
     * Тест для метода insertToBufer.
     * @throws SQLException -
     * */
    @Test
    public void whenInsertToBufferThenGetVacanciesFromIt() throws SQLException {
        VacancyDB vacancyDB = null;
        try {
            vacancyDB = new VacancyDB(URL, USER, PASSWORD);
            vacancyDB.tryConnect();
            vacancyDB.createTables(VACANCY_TABLE);
            vacancyDB.clearBufer();
            Calendar calendar = Calendar.getInstance();
            calendar.set(2017, 9, 20, 12, 0, 0);
            ArrayList<Vacancy> expected = new ArrayList<>(Arrays.asList(new Vacancy("1", "Tom", calendar.getTime()), new Vacancy("2", "Ann", calendar.getTime())));

            vacancyDB.insertToBufer(expected);

            List<Vacancy> result = vacancyDB.vacanciesFromBuferTable();
            assertThat(result, is(expected));
            assertThat(result.get(0).getDate(), within(500, TimeUnit.MILLISECONDS, expected.get(0).getDate()));
            assertThat(result.get(1).getDate(), within(500, TimeUnit.MILLISECONDS, expected.get(1).getDate()));
        } finally {
            vacancyDB.close();
        }
    }

        /**
         * Тест для метода updateMainTableFromBufer.
         * @throws SQLException -
         * */
        @Test
        public void whenUpdateMainTableFromBufferThenGetRecordsInMainTable() throws SQLException {
            VacancyDB vacancyDB = null;
            try {
                vacancyDB = new VacancyDB(URL, USER, PASSWORD);
                vacancyDB.tryConnect();
                vacancyDB.createTables(VACANCY_TABLE);
                vacancyDB.clearBufer();
                Calendar calendar = Calendar.getInstance();
                calendar.set(2017, 9, 20, 12, 0, 0);
                ArrayList<Vacancy> expected = new ArrayList<>(Arrays.asList(new Vacancy("1", "Tom", calendar.getTime()), new Vacancy("2", "Ann", calendar.getTime())));
                vacancyDB.insertToBufer(expected);

                vacancyDB.clearMain();
                vacancyDB.updateMainTableFromBufer();

                List<Vacancy> result = vacancyDB.vacanciesFromMainTable();
                assertThat(result, is(expected));
                assertThat(result.get(0).getDate(), within(500, TimeUnit.MILLISECONDS, expected.get(0).getDate()));
                assertThat(result.get(1).getDate(), within(500, TimeUnit.MILLISECONDS, expected.get(1).getDate()));
            } finally {
                vacancyDB.close();
            }
    }
}
