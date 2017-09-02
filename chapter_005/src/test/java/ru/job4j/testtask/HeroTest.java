package ru.job4j.testtask;
import org.junit.Test;
import ru.job4j.testtask.board.Board;
import ru.job4j.testtask.board.Coordinate;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class HeroTest содержит тест для класса Hero.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 02.09.2017
 */
public class HeroTest {
    /**
     * Тест для nextCell.
     * */
    @Test
    public void whenNextCellThenGetNewCell() {
        Board board = new Board(2);
        Hero hero = new Hero(board);
        try {
            Coordinate startPosition = hero.changePosition();
            Coordinate nextPosition = hero.changePosition();
            boolean result = (startPosition.getX() != nextPosition.getX()) || (startPosition.getY() != nextPosition.getY());
            assertThat(result, is(true));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
