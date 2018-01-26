package ru.job4j.cartrade.controller.authorization;

/**
 * Class USerIdentification  описывает оюъект идентификации пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 23.11.2017
 * */
public class UserIdentification {
    /** id пользователя. */
    private final long id;
    /** роль пользователя. */
    private final String role;
    /** логин пользователя. */
    private final String login;
    /** пароль пользователя. */
    private final String password;

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
}
