package ru.job4j.jmm.volatileproblem;

/**
 * Class ReadTask описывает задачу чтения разделяемой переменной.
 *
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 10.08.2017
 */
public class ReadTask implements Runnable {
    /**
     * разделяемая переменная.
     */
    private SharedVariable sharedVariable;

    /**
     * Конструктор класса ReadTask.
     * @param sharedVariable - разделяемая переменная
     */
    public ReadTask(SharedVariable sharedVariable) {
        this.sharedVariable = sharedVariable;
    }

    /**
     * Задача чтения разделяемой переменной.
     */
    @Override
    public void run() {

        while (true) {
            if (sharedVariable.getCounter() >= 5) {
                break;
            }
        }
        System.out.println("Read operation Ok: " + sharedVariable.getCounter());
    }
}
