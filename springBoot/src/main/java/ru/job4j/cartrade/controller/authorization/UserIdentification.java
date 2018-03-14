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

    /**
     * Проврка на авенство объектов.
     * @param o - сравниваемый объект
     * @return true, если объекты равны
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserIdentification userIdentification = (UserIdentification) o;

        if (role != null ? !role.equals(userIdentification.role) : userIdentification.role != null) {
            return false;
        }
        if (login != null ? !login.equals(userIdentification.login) : userIdentification.login != null) {
            return false;
        }
        return password != null ? password.equals(userIdentification.password) : userIdentification.password == null;
    }

    /**
     * Вычисляет хэш-функцию.
     * @return хэш-код
     * */
    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
