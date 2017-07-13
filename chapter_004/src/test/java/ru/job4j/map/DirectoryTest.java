package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class DirectoryTest содержит тесты для класса Directory.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 13.07.2017
 */
public class DirectoryTest {
    /**
     * Тест для метода insert и get.
     * */
    @Test
    public void whenPutTwoRecordThenGetTwoTelephoneNumbers() {
        int[] expected = {378, 999};

        Directory<String, Integer> directory = new Directory<>(100);
        directory.insert("Tom", 378);
        directory.insert("Jane", 999);

        int[] result = {directory.get("Tom"), directory.get("Jane")};

        assertThat(result, is(expected));
    }
    /**
     * Тест для метода delete.
     * */
    @Test
    public void whenDeleteThenGetDirectoryWithoutOneElement() {
        boolean expected = true;

        Directory<String, Integer> directory = new Directory<>(100);
        directory.insert("Tom", 378);
        directory.insert("Jane", 999);

        boolean result = directory.delete("Tom");

        assertThat(result, is(expected));

        result = directory.delete("Jane");

        assertThat(result, is(expected));

        expected = false;

        result = directory.delete("Tom");

        assertThat(result, is(expected));
    }
    /**
     * Тест для итератора.
     */
    @Test
    public void whenIteratorThenGetIterator() {
        int[] expected = {378, 999};

        Directory<String, Integer> directory = new Directory<>(100);
        directory.insert("Tom", 378);
        directory.insert("Jane", 999);

        Iterator<Integer> iterator = directory.iterator();
        int i = 0;
        int[] result = new int[2];
        while (iterator.hasNext()) {
            result[i++] = iterator.next();
        }
        int tmp;
        if (result[0] > result[1]) {
            tmp = result[0];
            result[0] = result[1];
            result[1] = tmp;
        }

        assertThat(result, is(expected));
    }


}

