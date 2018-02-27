package ru.job4j.cartrade.controller.authorization;

/**
 * Class USerIdentification  описывает оюъект идентификации пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 23.11.2017
 * */
public class UserIdentification {
    /** id пользователя. */
    private long id;
    /** роль пользователя. */
    private String role;
    /** логин пользователя. */
    private String login;
    /** пароль пользователя. */
    private String password;

    /**
     * Конструктор UserIdentification.
     * @param id - id пользователя.
     * @param role - роль пользователя.
     * @param login - логин пользователя.
     * @param password - пароль пользователя
     * */
    public UserIdentification(long id, String role, String login, String password) {
        this.id = id;
        this.role = role;
        this.login = login;
        this.password = password;
    }
    /**
     * Конструктор UserIdentification.
     * */
    public UserIdentification() {
    }
    /**
     * Возвращает id пользователя.
     * @return id
     * */
    public long getId() {
        return id;
    }
    /**
     * Возвращает роль пользователя.
     * @return роль
     * */
    public String getRole() {
        return role;
    }
    /**
     * Возвращает логин пользователя.
     * @return логин
     * */
    public String getLogin() {
        return login;
    }
    /**
     * Возвращает пароль пользователя.
     * @return пароль
     * */
    public String getPassword() {
        return password;
    }

    /**
     * Задает id.
     * @param id - id пользователя
     * */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Задает роль пользователя.
     * @param role - роль пользователя
     * */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Задает login.
     * @param login - login
     * */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Задает password.
     * @param password - пароль
     * */
    public void setPassword(String password) {
        this.password = password;
    }
}
