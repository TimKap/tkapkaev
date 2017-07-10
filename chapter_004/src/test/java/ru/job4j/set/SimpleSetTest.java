package ru.job4j.set;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class содержит тесты для SimpleSet.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 10.07.2017
 */
public class SimpleSetTest {

    /**
     * Тест для метода add.
     * */
    @Test
    public void whenAddThenGetSetWithNewElement() {
        String[] expected = {"1", "2", "3"};

        SimpleSet<String> set = new SimpleSet<>();
        set.add("1");
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("2");
        String[] result = new String[3];
        int i = 0;
        for (Object element : set.getContainer()) {
            result[i++] = (String) element;
        }

        assertThat(result, is(expected));
    }

    /**
     * Тест для итератора.
     * */
    @Test
    public void whenIteratorThenGetIteratorForSet() {
        String[] expected = {"1", "2", "3"};

        SimpleSet<String> set = new SimpleSet<>();
        set.add("1");
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("2");
        String[] result = new String[3];
        int i = 0;

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            result[i++] = iterator.next();
        }

        assertThat(result, is(expected));

    }
}
