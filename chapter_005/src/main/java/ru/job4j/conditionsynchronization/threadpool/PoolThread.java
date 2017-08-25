package ru.job4j.conditionsynchronization.threadpool;

/**
 * Class PoolThread описывает поток из пула потоков.
 *
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 25.08.2017
 */
class PoolThread extends Thread {
    /**
     * Очередь задач.
     */
    private final BlockingQueue<Runnable> taskQueue;

    /** Состояние потока. */
    private boolean isRun = true;

    /**
     * Конструктор класса PoolThread.
     *
     * @param taskQueue - очередь задач
     */
    PoolThread(BlockingQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    /**
     * Исполнение потоком из пула очереди задач.
     * */
    @Override
    public void run() {
        while (isRun) {
            Runnable task;
            try {
                task = taskQueue.dequeue();
                task.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Завершает выполнение потока.
     * */
    public void abort() {
        isRun = false;
        interrupt();
    }
}
