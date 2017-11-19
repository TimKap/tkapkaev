package ru.job4j.firstservlets;

import ru.job4j.servletsform.ICRUDQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class User описывает модель пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 04.11.2017
 * */
public class User implements ICRUDQueries {
    /** имя. */
    private String name;
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
     * Конструктор класса User.
     * @param builder - UserBuilder
     * */
    protected User(UserBuilder builder) {
        this.name = builder.name;
        this.login = builder.login;
        this.email = builder.email;
        this.createDate = builder.createDate;
    }

    /**
     * Класс описывает builder для User.
     * @author Timur Kapkaev (timur.kap@yandex.ru)
     * @version $ID$
     * @since 16.11.2017
     * */
    public static class UserBuilder {
        /** имя. */
        private  String name;
        /** логин. */
        private String login;
        /** email. */
        private String email;
        /** дата создания. */
        private Date createDate;

        /**
         * Добавляет имя пользователя.
         * @param name - имя пользователя.
         * @return ссылка на объект UserBuilder
         * */
        public UserBuilder addName(String name) {
            this.name = name;
            return this;
        }
        /**
         * Добавляет логин пользователя.
         * @param login - логин пользователя.
         * @return ссылка на объект UserBuilder
         * */
        public  UserBuilder addLogin(String login) {
            this.login = login;
            return this;
        }
        /**
         * Добавляет электронный адрес пользователя.
         * @param email - электронный адрес пользователя.
         * @return ссылка на объект UserBuilder
         * */
        public UserBuilder addEmail(String email) {
            this.email = email;
            return this;
        }
        /**
         * Добавляет дату создания пользователя.
         * @param createDate - дата создания пользователя.
         * @return ссылка на объект UserBuilder
         * */
        public UserBuilder addCreateDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        /**
         * Создает объект User.
         * @return User
         * */
        public User build() {
            return new User(this);
        }



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
     * Задает имя пользователя.
     * @param name - имя
     * */
    public void setName(String name) {
        this.name = name;
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

    /**
     * Формирование запроса на добавление пользователя.
     * @param table - таблица
     * @return запрос на добавление пользователя
     * @throws SQLException - ошибка операции вставки
     * */
    @Override
    public PreparedStatement insert(Connection connection, String table) throws SQLException {
        PreparedStatement query = connection.prepareStatement(String.format("INSERT INTO %s (login, name, email) VALUES(?, ?, ?);", table));
        query.setString(1, login);
        query.setString(2, name);
        query.setString(3, email);
        return query;
    }
    /**
     * Формирование запроса на удаление пользователя.
     * @param table - таблица
     * @return запрос на добавление пользователя
     * @throws SQLException - ошибка операции удаления
     * */
    @Override
    public PreparedStatement delete(Connection connection, String table) throws SQLException {
        PreparedStatement query = connection.prepareStatement(String.format("DELETE FROM %s WHERE login=?;", table));
        query.setString(1, login);
        return query;
    }
    /**
     * Формирование запроса на добавление пользователя.
     * @param table - таблица
     * @return запрос на добавление пользователя
     * @throws SQLException - ошибка операции обновления
     * */
    @Override
    public PreparedStatement update(Connection connection, String table) throws SQLException {
        PreparedStatement query = connection.prepareStatement(String.format("INSERT INTO %s (login, name, email) VALUES(?, ?, ?) ON CONFLICT (login) DO UPDATE SET name=EXCLUDED.name, email=EXCLUDED.email, date=NOW();", table));
        query.setString(1, login);
        query.setString(2, name);
        query.setString(3, email);
        return query;
    }
}
