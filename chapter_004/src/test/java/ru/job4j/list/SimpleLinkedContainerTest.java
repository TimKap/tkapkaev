package ru.job4j.list;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class содержит тесты для SimpleLinkedContainer.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 09.07.2017
 */
public class SimpleLinkedContainerTest {
    /**
     * Тест для add.
     * */
    @Test
    public void whenAddThenGetListWithAddedElement() {

        int[] expected = {3, 6};

        SimpleLinkedContainer<Integer> list = new SimpleLinkedContainer<>();
        list.add(3);
        list.add(6);
        int[] result = {list.getFirst(), list.getLast()};
        assertThat(result, is(expected));
    }

    /**
     * Тест для get.
     * */
    @Test
    public void whenGetThenGetElementByIndex() {

        int[] expected = {3, 6, 9};

        SimpleLinkedContainer<Integer> list = new SimpleLinkedContainer<>();
        list.add(3);
        list.add(6);
        list.add(9);

        int[] result = {list.get(0), list.get(1), list.get(2)};
        assertThat(result, is(expected));

    }
    /**
     * Тест для Iterator.
     * */
    @Test
    public void whenIteratorThenGetIterator() {

        int[] expected = {3, 6, 9};

        SimpleLinkedContainer<Integer> list = new SimpleLinkedContainer<>();
        list.add(3);
        list.add(6);
        list.add(9);

        Iterator<Integer> iterator = list.iterator();
        int[] result = new int[3];
        int i = 0;
        while (iterator.hasNext()) {
            result[i++] = iterator.next();
        }
        assertThat(result, is(expected));

    }
}
