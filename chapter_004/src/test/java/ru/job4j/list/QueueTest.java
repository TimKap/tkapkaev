package ru.job4j.list;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class QueueTest содержит тесты для Queue.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 10.07.2017
 */
public class QueueTest {

    /**
     * Тест для enqueue.
     * */
    @Test
    public void whenEnqueueThenGetQueueWithAddedElement() {
        int[] expected = {1, 2, 3};

        Queue<Integer> queue = new Queue<>();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        int i = 0;
        int[] result = new int[3];
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            result[i++] = iterator.next();
        }

        assertThat(result, is(expected));
    }

    /**
     * Тест для метода dequeue.
     * */
    @Test
    public void whenDequeueThenGetQueueWithoutOneElement() {
        int[] expected = {2, 3};

        Queue<Integer> queue = new Queue<>();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);


        queue.dequeue();
        int i = 0;
        int[] result = new int[2];
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            result[i++] = iterator.next();
        }

        assertThat(result, is(expected));
    }
}
