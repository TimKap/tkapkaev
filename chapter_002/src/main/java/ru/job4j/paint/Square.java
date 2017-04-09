package ru.job4j.paint;

/**
 * Class Square описывает изображение квадрата.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 09.04.2017
 */
public class Square implements Shape {
    /**
     * Формирует изображение квадрата.
     * @return изображение квадарата в формате строки
     */
    public String pic() {
        StringBuilder square = new StringBuilder();
        square.append("*****\r\n");
        for (int i = 0; i < 4; i++) {
            square.append("*    *\r\n");
        }
        square.append("*****\r\n");
        return square.toString();
    }
}
