package ru.job4j.testtask.command;

import java.util.HashMap;


/**
 * Class Command описывает команду.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 24.07.2017
 */
public class Command {
    /** Имя команды. */
    private final String name;

    /** Атрибуты команды. */
    private HashMap<String, String> atributes;

    /***
     * Конструктор для класса Command.
     * @param name - имя команды
     * @param attributes - список атрибутов
     */
    public Command(String name, HashMap<String, String> attributes) {
        this.name = name;
        this.atributes = attributes;
    }
    /**
     * Конструктор для класса Command.
     * @param name - имя команды
     * */
    public Command(String name) {
        this(name, new HashMap<>());
    }

    /**
     * Возвращает имя команды.
     * @return имя команды
     * */
    public String getName() {
        return name;
    }

    /**
     * Возвращает атрибуты команды.
     * @return атрибуты команды
     * */
    public HashMap<String, String> getAttributes() {
        return atributes;
    }

    /**
     * Добавляет атрибут команде.
     * @param name - имя атрибута
     * @param value - значение атрибута
     * */
    public void addAttribute(String name, String value) {
        atributes.put(name, value);
    }

}
