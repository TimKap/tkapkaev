package ru.job4j.testtask.board;

/**
 * Class Coordinate описывает координаты.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 */
public class Coordinate {
    /** Координата Х. */
    private int x;
    /** Координата Y. */
    private int y;

    /**
     * Конструктор класса Coordinate.
     * @param x - координта X
     * @param y - коодината Y
     * */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Возвращает координату X.
     * @return координата X
     * */
    public int getX() {
        return x;
    }

    /**
     * Задает координату X.
     * @param x -координата X
     * */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Возвращает координату Y.
     * @return координата Y
     * */
    public int getY() {
        return y;
    }

    /**
     * Задает координату Y.
     * @param y -координата Y
     * */
    public void setY(int y) {
        this.y = y;
    }
}
