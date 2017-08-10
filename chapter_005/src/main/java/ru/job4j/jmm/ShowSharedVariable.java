package ru.job4j.jmm;

/**
 * Class ShowSharedVariable Описывает задачу отображения разделяемой переменной.
 * @author Timur Kapakev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 08.08.2017
 */
public class ShowSharedVariable implements Runnable {

    /**разделяемая переменная. */
    private SharedVariable sharedVariable;

    /**
     * Конструктор ShowSharedVariable.
     * @param sharedVariable - разделяемая переменная.
     * */
    public ShowSharedVariable(SharedVariable sharedVariable) {
        this.sharedVariable = sharedVariable;
    }

    /**
     * Задача отображения разделяемой переменной.
     * */
    @Override
    public void run() {
        int counter = 0;
        for (;;) {
            counter++;
            System.out.printf("Counter = %d, Thread name: %s Local counter: %d \r\n", sharedVariable.getCounter(), Thread.currentThread().getName(), counter);
            try {
                Thread.sleep(4001);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
