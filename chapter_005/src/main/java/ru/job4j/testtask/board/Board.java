package ru.job4j.testtask.board;

import java.util.HashSet;
import java.util.Set;
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

    /** заблокированные клетки .*/
    private  Coordinate[] blockedCells;
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

    /**
     * Генерирует случайную координату.
     * @param size - размер поля
     * @return произвольная координата
     * */
    public static Coordinate randomCoordinate(int size) {
        int x = (int) (Math.random() * (size - 1) + 0.5);
        int y = (int) (Math.random() * (size - 1) + 0.5);
        return new Coordinate(x, y);
    }

    /**
     * Создает новое поле.
     * @param size - размер поля.
     * @param sizeLock - количество заблокированных ячеек.
     * @return поле
     * */
    public static Board newBoard(int size, int sizeLock) {
        Board board = new Board(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board.cells[i][j] = new ReentrantLock();
            }
        }

        /* Задает множество заблокированных точек поля*/
        Set<Coordinate> lockedCells = new HashSet<>();
        while (lockedCells.size() < sizeLock) {
            lockedCells.add(randomCoordinate(size));
        }

        /* создание заблокированных клеток */
        board.blockedCells = new Coordinate[sizeLock];
        int i = 0;
        for (Coordinate coordinate : lockedCells) {
            board.blockedCells[i++] = coordinate;
            board.cells[coordinate.getY()][coordinate.getX()].lock();
        }
        return board;
    }

    /**
     * Возвращает координаты заблокированных ячеек.
     * @return координаты заблокированных ячеек
     * */
    public Coordinate[] getBlockedCells() {
        return blockedCells;
    }
}
