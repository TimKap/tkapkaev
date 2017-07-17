package ru.job4j.tree;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class TreeTest содержит тесты для класса Tree.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 17.07.2017
 */
public class TreeTest {

    /**
    * Тест для add.
    * */
    @Test
    public void whenAddThenBuildTree() {

        int[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8};


        Tree<Integer> tree = new Tree(0);
        int[] result = new int[9];
        /*0-й уровень*/
        result[0] = tree.getRoot().getValue();
        assertThat(result[0], is(expected[0]));

        /*1-й уровень*/
        tree.add(0, 1);
        tree.add(0, 2);

        /*возвращаем значения дочерних узлов узла 0*/
        result[1] = tree.getRoot().getChildren().get(0).getValue();
        result[2] = tree.getRoot().getChildren().get(1).getValue();
        assertThat(result[1], is(expected[1]));
        assertThat(result[2], is(expected[2]));

        /*2-й уровень.*/
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(1, 5);

        tree.add(2, 6);
        tree.add(2, 7);
        tree.add(2, 8);

        /*Возвращаем значения дочерних узлов узла 1*/
        result[3] = tree.getRoot().getChildren().get(0).getChildren().get(0).getValue();
        result[4] = tree.getRoot().getChildren().get(0).getChildren().get(1).getValue();
        result[5] = tree.getRoot().getChildren().get(0).getChildren().get(2).getValue();

        /*Возвращаем значения дочерних узлов узла 2*/
        result[6] = tree.getRoot().getChildren().get(1).getChildren().get(0).getValue();
        result[7] = tree.getRoot().getChildren().get(1).getChildren().get(1).getValue();
        result[8] = tree.getRoot().getChildren().get(1).getChildren().get(2).getValue();

        assertThat(result[3], is(expected[3]));
        assertThat(result[4], is(expected[4]));
        assertThat(result[5], is(expected[5]));
        assertThat(result[6], is(expected[6]));
        assertThat(result[7], is(expected[7]));
        assertThat(result[8], is(expected[8]));
    }

    /**
     * Тест для iterator.
     * */
    @Test
    public void whenIteratorThenGetIterator() {
        int[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8};

        Tree<Integer> tree = new Tree(0);

        /*1-й уровень*/
        tree.add(0, 1);
        tree.add(0, 2);

        /*2-й уровень.*/
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(1, 5);

        tree.add(2, 6);
        tree.add(2, 7);
        tree.add(2, 8);

        Iterator<Integer> iterator = tree.iterator();
        int[] result = new int[9];
        int i = 0;
        while (iterator.hasNext()) {
            result[i++] = iterator.next();
        }

        assertThat(result, is(expected));
    }

}
