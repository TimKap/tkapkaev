package ru.job4j.iterator;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class MatrixIteratorTest содержит тест для итератора матрицы.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 29.06.2017
 */
public class MatrixIteratorTest {
    /**
     * Тест для итератора матрицы.
     * */
    @Test
    public void whenIterateMAtrixThenGetSequenceOfElements() {
        MatrixIterator iterator = new MatrixIterator(new int[][]{{1, 2}, {3, 4}});

        int[] expected = {1, 2, 3, 4};

        int[] result = new int[4];
        int i = 0;
        while (iterator.hasNext()) {
            result[i++] = (Integer) iterator.next();
        }

        assertThat(result, is(expected));
    }
}
