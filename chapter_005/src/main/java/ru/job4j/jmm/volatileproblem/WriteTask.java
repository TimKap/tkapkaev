package ru.job4j.jmm.volatileproblem;

/**
 * Class WriteTask описывает задачу изменения разделяемой переменной.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 10.08.2017
 */
public class WriteTask implements Runnable {

    /** Разделяемая переменная. */
    private SharedVariable sharedVariable;
    /***
     * Конструктор класса WriteTask.
     * @param sharedVariable - разделяемая переменная
     * */
    public WriteTask(SharedVariable sharedVariable) {
        this.sharedVariable = sharedVariable;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            sharedVariable.add(1);
            System.out.println("Counter: " + sharedVariable.getCounter());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
