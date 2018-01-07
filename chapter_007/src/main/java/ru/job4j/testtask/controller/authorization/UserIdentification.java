package ru.job4j.testtask.controller.authorization;

/**
 * Class USerIdentification  описывает оюъект идентификации пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 23.11.2017
 * */
public class UserIdentification {
    /** роль пользователя. */
    private final String role;
    /** логин пользователя. */
    private final String login;
    /** пароль пользователя. */
    private final String password;

    /**
     * Конструктор UserIdentification.
     * @param role - роль пользователя.
     * @param login - логин пользователя.
     * @param password - пароль пользователя
     * */
    public UserIdentification(String role, String login, String password) {
        this.role = role;
        this.login = login;
        this.password = password;
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
}
