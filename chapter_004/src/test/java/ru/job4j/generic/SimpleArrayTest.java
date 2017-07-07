package ru.job4j.generic;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class SimpleArrayTest содержит тесты для SimpleArray.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 07.07.2017
 */
public class SimpleArrayTest {

    /**
     * Тест для метода add.
     * */
    @Test
    public void whenAddElementThenGetModifiedList() {

        String[] expected = {"1", "2", "3"};

        SimpleArray<String> list = new SimpleArray<>(3);
        list.add("1");
        list.add("2");
        list.add("3");

        String[] result =  {(String) list.getObjects()[0], (String) list.getObjects()[1], (String) list.getObjects()[2]};

        assertThat(result, is(expected));
    }

    /**
     * Тест для метода get.
     * */
    @Test
    public void whenGetThenReturnElementFromTheList() {
        String expected = "1";

        SimpleArray<String> list = new SimpleArray<>(3);
        list.add("1");

        String result = list.get(0);

        assertThat(result, is(expected));
    }

    /**
     * Тест для метода update.
     * */
    @Test
    public void whenUpdateThenGetModifiedList() {
        String expected = "3";

        SimpleArray<String> list = new SimpleArray<>(3);
        list.add("1");

        list.update(0, "3");

        String result = list.get(0);

        assertThat(result, is(expected));
    }
    /**
     * Тест для метода delete.
     * */
    @Test
    public void whenDeleteElementThenGetModifiedList() {
        String[] expected = {"1", "3", null};

        SimpleArray<String> list = new SimpleArray<>(3);
        list.add("1");
        list.add("2");
        list.add("3");

        list.delete(1);

        String[] result = {(String) list.getObjects()[0], (String) list.getObjects()[1], (String) list.getObjects()[2]};

        assertThat(result, is(expected));

    }

}
