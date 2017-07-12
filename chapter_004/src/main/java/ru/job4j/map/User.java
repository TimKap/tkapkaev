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

    /**
     * Вычисляет хеш-функцию для объекта класса User.
     * @return хеш-код
     * */

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + children;
        return result;
    }


    /**
     * Определяет равенство объектов класса User.
     * @param o - объект с которым выполняется сравнение.
     * @return true, если объекты равны.
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (children != user.children) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return birthday != null ? birthday.equals(user.birthday) : user.birthday == null;
    }


}
