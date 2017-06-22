package ru.job4j.convertlist;

/**
 * Class User описывает пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 22.06.2017
 */
public class User {
    /** Id пользователя.*/
    private int id;
    /** Имя пользователя.*/
    private String name;
    /** Город.*/
    private String city;

    /**
     * Конструктор класса User.
     * @param id - id пользователя.
     * @param name - имя пользователя.
     * @param city - город.
     * */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Возвращает id пользователя.
     * @return id пользователя
     * */
    public int getId() {
        return id;
    }

    /**
     * Возвращает имя пользователя.
     * @return имя пользователя
     * */
    public String getName() {
        return name;
    }
    /**
     * Возвращает город пользователя.
     * @return город пользователя
     * */
    public String getCity() {
        return city;
    }


}
