package ru.job4j.testtask.board;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Class Board описывает игровое поле.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 01.09.2017
 */
public class Board {

    /** Массив ячеек игрового поля. */
    private final ReentrantLock[][] cells;

    /** размер игрового поля. */
    private final int size;

    /**
     * Конструктор класса Board.
     * @param size - размер игрового поля.
     * */
    public Board(int size) {
        this.size = size;
        cells = new ReentrantLock[size][size];
    }

    /**
     * Воозвращает ячейку доски.
     * @param coordinate - координата ячейки
     * @return ячейку поля
     * */
    public synchronized ReentrantLock getCell(Coordinate coordinate) {
        ReentrantLock cell = cells[coordinate.getY()][coordinate.getX()];
        if (cell == null) {
            cells[coordinate.getY()][coordinate.getX()] = new ReentrantLock();
            cell = cells[coordinate.getY()][coordinate.getX()];
        }
        return cell;
    }

    /**
     * Возвращает размер игрового поля.
     * @return размер игрового поля
     * */
    public int getSize() {
        return size;
    }

}
