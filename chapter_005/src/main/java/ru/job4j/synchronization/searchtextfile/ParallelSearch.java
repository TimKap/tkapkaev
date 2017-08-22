package ru.job4j.synchronization.searchtextfile;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * Class ParallelSearch описывает поиск заданного текста в файловой системе.
 * Поиск осуществляется параллельно в нескольких файлах
 *
 * @author Timur Kapkaev (timur.kap@ayndex.ru)
 * @version $Id$
 * @since 22.08.2017
 */
public class ParallelSearch {
    /**
     * Путь до папки откуда надо осуществлять поиск.
     */
    private final String root;
    /**
     * Заданный текст.
     */
    private final String text;
    /**
     * Расширения файлов, в которых выполняется поиск.
     */
    private final List<String> exts;

    /**
     * Коструктор класса ParallelSearch.
     *
     * @param root - каталог, с которого начинаетя поиск
     * @param text - заданный текст
     * @param exts - расширения файлов, в которых осуществляется поиск
     */
    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    /**
     * Поиск заданного текста в файловой системе.
     *
     * @return список файлов, содержащих указанный текст.
     */
    List<String> result() {
        Queue<String> filesName = new LinkedList<>();
        List<String> filesContainsText = new ArrayList<>();
        Thread makeFilesNameQueue = new Thread(new SearchFiles(root, exts, filesName));

        SearchTextInFile searchTask = new SearchTextInFile(filesName, text, filesContainsText);
        Thread searchText1 = new Thread(searchTask);
        Thread searchText2 = new Thread(searchTask);
        Thread searchText3 = new Thread(searchTask);

        makeFilesNameQueue.start();
        searchText1.start();
        searchText2.start();
        searchText3.start();


        try {
            makeFilesNameQueue.join();
            searchText1.interrupt();
            searchText2.interrupt();
            searchText3.interrupt();
            searchText1.join();
            searchText2.join();
            searchText3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return filesContainsText;

    }
}
