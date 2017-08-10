package ru.job4j.jmm.volatileproblem;

/**
 * Class VolatileProblem описывает проблему видимости разделяемых переменных в многопоточном приложении.
 *
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 08.08.2017
 */
public class VolatileProblem {

    /**
     * Точка входа.
     * @param args - аргументы командной строки
     * */
    public static void main(String[] args) {
        SharedVariable sharedVariable = new SharedVariable();

        Thread writeTask = new Thread(new WriteTask(sharedVariable));
        Thread readTask = new Thread(new ReadTask(sharedVariable));
        writeTask.start();
        readTask.start();

        try {
            writeTask.join();
            readTask.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
