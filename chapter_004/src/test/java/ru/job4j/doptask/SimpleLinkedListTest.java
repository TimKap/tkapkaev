package ru.job4j.doptask;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class содержиттесты для SimpleLinkedList.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 25.07.2017
 */
public class SimpleLinkedListTest {
    /**
     * Тест для getK.
     * */
    @Test
    public void whenGetKThenReturnKValueFromTheEnd() {
        int expected = 5;

        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);

        int result = list.getK(4);

        assertThat(result, is(expected));
    }
}
