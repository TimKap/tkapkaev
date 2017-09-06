package ru.job4j.testtask;

import org.junit.Test;
import ru.job4j.testtask.board.Board;
import ru.job4j.testtask.board.Coordinate;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class PlayerTest содержит тесты для класса Player.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 06.09.2017
 */
public class PlayerTest {

    /**
     * Тест для метода moveTo ти currentCoordinate.
     * */
    @Test
    public void whenMoveToThenGetExpectedCoordinate() {
        Board board = Board.newBoard(3, 0);

        Player player = new Player(board);
        Coordinate coordinate = new Coordinate(0, 0);
        player.initStartPosition(coordinate);
        Coordinate result = player.currentCoordinate();
        assertThat(result, is(coordinate));

        coordinate.setY(1);
        coordinate.setX(1);
        player.moveTo(coordinate);
        result = player.currentCoordinate();
        assertThat(result, is(coordinate));
    }

    /**
     * Тест для moveUp.
     */
    @Test
    public void whenMoveUpThenGetNewPlayerPosition() {
        Board board = Board.newBoard(3, 0);
        Player player = new Player(board);
        Coordinate coordinate = new Coordinate(0, 0);
        player.initStartPosition(coordinate);
        coordinate.setX(0);
        coordinate.setY(1);

        player.moveUp();
        Coordinate result = player.currentCoordinate();
        assertThat(result, is(coordinate));

    }

}
