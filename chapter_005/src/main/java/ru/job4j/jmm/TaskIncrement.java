package ru.job4j.jmm;

/**
 * Class TaskIncrement огписывает задачу инкремента переменной.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 08.008.2017
 */
public class TaskIncrement implements Runnable {

    /** разделяемая переменная. */
    private SharedVariable sharedVariable;

    /**
     * Конструктор класса TaskIncrement.
     * @param sharedVariable - разделяемая переменная
     * */
    public TaskIncrement(SharedVariable sharedVariable) {
        this.sharedVariable = sharedVariable;
    }

    /**
     * Задача инкремента.
     * */
    @Override
    public void run() {
        int counter = 0;
        for (;;) {
            counter++;
            sharedVariable.setCounter(counter);
            System.out.printf("Counter = %d, Thread name: %s, Local counter %d\r\n", sharedVariable.getCounter(), Thread.currentThread().getName(), counter);
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }

    }
}
