package ru.job4j.conditionsynchronization.lock;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class LockTest описывает демонстрацию работы собственного механизма блокировки.
 *
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 25.08.2017
 */
public class LockTest {

    /**
     * Демонстрация работы механизма блокировки.
     */
    @Test
    public void demonstrateLockMechanism() {
        ThreadSafeCounter counter = new ThreadSafeCounter();
        Thread th1 = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        try {
                            counter.increment();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

        );

        Thread th2 = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        try {
                            counter.increment();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

        );
        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();
            int expected = 20;
            int result = counter.getCounter();
            assertThat(result, is(expected));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}

/**
 * Class ThreadSafeCounter описывает потокобезопасный счетчик.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 25.08.2017
 * */
class ThreadSafeCounter {
    /**
     * Счетчик.
     */
    private int counter;

    /**
     * Блокировка.
     */
    private final Lock lock = new Lock();

    /**
     * Инкремент счетчика.
     * @throws InterruptedException - прерывание потока в состоянии ожидания
     */
    void increment() throws InterruptedException {
        try {
            lock.lock();
            counter++;
        } catch (InterruptedException e) {
            throw e;
        } finally {
            lock.unlock();
        }


    }

    /**
     * Возвращает значение счетчика.
     *
     * @return значение счетчика
     * @throws InterruptedException - прерывание потока в состоянии ожидания
     */
    int getCounter() throws InterruptedException {
        int counter;
        try {
            lock.lock();
            counter = this.counter;
        } catch (InterruptedException e) {
            throw e;
        } finally {
            lock.unlock();
        }
        return counter;
    }

}

