package ru.job4j.doptask;

import org.junit.Test;
import ru.job4j.doptask.field.Point;
import ru.job4j.doptask.space.Cell;
import ru.job4j.doptask.space.Space;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class MaxFieldTest содержит тест для MaxField.
 *
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 03.09.2017
 */
public class MaxFieldTest {

    /**
     * Тест для searchMaximumField.
     */

    @Test
    public void whenSearchMaximumThenGetMaximumField() {

        Cell[][] cells = new Cell[][]{
                {new Cell(new Point(0, 0), 0), new Cell(new Point(1, 0), 1), new Cell(new Point(2, 0), 1)},
                {new Cell(new Point(0, 1), 1), new Cell(new Point(1, 1), 1), new Cell(new Point(2, 1), 1)},
                {new Cell(new Point(0, 2), 1), new Cell(new Point(1, 2), 1), new Cell(new Point(2, 2), 0)}
        };
        Space space = new Space(3, 3, cells);

        MaxField maxField = new MaxField();
        assertThat(maxField.searchMaximumField(space), is(7));
    }

    /**
     * Демонстрация работы поиска максимльного множества на поле произвольного разсера.
     */
    @Test
    public void demonstrateSearchMaximumField() {

        Space space = Space.newRandomSpace(4, 5);

        for (int i = space.getSizeY() - 1; i >= 0; i--) {
            for (int j = 0; j < space.getSizeX(); j++) {
                System.out.print(space.getSpace()[i][j].toString() + "    ");
            }
            System.out.println();
        }

        System.out.println(new MaxField().searchMaximumField(space));
    }
}
