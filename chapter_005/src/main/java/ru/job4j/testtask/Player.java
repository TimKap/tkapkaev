package ru.job4j.testtask;

import ru.job4j.testtask.board.Board;
import ru.job4j.testtask.board.Coordinate;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Class Player Рписывает игрока.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 05.09.2017
 */
public class Player {
    /** Игровое пространство игрока. */
    private final Board board;

    /** Текущее положение игрока. */
    private ReentrantLock currentPosition;

    /**
     * Конструктор класса Player.
     * @param board - игровое пространство игрока
     *
     * */
    public Player(Board board) {
        this.board = board;
    }

    /**
     * Задает начальное положение монстра.
     * */
    public final void initStartPosition() {
        int size = board.getSize();
        ReentrantLock position = board.getCell(Board.randomCoordinate(size));
        while (!position.tryLock()) {
            position = board.getCell(Board.randomCoordinate(size));
        }
        currentPosition = position;
    }

    /**
     * Задает указанное начальное положение.
     * @param coordinate - начальное положение
     * */
    final void initStartPosition(Coordinate coordinate) {
        currentPosition = board.getCell(coordinate);
        currentPosition.lock();
    }

    /**
     * Определяет текущую координату игрока.
     * @return координата игрока.
     * */

    public Coordinate currentCoordinate() {
        int size = board.getSize();
        Coordinate currentCoordinate = null;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                currentCoordinate = new Coordinate(j, i);
                if (currentPosition.equals(board.getCell(currentCoordinate))) {
                    return currentCoordinate;
                }
            }
        }
        return currentCoordinate;
    }

    /**
     * Проверка корректности координаты.
     * @param coordinate - координата
     * @return true, если координата корректна
     * */
    private boolean validateCoordinate(Coordinate coordinate) {
        int size = board.getSize();
        if (coordinate.getX() >= size || coordinate.getX() < 0) {
            return false;
        }

        return (coordinate.getY() < size && coordinate.getY() >= 0);
    }

    /**
     * Перемещает игрока в заданную координату игрового поля.
     * (в случае успешного перемещения, положение, которое занимал игрок освобождается
     * @param coordinate - заданная координата
     * @return true, если перемещене остоялось
     * */
     boolean moveTo(Coordinate coordinate) {
        if (!validateCoordinate(coordinate)) {
            return false;
        }
        Coordinate currentCoordinate = currentCoordinate();
        if (currentCoordinate.equals(coordinate)) {
            return false;
        }
        ReentrantLock cell = board.getCell(coordinate);
        if (cell.tryLock()) {
            /* Освободили предыдудщее положение*/
            //System.out.println("Osvobodil");
            currentPosition.unlock();
            /* Заняли новое положение*/
            currentPosition = cell;
            //System.out.printf("Thread: %s занял ячейку X: %d Y: %d\n\r", Thread.currentThread().getName(), currentCoordinate().getX(), currentCoordinate().getY());
            return true;
        }
        return false;
    }

    /**
     * Переместить персонажа на один шаг вверх.
     * @return true, если перемещение выполнено
     * */
    public boolean moveUp() {
        Coordinate coordinate = currentCoordinate();
        coordinate.setY(coordinate.getY() + 1);
        return moveTo(coordinate);
    }

    /**
     * Переместить персонажа на один шаг вниз.
     * @return true, если перемещение выполнено
     * */
    public boolean moveDown() {
        Coordinate coordinate = currentCoordinate();
        coordinate.setY(coordinate.getY() - 1);
        return moveTo(coordinate);
    }

    /**
     * Переместить персонажа на один шаг влево.
     * @return true, если перемещение выполнено
     * */
    public boolean moveLeft() {
        Coordinate coordinate = currentCoordinate();
        coordinate.setX(coordinate.getX() - 1);
        return moveTo(coordinate);
    }

    /**
     * Переместить персонажа на один шаг вправо.
     * @return true, если перемещение выполнено
     * */
    public boolean moveRight() {
        Coordinate coordinate = currentCoordinate();
        coordinate.setX(coordinate.getX() + 1);
        return moveTo(coordinate);
    }
}
