package ru.job4j.doptask;

import org.junit.Test;
import ru.job4j.doptask.field.Point;
import ru.job4j.doptask.space.Cell;
import ru.job4j.doptask.space.Space;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class SpaceTest содержит тесты для класса Space.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 03.09.2017
 */
public class SpaceTest {

    /**
     * Демонстрация работы конструктора с заполнением пространства.
     * */
    @Test
    public void demonstrateSpaceConstructorWithFillingSpace() {
        Space space = Space.newRandomSpace(3, 3);

        Cell[][] spaceSells = space.getSpace();
        for (int i = space.getSizeY() - 1; i >= 0; i--) {
            for (int j = 0; j < space.getSizeX(); j++) {
                System.out.print(spaceSells[i][j].toString() + "     ");
            }
            System.out.println();
        }
    }

    /**
     * Тест для getSubSpace.
     * */
    @Test
    public void whenGetSubSpaceThenGetSubspaceOfCurrentSpace() {

        Space space = Space.newRandomSpace(3, 3);
        Cell[][] expectedCells = space.getSpace();
        Space subspace1 = space.getSubSpace(new Point(0, 0), new Point(1, 1));
        Space subspace2 = space.getSubSpace(new Point(0, 2), new Point(1, 2));
        Space subspace3 = space.getSubSpace(new Point(2, 2), new Point(2, 2));
        Space subspace4 = space.getSubSpace(new Point(2, 0), new Point(2, 1));

        assertThat(subspace1.getSpace()[0][0], is(expectedCells[0][0]));
        assertThat(subspace1.getSpace()[0][1], is(expectedCells[0][1]));
        assertThat(subspace1.getSpace()[1][0], is(expectedCells[1][0]));
        assertThat(subspace1.getSpace()[1][1], is(expectedCells[1][1]));

        assertThat(subspace2.getSpace()[0][0], is(expectedCells[2][0]));
        assertThat(subspace2.getSpace()[0][1], is(expectedCells[2][1]));

        assertThat(subspace3.getSpace()[0][0], is(expectedCells[2][2]));

        assertThat(subspace4.getSpace()[0][0], is(expectedCells[0][2]));
        assertThat(subspace4.getSpace()[1][0], is(expectedCells[1][2]));
    }
}
