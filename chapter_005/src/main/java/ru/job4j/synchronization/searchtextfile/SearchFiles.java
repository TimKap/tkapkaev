package ru.job4j.synchronization.searchtextfile;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Class SearchFiles выполняет поиск файлов с задданным расширением.
 *
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 22.08.2017
 */
@ThreadSafe
public class SearchFiles implements Runnable {

    /**
     * Заданные расширения.
     */
    private final List<String> exts;

    /**
     * Очередь файлов с заданным раширением.
     */
    @GuardedBy(value = "filesNAme")
    private final Queue<String> filesName;

    /**
     * Каталог, с которого начинается поиск.
     */
    private final String root;

    /**
     * Конструктор класса SearchFiles.
     *
     * @param root      - каталог, с которого начинается поиск
     * @param exts      - расширения файлов
     * @param filesName -  очередь файлов с заданным расширением
     */
    public SearchFiles(String root, List<String> exts, Queue<String> filesName) {
        this.root = root;
        this.exts = exts;
        this.filesName = filesName;
    }

    /**
     * Задача формирования очереди из файлов с заданным расширением.
     * */
    @Override
    public void run() {
        makeQueue(root);
    }

    /**
     * Формирование очереди из имен файлов с заданным расширением.
     * @param path - путь к каталогу
     * */
    private void makeQueue(String path) {

        /* текущий каталог */
        File rootDirectory = new File(path);
        if (rootDirectory.isDirectory()) {

            /* Имена каталогов и файлов, содеражащихся в текущем каталоге */
            String[] entitiesName = rootDirectory.list();

            /* Абсолютные пути к каталогам в текущем каталоге */
            List<String> directoriesPath = new ArrayList<>();

            /* поиск каталогов и заполнение очереди из файлов */
            if (entitiesName != null) {
                for (String entityName : entitiesName) {
                    String pathToEntity = path + File.separator + entityName;
                    if (new File(pathToEntity).isDirectory()) {
                        directoriesPath.add(pathToEntity);
                    } else {
                        if (checkExtension(pathToEntity)) {
                            /* добавляем файл в очередь */
                            synchronized (filesName) {
                                filesName.add(pathToEntity);
                            }
                        }
                    }
                }
            }
            if (!directoriesPath.isEmpty()) {
                for (String directoryPath : directoriesPath) {
                    makeQueue(directoryPath);
                }
            }
        }
    }

    /**
     * Проверка расширения файла.
     * @param fileName - имя файла
     * @return true, если расширение удовлетворяет требованиям
     * */
    private boolean checkExtension(String fileName) {
        int indexPoint = fileName.lastIndexOf(".");
        if ((indexPoint != -1) && (indexPoint != 0)) {
            String extension = fileName.substring(indexPoint + 1);
            if (exts.contains(extension)) {
                return true;
            }
        }
        return false;
    }

}