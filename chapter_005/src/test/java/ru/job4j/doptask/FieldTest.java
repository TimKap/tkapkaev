package ru.job4j.doptask;


import org.junit.Test;
import ru.job4j.doptask.field.Field;
import ru.job4j.doptask.field.Point;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import  static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class FieldTest содержит тест к методам класса Field.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 03.09.2017
 */
public class FieldTest {

    /**
     * Тест для конструктора Field.
     * */
    @Test
    public void whenFieldThenGetCorrectObject() {

        Point[] points = new Point[] {new Point(0, 0), new Point(1, 0)};

        Set<Point> expectedFieldsCore = new HashSet<>(Arrays.asList(points));
        Set<Point> expectedAdjacentLayer = new HashSet<>(Arrays.asList(new Point(-1, 0), new Point(0, 1),
                new Point(1, 1), new Point(2, 0), new Point(1, -1), new Point(0, -1)));

        Field field = new Field(points);
        Set<Point> resultAdjacentLayer = field.getAdjacentLayer();
        Set<Point> resultFieldsCore = field.getFieldsCore();

        assertThat(resultAdjacentLayer, is(expectedAdjacentLayer));
        assertThat(resultFieldsCore, is(expectedFieldsCore));
    }

    /**
     * Тест для joinPoint.
     * */
    @Test
    public void whenAddPointThenGetExtendedField() {

        /* Проверка 1-й конфигурации поля*/
        Field expectedField = new Field(new Point[] {new Point(0, 0), new Point(1, 0), new Point(1, 1)});

        Field field = new Field(new Point[] {new Point(0, 0), new Point(1, 0)});
        field.joinPoint(new Point(1, 1));

        assertThat(field.getFieldsCore(), is(expectedField.getFieldsCore()));
        assertThat(field.getAdjacentLayer(), is(expectedField.getAdjacentLayer()));

        /* Проверка второй конфигурации поля (добавление точки с "разврывом")*/
        expectedField = new Field(new Point[] {new Point(0, 0), new Point(1, 0), new Point(3, 0)});
        field = new Field(new Point[] {new Point(0, 0), new Point(1, 0)});
        field.joinPoint(new Point(3, 0));

        assertThat(field.getFieldsCore(), is(expectedField.getFieldsCore()));
        assertThat(field.getAdjacentLayer(), is(expectedField.getAdjacentLayer()));

    }

    /**
     * Тест для joinField.
     * */
    @Test
    public void whenJoinFieldThenGetExtendedField() {
        /* Проверка 1-й конфигурации поля */
        Field expectedField = new Field(new Point[]{new Point(0, 0), new Point(1, 0)});

        Field field = new Field(new Point[] {new Point(0, 0)});
        Field joinedField = new Field(new Point[]{new Point(1, 0)});

        field.joinField(joinedField);

        assertThat(field.getFieldsCore(), is(expectedField.getFieldsCore()));
        assertThat(field.getAdjacentLayer(), is(expectedField.getAdjacentLayer()));

        /* Проверка 2-й конфигурации поля (поле с "разрывом" ядра)*/
        expectedField = new Field(new Point[]{new Point(0, 0), new Point(2, 0)});

        field = new Field(new Point[] {new Point(0, 0)});
        joinedField = new Field(new Point[]{new Point(2, 0)});

        field.joinField(joinedField);

        System.out.println(expectedField.getFieldsCore());
        assertThat(field.getFieldsCore(), is(expectedField.getFieldsCore()));
        assertThat(field.getAdjacentLayer(), is(expectedField.getAdjacentLayer()));
    }

    /**
     * Тест для isIntersection.
     * */
    @Test
    public void whenIsIntersectionThenGetTrueIfFieldsIntersectioned() {
        /* проверка двух пересекающихся полей */
        Field field = new Field(new Point[] {new Point(0, 0), new Point(1, 0)});
        Field secondField = new Field(new Point[] {new Point(2, 0), new Point(3, 0)});

        assertThat(field.isIntersection(secondField), is(true));

        /*Проверка: от перестановки полей результат не изменится*/
        assertThat(secondField.isIntersection(field), is(true));

        /* проверка двух непересекающихся полей */
        field = new Field(new Point[] {new Point(0, 0), new Point(1, 0)});
        secondField = new Field(new Point[] {new Point(3, 0)});

        assertThat(field.isIntersection(secondField), is(false));

        /*Проверка: от перестановки полей результат не изменится*/
        assertThat(secondField.isIntersection(field), is(false));



    }


}
