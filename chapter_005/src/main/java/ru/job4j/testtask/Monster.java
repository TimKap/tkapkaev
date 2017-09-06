package ru.job4j.testtask;

import ru.job4j.testtask.board.Board;
import ru.job4j.testtask.board.Coordinate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class Monster описывает монстра.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 01.09.2017
 */
public class Monster {
    /** Игровое пространство монстра. */
    private final Board board;

    /** Текущее положение монстра. */
    private ReentrantLock currentPosition;

    /**
     * Конструктор класса Monster.
     * @param board - игровое пространство монстра
     *
     * */
    public Monster(Board board) {
        this.board = board;
    }

    /**
     * Изменяет автоматически положение монстра.
     * @throws InterruptedException - прерывание потока в состоянии ожижания
     * @return координаты нового положения монстра
     * */
    public Coordinate autoAction() throws InterruptedException {
        /* 1. монстер пытается занять новую клетку */
        Coordinate nextCoordinate = new Coordinate(0, 0);
        ReentrantLock nextCell = randomCell(currentPosition, nextCoordinate);
        while (!nextCell.tryLock(500, TimeUnit.MILLISECONDS)) {
       //     System.out.println(Thread.currentThread().getName() + "Can't get accesses to cell X " + nextCoordinate.getX() + " Y " + nextCoordinate.getY());
            nextCell = randomCell(currentPosition, nextCoordinate);
        }

        /* 2. монстер занял новую клетку*/
        /* 3. монстер совбождает занимаемую им клетку */
        if (currentPosition != null) {
        //    System.out.printf("Thread: %s освободил предыдущую координату\n\r", Thread.currentThread().getName());
            currentPosition.unlock();
        }

        /* обновили текущее значение ячейки */
        //System.out.printf("Thread: %s занял ячейку X: %d Y: %d\n\r", Thread.currentThread().getName(), nextCoordinate.getX(), nextCoordinate.getY());
        currentPosition = nextCell;
        return nextCoordinate;
    }

    /**
     * Возвращает координату нового положения монстра.
     * @param currentPosition - текущее положение монстра
     * @param nextCoordinate - следующее положение монстра
     * @return новое положение монстра
     * */
    private ReentrantLock randomCell(ReentrantLock currentPosition, Coordinate nextCoordinate) {

        ReentrantLock nextCell;
        int size = board.getSize();
        do {
            nextCoordinate.setX(Board.randomCoordinate(size).getX());
            nextCoordinate.setY(Board.randomCoordinate(size).getY());
            nextCell = board.getCell(nextCoordinate);
        } while (currentPosition != null && nextCell.equals(currentPosition));
        return nextCell;
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
     * Определяет текущую координату имонстра.
     * @return координата монстра.
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

}
