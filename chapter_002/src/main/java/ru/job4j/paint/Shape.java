package ru.job4j.paint;

/**
 * Interface Shape описывает изображение фигуры.
 * @author Timur Kapkaev (timur.kap@ayndex.ru)
 * @version $Id$
 * @since 09.04.2017
 */
public interface Shape {
    /**
     * Формирование изображение фигуры.
     * @return изображение фигуры в формате строки
     * */
    String pic();
}
