package ru.job4j.menutracker;

/**
 * Interface Input обеспечивает ввод данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 09.04.2017
 */
public interface Input {
    /**
     * Ввод данных в приложение.
     * @param question - вопрос
     * @return - ответ на вопрос
     */
    String ask(String question);
}
