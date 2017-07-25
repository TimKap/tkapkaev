package ru.job4j.testtask.command;

/**
 * Интерфей описывающий поток команд.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 24.07.2017
 */
public interface ICommandStream {
    /**
     * Возвращает команду из потока.
     * @return команду из потока, null если в потоке нет окманды
     * */
    Command getCommand();

    /**
     * Освобождает поток.
     * */
    void close();
}

