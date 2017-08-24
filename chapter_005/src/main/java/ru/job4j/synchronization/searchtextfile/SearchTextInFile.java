package ru.job4j.synchronization.searchtextfile;


import net.jcip.annotations.ThreadSafe;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Class SearchTextInFile описывает задачу поиска текста в файле.
 *
 * @author Timur Kapakev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 22.08.2017
 */
@ThreadSafe
public class SearchTextInFile implements Runnable {
    /**
     * Искомый текст.
     */
    private final String text;

    /**
     * Список файлов, в которых ыыполняется поиск.
     */
    private final ConcurrentLinkedQueue<String> filesName;

    /**
     * Файлы, в которых обнаружен искомый текст.
     */
    private final CopyOnWriteArrayList<String> filesContainsText;

    /**
     * Конструктор  класса SearchTextInFile.
     *
     * @param filesName         - список файлов, в которых выполняется поиск
     * @param text              - искомый текст
     * @param filesContainsText - Файлы, в которых обнаружен искомый текст
     */

    public SearchTextInFile(ConcurrentLinkedQueue<String> filesName, String text, CopyOnWriteArrayList<String> filesContainsText) {
        this.filesName = filesName;
        this.text = text;
        this.filesContainsText = filesContainsText;
    }

    /**
     * Задача поиска текста в файле.
     */
    @Override
    public void run() {
        while (true) {
            String fileName;

            if (filesName.isEmpty() && Thread.currentThread().isInterrupted()) {
                break;
            }

            fileName = filesName.poll();

            if (fileName != null && searchText(fileName, text)) {
                filesContainsText.add(fileName);
            }
        }

    }

    /**
     * Поиск текста в файле.
     *
     * @param fileName - имя файла
     * @param text     - искомый текст
     * @return true, если в файле был обнаружен искомый текст
     */
    private boolean searchText(String fileName, String text) {

        BufferedReader file = null;
        boolean result = false;
        try {
            file = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            String str = file.readLine();
            while (str != null) {
                if (str.contains(text)) {
                    result = true;
                    break;
                }
                str = file.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
