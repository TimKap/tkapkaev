package ru.job4j.list;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class LinkedListCyclicityTest содержит тесты для класса LinkedListCyclicity.
 */
public class LinkedListCyclicityTest {
    /**
     * Тест для hasCycle.
     * */
    @Test
    public void whenHasCycleThenLinkedListCheckedOnCycle() {
        boolean expected = true;

        LinkedListCyclicity<Integer> list = new LinkedListCyclicity<>();
        Node<Integer> first, second, third, fourth;
        first = new Node<>(1);
        second = new Node<>(2);
        third = new Node<>(3);
        fourth = new Node<>(4);

        list.addNode(first);
        list.addNode(second);
        list.addNode(third);
        list.addNode(fourth);
        list.addNode(first);

        boolean result = list.hasCycle();

        assertThat(result, is(expected));

    }
}
