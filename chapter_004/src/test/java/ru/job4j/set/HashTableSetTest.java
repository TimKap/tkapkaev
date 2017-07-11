package ru.job4j.set;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class HashTableSetTest содержит тесты для HashTableSet.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 11.07.2017
 */
public class HashTableSetTest {
    /**
     * Тест для метода add.
     * */
    @Test
    public void whenAddThenGetSetWithAddedElement() {
        String[] expected = {"1", "2", "3"};

        HashTableSet<String> set = new HashTableSet<>();
        set.add("1");
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("2");
        String[] result = new String[3];
        Iterator<String> iterator = set.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            result[i++] = iterator.next();
        }
        assertThat(result, is(expected));
    }
}
