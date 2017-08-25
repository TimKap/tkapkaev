package ru.job4j.conditionsynchronization.threadpool;

import org.junit.Test;

/**
 * Class ThreadPoolTest содержит тесты для класса ThreadPool.
 *
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 25.08.2017
 */

public class ThreadPoolTest {
    /**
     * Демонстрация функционирования пула потоков.
     * */
    @Test
    public void demonstrate() {
        ThreadsPool pool = new ThreadsPool(2);
        pool.execute(new LoadTask("1"));
        pool.execute(new LoadTask("2"));
        pool.execute(new LoadTask("3"));
        pool.execute(new LoadTask("4"));
        try {
            Thread.sleep(5000);
            pool.abort();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Class LoadTask описывает нагрузочную задачу для демонстрации работы пула потоков.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 25.08.2017
 * */
class LoadTask implements Runnable {
    /** ID задачи. */
    private final String id;

    /**
     * Конструктор класса LoadTask.
     * @param id - ID задачи
     * */
    LoadTask(String id) {
        this.id = id;
    }

    /**
     * Задача - нагрузка.
     * */
    @Override
    public void run() {
        System.out.println(id);
        try {
            Thread.sleep(1000);
            System.out.printf("Задача %s выполнилась\r\n", id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
