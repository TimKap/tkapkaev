package ru.job4j.tree;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class SearchBinaryTreeTest.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 17.07.2017
 */
public class SearchBinaryTreeTest {

    /**
     * Тест дял add.
     * */
    @Test
    public void whenAddThenGetSearchBinaryTreeWithNewNodes() {
        int[] expected = {100, 30, 400, 10, 200, 500};
        /* Бинарное дерево поиска
              100
           /       \
          30       400
         /  \     /   \
       10       200   500
       */
        SearchBinaryTree<Integer> tree = new SearchBinaryTree<>();
        /* 0-й уровень */
        tree.add(100);
        /* 1-й уровень */
        tree.add(400);
        tree.add(30);
        /* 2- й уровень */
        tree.add(200);
        tree.add(10);
        tree.add(500);

        int[] result = new int[4];

        /* 0-й уровень */
        result[0] = tree.getRoot().getKey();
        assertThat(result[0], is(expected[0]));

        /* 1-й уровень */
        result[0] = tree.getRoot().getLeft().getKey();
        result[1] = tree.getRoot().getRight().getKey();
        assertThat(result[0], is(expected[1]));
        assertThat(result[1], is(expected[2]));

        /* 2-й уровень */
        result[0] = tree.getRoot().getLeft().getLeft().getKey();
        result[1] = tree.getRoot().getRight().getLeft().getKey();
        result[2] = tree.getRoot().getRight().getRight().getKey();
        assertThat(result[0], is(expected[3]));
        assertThat(result[1], is(expected[4]));
        assertThat(result[2], is(expected[5]));
    }

    /**
     * Тест для iterator.
     * */
    @Test
    public void whenIteratorThenGetIterator() {
        int[] expected = {100, 30, 400, 10, 200, 500};

        SearchBinaryTree<Integer> tree = new SearchBinaryTree<>();
        /* 0-й уровень */
        tree.add(100);
        /* 1-й уровень */
        tree.add(400);
        tree.add(30);
        /* 2- й уровень */
        tree.add(200);
        tree.add(10);
        tree.add(500);

        int[] result = new int[6];
        int i = 0;
        Iterator<Integer> iterator = tree.iterator();

        while (iterator.hasNext()) {
            result[i++] = iterator.next();
        }
        assertThat(result, is(expected));

    }
}
