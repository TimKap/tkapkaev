package ru.job4j.jmm.synchronizationproblem;

/**
 * Class SynchronizationProblem описывает проблему синхронизации в многопоточных приложениях.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 10/08.2017
 */
public class SynchronizationProblem {
    /**
     * Точка входа.
     * @param args - аргументы командной строки
     * */
    public static void main(String[] args) {

        IncrementTask task = new IncrementTask();

        Thread th1 = new Thread(task);
        Thread th2 = new Thread(task);
        th1.start();
        th2.start();
        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Counter expected 20 but result is: " + task.getCounter());
    }
}
