package ru.job4j.set;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class SimpleLinkedSetTest содержит тесты для SimpleLinkedSet.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 10.07.2017
 */
public class SimpleLenkedSetTest {
    /**
     * Тест для метода add.
     * */
    @Test
    public void whenAddThenGetLinkedSetWithNewElement() {
        String[] expected = {"1", "2", "3"};

        SimpleLinkedSet<String> set = new SimpleLinkedSet<>();
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
