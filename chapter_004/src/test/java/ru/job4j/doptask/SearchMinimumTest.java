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
        int[] expected = {1, 2, 3};

        SearchMinimum minimum = new SearchMinimum();
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(6, 3, 4, 2, 5, 1));

        int[] result = minimum.search(list, 3);

        assertThat(result, is(expected));

    }
}
