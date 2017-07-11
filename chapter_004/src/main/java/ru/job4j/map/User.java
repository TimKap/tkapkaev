package ru.job4j.map;

import java.util.Calendar;

/**
 * Class User описывает пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 11.07.2017
 */
public class User {
    /** Имя пользователя. */
    private String name;
    /** Дата рождения. */
    private Calendar birthday;
    /** Количество детей. */
    private int children;

    /**
     * Конструктор класса User.
     * @param name - имя пользователя
     * @param birthday - дата рождения
     * @param children - количество детей
     * */
    public User(String name, Calendar birthday, int children) {
        this.name = name;
        this.birthday = birthday;
        this.children = children;
    }

    /**
     * Конструктор класса User.
     * @param name - имя пользователя
     * @param birthday - дата рождения
     * */
    public User(String name, Calendar birthday) {
        this(name, birthday, 0);
    }

    /**
     * Возвращает имя пользователя.
     * @return имя пользователя
     * */
    public String getName() {
        return name;
    }

    /**
     * Возвращает дату рождения.
     * @return дата рождения
     * */
    public Calendar getBirthday() {
        return birthday;
    }

    /**
     * Возвращает количество детей.
     * @return количество детей
     * */
    public int getChildren() {
        return children;
    }

    /**
     * Задает  количество детей у пользователя.
     * @param children - количество детей
     * */
    public void setChildren(int children) {
        this.children = children;
    }
}
