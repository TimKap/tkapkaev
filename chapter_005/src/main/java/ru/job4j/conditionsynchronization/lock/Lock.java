package ru.job4j.conditionsynchronization.lock;

/**
 * Class Lock описывает простой механизм блокировки.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 25.08.201
 */
public class Lock {
    /** состояние блокировки. */
    private boolean isLocked = false;
    /**
     * Захват блокировки.
     * @throws InterruptedException - прерывание потока в состоянии ожидания
     * */
    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    /**
     * Освобождает блокировку.
     * */
    public synchronized void unlock() {
        isLocked = false;
        notifyAll();
    }
}
