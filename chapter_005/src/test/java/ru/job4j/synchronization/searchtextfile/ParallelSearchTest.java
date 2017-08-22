package ru.job4j.synchronization.searchtextfile;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class ParallelSearchTest запускает задачу || поиска текста в файлах.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 22.08.2017
 */
public class ParallelSearchTest {
    /**
     *  Запуск задачи || поиска текста в файлах.
     * */
    @Test
    public void startParallelSearch() {
        List<String> exts = new ArrayList<>(Arrays.asList("java"));
        ParallelSearch search = new ParallelSearch("C:\\projects\\tkapkaev", "package", exts);
        long start = System.currentTimeMillis();
        List<String> result = search.result();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        for (String name : result) {
            System.out.println(name);
        }
    }
}
