package ru.job4j.database;

import org.junit.Test;
import ru.jib4j.database.Database;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class DatabaseTest содержит тесты для Database.
 *
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 15.09.2017
 */
public class DatabaseTest {

    /**
     * Тест для getNumbersFromDatabase.
     */
    @Test
    public void whenGetNumbersFromDatabaseThenGetNumberArray() {
        int[] expected = {1, 2, 3, 4, 5};

        Database database = new Database();
        database.setDatabase("SQLite");
        database.setUsername("postgres");
        database.setPassword("1");
        database.setRecordsNumber(5);

        int[] result = database.getNumbersFromDatabase();

        assertThat(result, is(expected));
    }

    /**
     * Тест для workWithDataBase.
     * */
    @Test
    public void whenWorkWithDatabaseThenGetNumberArray() {
        int[] expected = {1, 2, 3, 4, 5};

        Database database = new Database();
        database.setDatabase("SQLite");
        database.setUsername("postgres");
        database.setPassword("1");
        database.setRecordsNumber(5);
        int[] result = database.workWithDataBase();
        assertThat(result, is(expected));
    }

    /**
     * demonstration.
     * */

    public void demonstration() {
        long start = System.currentTimeMillis();
        Database database = new Database();
        database.setDatabase("SQLite");
        database.setUsername("postgres");
        database.setPassword("1");
        database.setRecordsNumber(1000000);
        database.workWithDataBase();
        long end = System.currentTimeMillis();
        System.out.println("Time:  " + (end - start));
    }
}
