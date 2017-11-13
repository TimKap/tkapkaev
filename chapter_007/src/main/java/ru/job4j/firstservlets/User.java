package ru.job4j.firstservlets;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class User описывает модель пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 04.11.2017
 * */
public class User {
    /** имя. */
    private final String name;
    /** логин. */
    private String login;
    /** email. */
    private String email;
    /** дата создания. */
    private final Date createDate;

    /**
     * Конструктор класса User.
     * @param name - имя пользователя
     * @param login - логин
     * @param email - электронный адрес
     * @param createDate - дата создания
     * */
    public User(String name, String login, String email, Date createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    /**
     * Возвращает имя пользователя.
     * @return имя пользователя
     * */
    public String getName() {
        return name;
    }
    /**
     * Возвращает логин.
     * @return логин
     * */
    public String getLogin() {
        return login;
    }
    /**Возвращает электронный адрес.
     * @return электронный адрес
     * */
    public String getEmail() {
        return email;
    }
    /**
     * Возвращает дату создания.
     * @return дата создания
     * */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Задает логин пользователя.
     * @param login - логин
     * */
    public void setLogin(String login) {
        this.login = login;
    }
    /**
     * Задает электронный адрес пользователя.
     * @param email - электронный адрес пользователя.
     * */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Возвращает строковое представление пользователя.
     * @return строковое представление пользователя.
     * */
    public String stringForm() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Name:   %s\r\n", name));
        builder.append(String.format("Login:  %s\r\n", login));
        builder.append(String.format("e-mail: %s\r\n", email));

        builder.append(dateString());
        return builder.toString();
    }

    /**
     * Возвращает дату в строковом формате.
     * @return дату в формате строки
     * */
    public String dateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss z");
        return String.format("Date:   %s\r\n", dateFormat.format(createDate));
    }
}
