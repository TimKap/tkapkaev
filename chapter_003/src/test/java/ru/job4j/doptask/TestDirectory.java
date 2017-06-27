package ru.job4j.doptask;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Class содержит тесты для дополнительного задания.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 27.06.2017
 */
public class TestDirectory {
    /**
     * Тест для метода сортировки списка по возрастанию.
     * */
    @Test
    public void whenAscendSortThenGetSortedDirectory() {
        ArrayList<String> departments = new ArrayList<>();
        departments.addAll(Arrays.asList("K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"));
        Directory directory = new Directory(departments);

        List<String> expected = new ArrayList<String>();
        expected.addAll(Arrays.asList("K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2", "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"));
        directory.ascendSort();

        List<String> result = directory.getDepartments();
        assertThat(result, is(expected));
    }

    /**
     * Тест для метода сортировки списка по убыванию.
     * */
    @Test
    public void whenDescendSortThenGetSortedDirectory() {
        ArrayList<String> departments = new ArrayList<>();
        departments.addAll(Arrays.asList("K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"));
        Directory directory = new Directory(departments);

        List<String> expected = new ArrayList<String>();
        expected.addAll(Arrays.asList("K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1", "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"));
        directory.descendSort();

        List<String> result = directory.getDepartments();
        assertThat(result, is(expected));
    }

}
