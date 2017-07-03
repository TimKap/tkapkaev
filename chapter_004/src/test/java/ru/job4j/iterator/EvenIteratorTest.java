package ru.job4j.iterator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Class EvenIteratorTest содержит тесты к EvenIterator.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 30.06.2017
 */
public class EvenIteratorTest {

    /**
     * Тест для итература четных чисел.
     */
    @Test
    public void whenIterateNumberThenGetEvenNumber() {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.addAll(Arrays.asList(1, 2, 3, 5, 2, 6));

        EvenIterator iterator = new EvenIterator(numbers);

        int[] expected = {2, 2, 6};

        int[] result = new int[3];
        int i = 0;
        while (iterator.hasNext()) {
            result[i++] = (Integer) iterator.next();
        }
        assertThat(result, is(expected));
    }
}
