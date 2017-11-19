package ru.job4j.security.model;

import ru.job4j.firstservlets.User;
import ru.job4j.servletsform.PrimaryKey;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Class AdvancedUser описывает модель пользователя с паролем и ролью.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 17.11.2017
 * */
public class AdvancedUser extends User {
    /** пароль. */
    private String password;

    /** роль пользователя. */
    private String role;

    /**
     * Конструктор класса AdvancedUser.
     * @param builder - AdvancedUserBuilder
     * */
    protected AdvancedUser(AdvancedUserBuilder builder) {
        super(builder);
        this.password = builder.password;
        this.role = builder.role;
    }

    /**
     * Класс описывает builder для AdvancedUser.
     * @author Timur Kapkaev (timur.kap@yandex.ru)
     * @version $ID$
     * @since 16.11.2017
     * */

    public static class AdvancedUserBuilder extends UserBuilder {
        /** пароль. */
        private String password;

        /** роль пользователя. */
        private String role;

        /**
         * Добавляет имя пользователя.
         * @param name - имя пользователя.
         * @return ссылка на объект AdvancedUserBuilder
         * */
        public AdvancedUserBuilder addName(String name) {
            super.addName(name);
            return this;
        }

        /**
         * Добавляет логин пользователя.
         * @param login - логин пользователя.
         * @return ссылка на объект AdvancedUserBuilder
         * */
        public  AdvancedUserBuilder addLogin(String login) {
            super.addLogin(login);
            return this;
        }

        /**
         * Добавляет электронный адрес пользователя.
         * @param email - электронный адрес пользователя.
         * @return ссылка на объект AdvancedUserBuilder
         * */
        public AdvancedUserBuilder addEmail(String email) {
            super.addEmail(email);
            return this;
        }

        /**
         * Добавляет дату создания пользователя.
         * @param createDate - дата создания пользователя.
         * @return ссылка на объект AdvancedUserBuilder
         * */
        public AdvancedUserBuilder addCreateDate(Date createDate) {
            super.addCreateDate(createDate);
            return this;
        }

        /**
         * Добавляет пароль пользователя.
         * @param password - пароль пользователя.
         * @return ссылка на объект AdvancedUserBuilder
         * */
        public AdvancedUserBuilder addPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * Добавляет дату создания пользователя.
         * @param role - дата создания пользователя.
         * @return ссылка на объект AdvancedUserBuilder
         * */
        public AdvancedUserBuilder addRole(String role) {
            this.role = role;
            return this;
        }

        /**
         * Создает объект AdvancedUser.
         * @return AdvancedUser
         * */
        @Override
        public AdvancedUser build() {
            return new AdvancedUser(this);
        }
    }


    /**
     * Возвращает роль пользователя.
     * @return роль пользователя
     * */
    public String getRole() {
        return role;
    }

    /**
     * Возвращает пароль пользователя.
     * @return пароль пользователя
     * */
    public String getPassword() {
        return password;
    }

    /**
     * Формирование запроса на добавление пользователя.
     * @param connection - соединение с базой данных
     * @param table - таблица
     * @return запрос на добавление пользователя
     * @throws SQLException - ошибка операции вставки
     * */
    @Override
    public PreparedStatement insert(Connection connection, String table) throws SQLException {
        PreparedStatement query = connection.prepareStatement(String.format("INSERT INTO %s (login, name, email, password, role) VALUES(?, ?, ?, ?, ?) ON CONFLICT (login) DO NOTHING;", table));
        query.setString(1, getLogin());
        query.setString(2, getName());
        query.setString(3, getEmail());
        query.setString(4, password);
        query.setString(5, role);
        return query;
    }

    /**
     * Формирование запроса на добавление пользователя.
     * @param connection - соединение с базой данных
     * @param table - таблица
     * @return запрос на добавление пользователя
     * @throws SQLException - ошибка операции обновления
     * */
    @Override
    public PreparedStatement update(Connection connection, String table) throws SQLException {
        PreparedStatement query = connection.prepareStatement(String.format("INSERT INTO %s (login, name, email, password, role) VALUES(?, ?, ?, ?, ?) ON CONFLICT (login) DO UPDATE SET name=EXCLUDED.name, email=EXCLUDED.email, date=NOW(), password=EXCLUDED.password, role=EXCLUDED.role;", table));
        query.setString(1, getLogin());
        query.setString(2, getName());
        query.setString(3, getEmail());
        query.setString(4, password);
        query.setString(5, role);
        return query;
    }

    /**
     * Class Key описывает первичный для AdvancedUser.
     * @author Timur Kapkaev (timur.kap@yandex.ru)
     * @version $ID$
     * @since 17.11.2017
     * */
    public static class Key extends PrimaryKey {

        /** логин пользователя. */
        private final String login;

        /**
         * Конструктор класса Key.
         * @param login - логин пользователя
         */
        public Key(String login) {
            this.login = login;
        }

        /**
         * Запрос поиска по первичному ключу.
         * @param connection - соединение с базой данных
         * @param table - таблица
         * @return запрос поиска по первичнному ключу
         * @throws SQLException - ошибка поиска.
         * */
        @Override
        public PreparedStatement searchQuery(Connection connection, String table) throws SQLException {
            PreparedStatement ps = connection.prepareStatement(String.format("SELECT * FROM %s WHERE login = ?;", table));
            ps.setString(1, login);
            return ps;
        }
    }

    /**
     * Добавляет информацию о пользователе в запрос.
     * @param request - запрос
     * */
    public void insertUserToRequest(HttpServletRequest request) {
        request.setAttribute("login", getLogin());
        request.setAttribute("name", getName());
        request.setAttribute("email", getEmail());
        request.setAttribute("role", getRole());
        request.setAttribute("password", getPassword());

    }

    /** Изменяет параметры пользователя.
     * @param name - новое имя поьзователя.
     * @param email - новый email пользователя.
     * @param role - роль пользователя.
     * @param password - новый пароль пользователя.
     * */

    public void modifUser(String name, String email, String role, String password) {
        if (name != null && !name.equals("")) {
            setName(name);
        }

        if (email != null && !email.equals("")) {
            setEmail(email);
        }

        if (role != null && !role.equals("")) {
            this.role = role;
        }
        if (password != null && !password.equals("")) {
            this.password = password;
        }
    }


}
