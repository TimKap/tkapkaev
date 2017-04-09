package ru.job4j.paint;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Class PaintTest содержит тесты для класса Paint.
 * @author Timur KApkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 09.04.2017
 */
public class PaintTest {
    /** Тест для формирования изображения триугольника.*/
    @Test
    public void whenDrawTriangleThenGetPictureTriangle() {
        Paint paint = new Paint();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        /*Перехват потока вывода на консоль*/
        System.setOut(new PrintStream(out));
        /* Формирование изображения триугольника*/
        paint.draw(new Triangle());
        String result = out.toString();
        String expected = new StringBuilder().append("  *  \r\n").append(" *** \r\n").append("*****\r\n").toString();
        assertThat(result, is(expected));
    }
    /** Тест для формировнаия изображения квадрата.*/
    @Test
    public void  whenDrawSquareThenGetPictureSquare() {
        Paint paint = new Paint();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        /* Перехват потока вывода на консоль */
        System.setOut(new PrintStream(out));
        /* Формирование изображения квадрата */
        paint.draw(new Square());
        String result = out.toString();
        String expected = new StringBuffer().append("*****\r\n").append("*    *\r\n").append("*    *\r\n").append("*    *\r\n").append("*    *\r\n").append("*****\r\n").toString();
        assertThat(result, is(expected));
    }
}
