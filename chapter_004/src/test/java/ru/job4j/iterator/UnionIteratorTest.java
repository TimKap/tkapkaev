package ru.job4j.iterator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class UnionIteratorTest содержит тесты для UnionIterator.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 03.07.2017
 */
public class UnionIteratorTest {

    /**
     * Тест для метода convert.
     * */
    @Test
    public void whenConvertSetOfIteratorsThenGetOneIteratorToIntegerNumbers() {
        List<Iterator<Integer>> list = new ArrayList<>();
        list.addAll(Arrays.asList(Arrays.asList(1, 2, 3).iterator(), Arrays.asList(4, 5, 6).iterator()));

        int[] expected = {1, 2, 3, 4, 5, 6};

        int[] result = new int[6];
        Iterator<Integer> it = UnionIterator.convert(list.iterator());
        int i = 0;
        while (it.hasNext()) {
            result[i++] = it.next();
        }

        assertThat(result, is(expected));
    }
}
