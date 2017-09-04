package ru.job4j.doptask.space;

import ru.job4j.doptask.field.Point;

/**
 * Class Cell описывает ячейки пространства.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 03.09.2017
 */
public class Cell {
    /** Координаты ячейки. */
    private final Point coordinate;

    /** Значение ячейки. */
    private final int value;

    /**
     * Конструктор ячейки.
     * @param coordinate - координата ячейки
     * @param value - значение ячейки.
     * */
    public Cell(Point coordinate, int value) {
        this.coordinate = coordinate;
        this.value = value;
    }

    /**
     * Возвращает координату ячейки.
     * @return координата ячейки
     * */
    public Point getCoordinate() {
        return coordinate;
    }
    /**
     * Возвращает значение ячейки.
     * @return значение ячейки
     * */
    public int getValue() {
        return value;
    }

    /**
     * Строковое представление ячейки.
     * @return  строковое представление ячейки
     * */
    @Override
    public String toString() {
//        return String.format("value: %d   %s", value, coordinate.toString());
        return String.format("%d ", value);
    }

}
