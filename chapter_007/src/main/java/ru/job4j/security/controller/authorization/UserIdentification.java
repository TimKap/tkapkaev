package ru.job4j.security.controller.authorization;

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

    /**
     * Конструктор UserIdentification.
     * @param role - роль пользователя.
     * @param login - логин пользователя.
     * */
    public UserIdentification(String role, String login) {
        this.role = role;
        this.login = login;
    }
    /**
     * Возвращает роль пользователя.
     * @return роль
     * */
    public String getRole() {
        return role;
    }
    /**
     * Возвращает пароль пользователя.
     * @return пароль
     * */
    public String getLogin() {
        return login;
    }
}
