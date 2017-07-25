package ru.job4j.testtask.commandinterpretator;

import ru.job4j.testtask.command.Command;

/**
 * Interface ICommandInterpretator содержит метод выполнения команд.
 * @author Timur Kapakev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 24.07.2017
 */
public interface ICommandInterpretator {
    /**
     * @param command - команда на исполнение
     * */
    void execute(Command command);
}
