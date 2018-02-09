package ru.job4j.ioc.model;
/**
 * Class User писывает модель User.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 08.02.2018
 * */
public class User extends PersistentClass {
    /** login. */
    private String login;
    /** surname. */
    private String surname;
    /** password.*/
    private String password;

    /**
     * Клнструктор User.
     * @param login - логин
     * @param surname - фамилия
     * @param password - пароль
     * */
    public User(String login, String surname, String password) {
        this.login = login;
        this.surname = surname;
        this.password = password;
    }

    /**
     * Возвращает логин.
     * @return логин
     * */
    public String getLogin() {
        return login;
    }

    /**
     * Задает логин.
     * @param login - логин
     * */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Возвращает фамилия.
     * @return фамилия
     * */
    public String getSurname() {
        return surname;
    }

    /**
     * Задает фамилию.
     * @param surname - фамиллия
     * */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Возвращает пароль.
     * @return пароль
     * */
    public String getPassword() {
        return password;
    }

    /**
     * Задает пароль.
     * @param password - пароль
     * */
    public void setPassword(String password) {
        this.password = password;
    }
}
