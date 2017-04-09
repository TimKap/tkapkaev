package ru.job4j.paint;


/**
 * Class Paaint описывет изображение фигуры.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 09.04.2017
 */
public class Paint {
    /**
     * Формирование изображения фигуры.
     * @param shape тип фигуры
     * */
    public void draw(Shape shape) {
        System.out.print(shape.pic());
    }
}
