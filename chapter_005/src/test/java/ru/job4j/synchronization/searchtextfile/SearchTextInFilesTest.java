package ru.job4j.synchronization.searchtextfile;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Arrays;
/**
 * Class SearchTextInFilesTest содержит тесты для SearchTextInFiles.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 22.08.2017
 */
public class SearchTextInFilesTest {

    /**
     * Тест для run.
     * */
    @Test
    public void whenRunThenGetListOfFilesContainText() {
        List<String> expected = new ArrayList<>(Arrays.asList(".\\src\\test\\java\\ru\\job4j\\synchronization\\searchtextfile\\SearchTextInFilesTest.java"));

        List<String> exts = new ArrayList<>(Arrays.asList("java"));
        Queue<String> filesName = new LinkedList<>();
        SearchFiles searhFiles = new SearchFiles(".\\src\\test\\java\\ru\\job4j\\synchronization\\searchtextfile", exts, filesName);
        searhFiles.run();

        List<String> result = new ArrayList<>();
        SearchTextInFile searchTextInFile = new SearchTextInFile(filesName, " whenRunThenGetListOfFilesContainText", result);
        Thread.currentThread().interrupt();
        searchTextInFile.run();

        assertThat(result, is(expected));

    }
}
