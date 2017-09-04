package ru.job4j.doptask.field;

/**
 * Class Point описывает точку в двумерном пространстве.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 02.09.2017
 */
public class Point {
    /** Координата X. */
    private final int x;
    /** Координата Y. */
    private final int y;
    /**
     * Конструктор класса Point.
     * @param x - координата X
     * @param y - координата Y
     * */
    public Point(int x, int y) {
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
     * Возвращает координату Y.
     * @return координата Y
     * */
    public int getY() {
        return y;
    }

    /**
     * Проверка равенства точек.
     * @param o - точка, с котрой выполняется сравнение
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Point point = (Point) o;

        if (x != point.x) {
            return false;
        }
        return y == point.y;
    }

    /**
     * Хеш-функция точки.
     * */
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    /**
     * Представление точки в виде строки.
     * @return строковое представление точки
     * */
    @Override
    public String toString() {
        return String.format("Point: X %s, Y: %s", x, y);
    }
}
