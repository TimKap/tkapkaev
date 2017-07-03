package ru.job4j.iterator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Class PrimeNumbersIteratorTest содержит тесты к PrimeNumberIterator.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 03.07.2017
 */
public class PrimeNumbersIteratorTest {

    /**
     * Тест для итератора простых чисел.
     * */
    @Test
    public void whenIerateNumbersThenGetPrimeNumbers() {
        List<Integer> numbers = new ArrayList<>();
        numbers.addAll(Arrays.asList(2, 4, 11, 21, 135, 137));

        PrimeNumbersIterator iterator = new PrimeNumbersIterator(numbers);
        int[] expected = {2, 11, 137};
        int[] result = new int[3];
        int i = 0;
        while (iterator.hasNext()) {
            result[i++] = (int) iterator.next();
        }
        assertThat(result, is(expected));
    }

}
