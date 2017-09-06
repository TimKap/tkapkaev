package ru.job4j.testtask;

import org.junit.Test;
import ru.job4j.testtask.board.Board;
import ru.job4j.testtask.board.Coordinate;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class HeroTest содержит тест для класса Hero.
 *
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 02.09.2017
 */
public class MonsterTest {
    /**
     * Тест для nextCell.
     * @throws InterruptedException - прерывание потока, находящегося в состоянии ожидания
     */
    @Test
    public void whenNextCellThenGetNewCell() throws InterruptedException {
        Board board = new Board(2);
        Monster monster = new Monster(board);

        Coordinate startPosition = monster.autoAction();
        Coordinate nextPosition = monster.autoAction();
        boolean result = (startPosition.getX() != nextPosition.getX()) || (startPosition.getY() != nextPosition.getY());
        assertThat(result, is(true));

    }
}
