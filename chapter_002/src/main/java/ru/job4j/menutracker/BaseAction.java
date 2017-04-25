package ru.job4j.menutracker;

/**
 * Abstract Class BaseAction описывает поведение характерное для всех действий производимых пользователем.
 * @author Timur Kapkaev (timur.kap@ayndex.ru)
 * @version $Id$
 * @since 25.04.2017
 */
public abstract class BaseAction implements UserAction {

    /** Название действия. */
    private String name;

    /**
     * Конструктор класса.
     * @param name - название деймствия
     * */
    public BaseAction(String name) {
        this.name = name;
    }

    /** Вернуть назначенный ключ пункту меню.
     * @return ключ пункта меню
     * */
    public abstract int key();

    /** Выполнить действие.*/
    public abstract void execute();

    /** Информация о пункте меню.
     * @return информация о пункте меню
     * */
    public String info() {
        return String.format("%s. %s", key(), name);
    }
}
