package ru.job4j.threads;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class StringParser описывает многопоточный парсинг строки.
 *
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 31.07.2017
 */
public class StringThreadParser implements Runnable {

    /**
     * Входная строка.
     */
    private final String stroka;
    /**
     * Шаблон.
     */
    private final String pattern;

    /**
     * Конструктор класса StringThreadParser.
     *
     * @param stroka  - входная строка
     * @param pattern - шаблон
     */
    public StringThreadParser(String stroka, String pattern) {
        this.stroka = stroka;
        this.pattern = pattern;
    }

    /**
     * Задача определения числа совпадений шаблона.
     */
    @Override
    public void run() {
        Pattern pattern = Pattern.compile(this.pattern);
        Matcher m = pattern.matcher(stroka);
        int i = 0;
        while (m.find()) {
            i++;
        }
        System.out.println("Thread name: " + Thread.currentThread().getName());
        System.out.printf("Pattern(%s): %d\r\n", pattern, i);
    }


    /**
     * Точка входа в программу.
     * @param args - аргументы командной строки
     * */
    public static void main(String[] args) {

        String stroka = "abs gg hh";

        String patSpace = " ";
        String patWord = "[\\w]+";


        Thread thread1 = new Thread(new StringThreadParser(stroka, patSpace));
        Thread thread2 = new Thread(new StringThreadParser(stroka, patWord));
        thread1.setName("Search Space");
        thread2.setName("Search Word");
        thread1.start();
        thread2.start();
    }
}
