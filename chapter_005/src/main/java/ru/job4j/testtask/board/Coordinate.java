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


    /**
     * Определяет равенство коодинат.
     * @param o - координата, с которой выполняется сравнение
     * @return true, если координаты равны
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Coordinate that = (Coordinate) o;

        if (x != that.x) {
            return false;
        }
        return y == that.y;
    }

    /**
     * Задает хеш-функцию координаты.
     * @return хеш-код
     * */
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
