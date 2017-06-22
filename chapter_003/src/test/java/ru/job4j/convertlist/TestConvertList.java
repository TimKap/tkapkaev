package ru.job4j.convertlist;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class TestConvertList содержит тестовые методы для класса ConvertList.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 20.06.1990
 */
public class TestConvertList {

    /**
     * Тест для конвертации двумерного массива в список.
     */
    @Test
    public void whenConvertListThenGetArray() {

        int[][] mas = {{7, 6, 5}, {4, 3, 2}, {1, 0, 0}};
        List<Integer> expectedList = new ArrayList<Integer>();
        Collections.addAll(expectedList, 7, 6, 5, 4, 3, 2, 1, 0, 0);

        ConvertList convert = new ConvertList();

        List<Integer> resultList = convert.toList(mas);

        assertThat(resultList, is(expectedList));

     }

    /**
     * Тест для конвертации списка в двумекрный массив.
     */
    @Test
    public void whenConvertArrayThenGetList() {
        int[][] expectedMas = {{7, 6, 5}, {4, 3, 2}, {1, 0, 0}};

        ConvertList convert = new ConvertList();
        List<Integer> list = new ArrayList<Integer>();
        Collections.addAll(list, 7, 6, 5, 4, 3, 2, 1);
        int[][] resultMas = convert.toArray(list, 3);

        assertThat(resultMas, is(expectedMas));
    }

    /**
     * Тест для конвертации списка массивов целых чисел в один общий список.
     */
    @Test
    public void whenConvertListFromArraysThenGetUinionList() {
        List<int[]> data = new ArrayList<int[]>();

        data.add(new int[]{1, 2});
        data.add(new int[]{3, 4, 5, 6});

        List<Integer> expected = new ArrayList<Integer>();
        expected.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));

        List<Integer> result = new ConvertList().convert(data);

        assertThat(result, is(expected));
    }

}
