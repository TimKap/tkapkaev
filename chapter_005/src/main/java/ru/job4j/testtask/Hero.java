package ru.job4j.testtask;

import ru.job4j.testtask.board.Board;
import ru.job4j.testtask.board.Coordinate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class Hero описывает героя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 01.09.2017
 */
public class Hero {
    /** Игровое пространство героя. */
    private final Board board;

    /** Текущее положение героя. */
    private ReentrantLock currentPosition;

    /**
     * Конструктор класса Hero.
     * @param board - игровое пространство игрока
     * */
    public Hero(Board board) {
        this.board = board;
    }

    /**
     * Изменяет положение персонажа.
     * @throws InterruptedException - прерывание потока в состоянии ожижания
     * @return координаты нового положения героя
     * */
    public Coordinate changePosition() throws InterruptedException {

        /* 1. геройо совбождает занимаемую им клетку */
        if (currentPosition != null) {
              //System.out.printf("Thread: %s освободил предыдущую координату\n\r", Thread.currentThread().getName());
            currentPosition.unlock();
        }

        /* 2. герой пытается занять новую клетку */
        Coordinate nextCoordinate = new Coordinate(0, 0);
        ReentrantLock nextCell = nextCell(currentPosition, nextCoordinate);
        while (!nextCell.tryLock(500, TimeUnit.MILLISECONDS)) {
            //System.out.println(Thread.currentThread().getName() + "Can't get accesses to cell X " + nextCoordinate.getX() + " Y " + nextCoordinate.getY());
            nextCell = nextCell(currentPosition, nextCoordinate);
        }

        /* 3. герой занял новую клетку*/
        //System.out.printf("Thread: %s занял ячейку X: %d Y: %d\n\r", Thread.currentThread().getName(), nextCoordinate.getX(), nextCoordinate.getY());
        currentPosition = nextCell;
        return nextCoordinate;
    }

    /**
     * Возвращает координату нового положения игрока.
     * @param currentPosition - текущее положение героя
     * @param nextCoordinate - следующее положение героя
     * @return новое положение героя
     * */
    private ReentrantLock nextCell(ReentrantLock currentPosition, Coordinate nextCoordinate) {

        ReentrantLock nextCell;
        do {
            int x = (int) (Math.random() * board.getSize());
            int y = (int) (Math.random() * board.getSize());
            nextCoordinate.setX(x);
            nextCoordinate.setY(y);
            nextCell = board.getCell(nextCoordinate);
        } while (currentPosition != null && nextCell.equals(currentPosition));
        return nextCell;
    }
}
