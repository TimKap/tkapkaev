package ru.job4j.jmm.synchronizationproblem;

/**
 * Class IncrementTask описывает задачу инкремента разделяемой переменной.
 * @author Timur Kapkaev (timur.kap@yandx.ru)
 * @version $Id$
 * @since 10.08.2017
 */
public class IncrementTask implements Runnable {
    /** Разделяемая переменная. */
    private volatile int counter = 0;


    /**
     * Задача инкремента разделяемой переменной.
     * */
    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            counter++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Возращает занчение счетчика.
     * @return значение счетчика
     * */
    public int getCounter() {
        return counter;
    }
}
