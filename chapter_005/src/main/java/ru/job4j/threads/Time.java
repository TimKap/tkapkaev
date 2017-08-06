package ru.job4j.threads;

/**
 * Class Time описывает задачу проверки времени.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 04.08.2017
 */
public class Time implements Runnable {

    /** Управляемый поток. */
    private Thread controlledThread;

    /** Время ожидания выполнения задачи [мс]. */
    private long waitingTime;

    /**
     * Конструктор класса Time.
     * @param controlledThread - управляемый поток
     * @param waitingTime - время ожидания завершения задачи
     * */
    public Time(Thread controlledThread, long waitingTime) {
        this.controlledThread = controlledThread;
        this.waitingTime = waitingTime;
    }

    /**
     * Задача управления другим потоком.
     */
    @Override
    public void run() {

        try {
            Thread.sleep(waitingTime);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        if (controlledThread.isAlive()) {
            controlledThread.interrupt();
        }

    }

}
