package ru.job4j.doptask;

import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class SearchMinimumTest  содержит тест для SearchMinimum.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 25.07.2017
 */
public class SearchMinimumTest {
    /**
     * Тест для метода search.
     * */
    @Test
    public void whenSearchThenGetMinIndexes() {
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(4, 8, 10));


        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(10, 10, 10,  5, 1, 6, 11, 13, 1, 10, 1, 1));
        SearchMinimum minimumSearch = new SearchMinimum();
        MinContainer result = minimumSearch.search(list, 3);

        assertThat(result.getMin(), is(1));
        assertThat(result.getMinIndexes(), is(expected));
    }
}
