package ru.job4j.list;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class SimpleContainerTest содержит тесты к классу SimpleContainer.
 * @author Timur Kapkaev(timur.kap@yandex.ru)
 * @version $Id$
 * @since 09.07.2017
 */
public class SimpleContainerTest {
    /**
     * Тест для метода add.
     * */
    @Test
    public void whenAddElementTOSimpleContainerThenGetContainerWithAddedElement() {
        int[] expected = {1, 2};

        SimpleContainer<Integer> container = new SimpleContainer<>();
        container.add(1);
        container.add(2);

        int[] result = new int[2];
        result[0] = (Integer) container.getContainer()[0];
        result[1] = (Integer) container.getContainer()[1];
        assertThat(result, is(expected));
    }

    /**
     * Тест для метода get.
     * */
    @Test
    public void whenGetThenGetElementFromContainer() {

        SimpleContainer<Integer> container = new SimpleContainer<>();
        container.add(5);
        container.add(6);

        int[] expected = {5, 6};

        int[] result = new int[2];
        result[0] = container.get(0);
        result[1] = container.get(1);

        assertThat(result, is(expected));
    }

    /**
     * Тест для итератора.
     * */
    @Test
    public void whenIteratorThenGetIteratorForContainer() {
        SimpleContainer<Integer> container = new SimpleContainer<>();
        container.add(5);
        container.add(6);

        int[] expected = {5, 6};

        int[] result = new int[2];

        Iterator<Integer> iterator = container.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            result[i++] = iterator.next();
        }

        assertThat(result, is(expected));

    }
}
