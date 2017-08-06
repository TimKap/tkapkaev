package ru.job4j.threads;

/**
 * Class CountChar описывает задачу подсчета символов в строке..
 *
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 04.08.2017
 */
public class CountChar implements Runnable {

    /**
     * Заданный текст.
     */
    private final String[] text;

    /**
     * Количество символов в тексте.
     */
    private int result;

    /**
     * Конструктор класса CountChar.
     *
     * @param text - исходный текст
     */
    public CountChar(String[] text) {
        this.text = text;
    }


    @Override
    public void run() {
        result = 0;
        for (String stroka : text) {
            result += stroka.length();

            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Задача поиска символа прервана...");
                break;
            }
        }
        if (!Thread.currentThread().isInterrupted()) {
            System.out.println("Text contains " + result + " symbols");
        }
    }

    /**
     * Возвращает число символов в тексте.
     *
     * @return количество символов в тексте
     */
    public int getResult() {
        return this.result;
    }
}
