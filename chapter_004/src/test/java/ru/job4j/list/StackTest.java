package ru.job4j.list;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class StackTest оодержит тесты для класса Stack.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 10.07.2017
 */
public class StackTest {
    /**Тест для метода push.*/
    @Test
    public void whenPushThenGetStackWithAddedElement() {
        int[] expected = {1, 2, 3};

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        Iterator<Integer> iterator = stack.iterator();
        int i = 0;

        int[] result = new int[3];
        while (iterator.hasNext()) {
            result[i++] = iterator.next();
        }

        assertThat(result, is(expected));
    }

    /** Тест для pop. */
    @Test
    public void whenPopThenGetElementFromTheStack() {
        int[] expected = {1, 2};

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.pop();

        Iterator<Integer> iterator = stack.iterator();
        int i = 0;

        int[] result = new int[2];
        while (iterator.hasNext()) {
            result[i++] = iterator.next();
        }

        assertThat(result, is(expected));

    }

}
