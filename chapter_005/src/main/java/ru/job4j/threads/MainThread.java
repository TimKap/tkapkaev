package ru.job4j.threads;

/**
 * Created by Tim on 04.08.2017.
 */
public class MainThread {
    /**
     * Задача подсчета сиволов в тексте.
     *
     * @param args - аргументы командной строки
     */
    public static void main(String[] args) {
        String[] text = {"abc", "defgh", "ijkl", "mnopqr", "stuvwxyz"};

        CountChar task = new CountChar(text);
        // Поток задачи подсчета числа символов в тексте
        Thread countSymbolsThread = new Thread(task);
        // Поток задачи контроля времени выполнения задачи task в потоке countSymbolsThread
        Thread timeThread = new Thread(new Time(countSymbolsThread, 1));

        // запуск потоков
        countSymbolsThread.start();
        timeThread.start();

        try {
            //ожидание главным потоком завершения задач в дочерних потоках
            countSymbolsThread.join();
            timeThread.join();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
