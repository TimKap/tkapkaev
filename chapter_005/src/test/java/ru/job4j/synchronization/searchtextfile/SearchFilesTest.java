package ru.job4j.synchronization.searchtextfile;

import org.junit.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class SearchFilesTest содержит тесты для SearchFilesTest.
 *
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 22.08.2017
 */
public class SearchFilesTest {
    /**
     * Тест для метода run.
     */
    @Test
    public void whenRunThenGetQueueOfFilesWithSearchingParameters() {

        ConcurrentLinkedQueue<String> expected = new ConcurrentLinkedQueue<>(Arrays.asList(".\\src\\test\\java\\ru\\job4j\\synchronization\\searchtextfile\\package-info.java",
                ".\\src\\test\\java\\ru\\job4j\\synchronization\\searchtextfile\\SearchFilesTest.java",
                ".\\src\\test\\java\\ru\\job4j\\synchronization\\searchtextfile\\SearchTextInFilesTest.java",
                ".\\src\\test\\java\\ru\\job4j\\synchronization\\searchtextfile\\ParallelSearchTest.java"));

        List<String> exts = new ArrayList<>(Arrays.asList("java"));
        ConcurrentLinkedQueue<String> result = new ConcurrentLinkedQueue<>();
        SearchFiles searhFiles = new SearchFiles(".\\src\\test\\java\\ru\\job4j\\synchronization\\searchtextfile", exts, result);
        searhFiles.run();

        assertThat(true, is(expected.contains(result.poll())));
        assertThat(true, is(expected.contains(result.poll())));
        assertThat(true, is(expected.contains(result.poll())));
        assertThat(true, is(expected.contains(result.poll())));


    }
}
