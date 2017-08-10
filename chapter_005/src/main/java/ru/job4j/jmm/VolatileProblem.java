package ru.job4j.jmm;

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
        Thread threadIncrement = new Thread(new TaskIncrement(sharedVariable));
        Thread threadShow = new Thread(new ShowSharedVariable(sharedVariable));
        threadIncrement.setName("change variable");
        threadShow.setName("show variable");
        threadIncrement.start();
        threadShow.start();

        try {
            threadIncrement.join();
            threadShow.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
