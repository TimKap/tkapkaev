package ru.job4j.doptask.alternativesolution;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class AlternativeMAxFieldTest содержит тесты для MaxField.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 08.09.2017
 */
public class AlternativeNaxFieldTest {
    /**
     * Тест для searchContiguousCells.
     * */
    @Test
    public void whenSearchContiguousCellsThenGetFive() {
        int[][] testValue = new int[][]{
                      /*X:0  X: 1  X:2*/
         /*Y: 0*/        {1, 1, 0},
         /*Y: 1*/        {0, 1, 0},
         /*Y: 2*/        {0, 1, 1}
        };

        MaxField maxField = new MaxField(testValue);
        assertThat(maxField.searchContiguousCells(0, 0), is(5));
        maxField.recover();
        assertThat(maxField.searchContiguousCells(1, 0), is(5));
        maxField.recover();
        assertThat(maxField.searchContiguousCells(1, 1), is(5));
        maxField.recover();
        assertThat(maxField.searchContiguousCells(1, 2), is(5));
        maxField.recover();
        assertThat(maxField.searchContiguousCells(2, 2), is(5));
        maxField.recover();
        assertThat(maxField.searchContiguousCells(0, 1), is(0));
        maxField.recover();




    }

    /**
     * Тест для searchContiguousCells.
     * */
    @Test
    public void whenSearchContiguousCellsThenGetFour() {
        int[][] testValue2 = new int[][]{
                      /*X:0  X: 1  X:2*/
         /*Y: 0*/        {0, 1, 1},
         /*Y: 1*/        {0, 1, 1},
         /*Y: 2*/        {0, 0, 0}
        };

        MaxField maxField = new MaxField(testValue2);
        assertThat(maxField.searchContiguousCells(1, 0), is(4));
        maxField.recover();
    }

    /**
     * Тест для searchContiguousCells.
     * */
    @Test
    public void whenSearchContiguousCellsThenGetZero() {
        int[][] testValue = new int[][]{
                      /*X:0  X: 1  X:2*/
         /*Y: 0*/        {1, 1, 0},
         /*Y: 1*/        {0, 1, 0},
         /*Y: 2*/        {0, 1, 1}
        };
        MaxField maxField = new MaxField(testValue);

        assertThat(maxField.searchContiguousCells(0, 1), is(0));
        maxField.recover();
    }

    /**
     * Тест для searchMaximumField.
     * */
    @Test
    public void whenSearchMaximumFieldThenGetFive() {
        int[][] testValue = new int[][]{
                      /*X:0  X: 1  X:2*/
         /*Y: 0*/        {0, 1, 0},
         /*Y: 1*/        {1, 1, 1},
         /*Y: 2*/        {0, 1, 0}
        };

        MaxField maxField = new MaxField(testValue);
        assertThat(maxField.searchMaximumField(), is(5));
    }
}
