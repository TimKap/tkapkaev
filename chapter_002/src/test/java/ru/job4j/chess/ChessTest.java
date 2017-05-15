package ru.job4j.chess;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImposibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.figures.Bishop;
import ru.job4j.chess.figures.Figure;
/**
 * Class ChessTest содержит тесты для шахмат.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 15.05.2017
 */
public class ChessTest {

    /**
     * Тест для хода слона.
     * */
    @Test
    public void whenMakeWayBishopThenGetDiagonalTrajectory() {
        Bishop bishop = new Bishop(new Cell(2, 2));
        Cell[] resultTrajectory;
        Cell[] expectedTrajectory = new Cell[] {new Cell(3, 1), new Cell(4, 0)};

        try {
            resultTrajectory = bishop.way(new Cell(4, 0));
        } catch (ImposibleMoveException exception) {
            resultTrajectory = null;
        }

        assertThat(resultTrajectory[0].getX(), is(expectedTrajectory[0].getX()));
        assertThat(resultTrajectory[0].getY(), is(expectedTrajectory[0].getY()));
        assertThat(resultTrajectory[1].getX(), is(expectedTrajectory[1].getX()));
        assertThat(resultTrajectory[1].getY(), is(expectedTrajectory[1].getY()));
    }

    /**
     * Тест для хода фигурой по шахмаьной доски с перемещением фигуры.
     * */
    @Test
    public void whenMakeMoveFigureWithoutProblemsThengetNewPosition() {
        Figure[] figures = new Figure[] {new Bishop(new Cell(1, 1)), new Bishop(new Cell(3, 1))};
        Board board = new Board(figures);

        Cell expectedCell = new Cell(4, 4);

        Cell moveFrom = new Cell(1, 1);

        try {
            board.move(moveFrom, expectedCell);
        } catch (FigureNotFoundException exception) {

        } catch (ImposibleMoveException exception) {

        } catch (OccupiedWayException exception) {

        }
        Figure figure = board.getFigures()[0];

        assertThat(figure.checkCell(expectedCell), is(figure));
    }

    /**
     * Тест для хода фигурой по шахмаьной доски с ошибкой.
     * Ход осуществляется из ячейки, незанятой фигурой.
     * */
    @Test
    public void whenMakeMoveFromEmptyCellThenGetException() {
        Figure[] figures = new Figure[] {new Bishop(new Cell(1, 1)), new Bishop(new Cell(3, 1))};
        Board board = new Board(figures);

        Cell expectedCell = new Cell(4, 4);
        Cell moveFrom = new Cell(0, 0);

        FigureNotFoundException expectedException = new FigureNotFoundException();
        Exception result = null;

        try {
            board.move(moveFrom, expectedCell);
        } catch (FigureNotFoundException exception) {
            result = expectedException;
        } catch (ImposibleMoveException exception) {

        } catch (OccupiedWayException exception) {

        }

        assertThat(result, is(expectedException));
    }

    /**
     * Тест для хода фигурой по шахмаьной доски с ошибкой.
     * Выполняется недопустимое перемещение фигуры.
     * */
    @Test
    public void whenMakeMoveFigureThenGetException() {
        Figure[] figures = new Figure[] {new Bishop(new Cell(1, 1)), new Bishop(new Cell(3, 1))};
        Board board = new Board(figures);

        Cell expectedCell = new Cell(1, 4);
        Cell moveFrom = new Cell(1, 1);

        ImposibleMoveException expectedException = new ImposibleMoveException();
        Exception result = null;

        try {
            board.move(moveFrom, expectedCell);
        } catch (FigureNotFoundException exception) {

        } catch (ImposibleMoveException exception) {
            result = expectedException;

        } catch (OccupiedWayException exception) {

        }

        assertThat(result, is(expectedException));
    }

    /**
     * Тест для хода фигурой по шахмаьной доски с ошибкой.
     * Выполняется перемещение фигуры в клетку с занятой другой фигурой.
     * */
    @Test
    public void whenMakeMoveFigureToCellWithOtherFigureThenGetException() {
        Figure[] figures = new Figure[] {new Bishop(new Cell(1, 1)), new Bishop(new Cell(4, 4))};
        Board board = new Board(figures);

        Cell expectedCell = new Cell(4, 4);
        Cell moveFrom = new Cell(1, 1);

        OccupiedWayException expectedException = new OccupiedWayException();
        Exception result = null;

        try {
            board.move(moveFrom, expectedCell);
        } catch (FigureNotFoundException exception) {

        } catch (ImposibleMoveException exception) {

        } catch (OccupiedWayException exception) {
            result = expectedException;
        }

        assertThat(result, is(expectedException));
    }

}
