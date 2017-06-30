package ru.job4j.iterator;
import org.junit.Test;
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
        EvenIterator iterator = new EvenIterator(Integer.MAX_VALUE - 3);

        int[] expected = {Integer.MAX_VALUE - 3, Integer.MAX_VALUE - 1};

        int[] result = new int[2];
        int i = 0;
        while (iterator.hasNext()) {
            result[i++] = (Integer) iterator.next();
        }
        assertThat(result, is(expected));
    }
}
