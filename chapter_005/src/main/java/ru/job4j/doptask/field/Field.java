package ru.job4j.doptask.field;

import java.util.HashSet;
import java.util.Set;

/**
 * Class Field описывает поле.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 02.09.2017
 */
public class Field {

    /** Ядро поля. Образовано точками */
    private final Set<Point> fieldsCore = new HashSet<>();
    /** Прилегающий слоя ядра поля. Образуется точками, прилегающими к ядру.*/
    private final Set<Point> adjacentLayer = new HashSet<>();

    /**
     * Конструктор класса Field.
     * @param fieldsCore - точки, образующие ядро поля.
     * */
    public Field(Point[] fieldsCore) {
        initField(fieldsCore);
    }

    /**
     * Конструктор класса Field.
     * */
    public Field() {
    }

    /**
     * Инициализирует поле.
     * @param points - точки поля
     * */
    private void initField(Point[] points) {
        for (Point point : points) {
            joinPoint(point);
        }
    }

    /**
     * Возвращает ядро поля.
     * @return ядро поля.
     * */

    public Set<Point> getFieldsCore() {
        return fieldsCore;
    }
    /**
     * Возвращает прилегающий слой поля.
     * @return прилегающий слой
     * */
    public Set<Point> getAdjacentLayer() {
        return adjacentLayer;
    }

    /**
     * Создает прилегающий слой точки.
     * @param point - точка
     * @return прилегающий слой точки
     * */
    private Set<Point> makePointAdjacentLayer(Point point) {
        Set<Point> adjacentLayer = new HashSet<>();
        adjacentLayer.add(new Point(point.getX() - 1, point.getY()));
        adjacentLayer.add(new Point(point.getX(), point.getY() + 1));
        adjacentLayer.add(new Point(point.getX() + 1, point.getY()));
        adjacentLayer.add(new Point(point.getX(), point.getY() - 1));
        return adjacentLayer;
    }


    /**
     * Присоединяет к полю новую точку.
     * @param point - точка
     * */
    public void joinPoint(Point point) {

        fieldsCore.add(point);
        adjacentLayer.remove(point);

        Set<Point> adjacentPoints = makePointAdjacentLayer(point);
        for (Point adjacentPoint : adjacentPoints) {
            if (!fieldsCore.contains(adjacentPoint)) {
                adjacentLayer.add(adjacentPoint);
            }
        }
    }

    /**
     * Присоединяет новое поле к уже существующему.
     * @param field - присоединяемое поле.
     * */
    public void joinField(Field field) {
        fieldsCore.addAll(field.getFieldsCore());
        adjacentLayer.addAll(field.getAdjacentLayer());
        adjacentLayer.removeAll(fieldsCore);
    }

    /**
     * Проверка соприкосновения заданного поля с текущим.
     * @param field - заданное поле
     * @return true, если поля соприкасаются
     * */
    public boolean isIntersection(Field field) {
        for (Point point : field.getFieldsCore()) {
            if (adjacentLayer.contains(point)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Определяет равенство полей.
     * @param o - поле, с которым выполняется сравнение
     * @return true, если объекты равны
     * */

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Field field = (Field) o;
        return fieldsCore != null ? fieldsCore.equals(field.getFieldsCore()) : field.fieldsCore == null;
    }

    /**
     * Возвращает хеш-функцию поля.
     * @return хеш-функция
     * */
    @Override
    public int hashCode() {
        return fieldsCore != null ? fieldsCore.hashCode() : 0;
    }
}
