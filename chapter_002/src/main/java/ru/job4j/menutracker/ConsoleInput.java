package ru.job4j.menutracker;
import java.util.Scanner;

/**
 * Class ConsoleInput обеспечивает ввод данных из консоли.
 *  @author Timur Kapkaev (timur.kap@yandex.ru)
 *  @version $Id$
 *  @since 09.04.2017
 */
public class ConsoleInput implements Input {
    /** Консольный ввод.*/
    private Scanner scanner = new Scanner(System.in);
    /**
     * Возвращает ответ на вопрос.
     * @param question - вопрос
     * @return ответ на вопрос
     */
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }



}
