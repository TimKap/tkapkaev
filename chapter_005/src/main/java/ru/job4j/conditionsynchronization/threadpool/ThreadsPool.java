package ru.job4j.conditionsynchronization.threadpool;

import java.util.ArrayList;
import java.util.List;

/**
 * Class ThreadPool описывает пул потоков.
 *
 * @author Timur Kakpaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 25.08.2017
 */
public class ThreadsPool {
    /**
     * Очередь задач.
     */
    private final BlockingQueue<Runnable> taskQueue = new BlockingQueue<>();

    /**
     * Ограничение на число потоков в пуле.
     */
    private final int threadLimit;

    /**
     * Потоки пула.
     */
    private List<PoolThread> poolThreads = new ArrayList<>();

    /**
     * Конструктор класса ThreadPool.
     *
     * @param threadLimit - ограничение на число потоков в пуле
     */
    public ThreadsPool(int threadLimit) {
        this.threadLimit = threadLimit;
    }

    /**
     * Добавляет задачу на исполнение.
     *
     * @param task - задача
     */
    public synchronized void execute(Runnable task) {
        taskQueue.enqueue(task);
        if (poolThreads.size() < threadLimit) {
            PoolThread thread = new PoolThread(taskQueue);
            poolThreads.add(thread);
            thread.start();
        }
    }

    /**
     * Останавливает работу пулапотоков.
     */
    public synchronized void abort() {
        System.out.println("Запущен процесс остановки пула потоков...");
        while (poolThreads.size() > 0) {
            poolThreads.get(0).abort();
            poolThreads.remove(0);
        }

    }
}
