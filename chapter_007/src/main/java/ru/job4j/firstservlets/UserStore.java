package ru.job4j.firstservlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.Properties;
import java.util.Date;

/**
 * Class UserStore описывает работу с базой данных пользователей.
 * @author Timur Kapkaev (timur.kap@aydex.ru)
 * @version $ID$
 * @since 07.11.2017
 * */
public class UserStore {

    /** экземпляр UserStore. */
    private static final UserStore INSTANCE = new UserStore();
    /** соединение с базой данных. */
    private final Connection dbConnection;

    /** имя таблицы. */
    private String table;

    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(UserStore.class);

    /**
     * Коструктор класса UserStore.
     * */
    private UserStore() {
        dbConnection = tryConnection();
    }


    /**
     * Возвращает экземпляр UserStore.
     * @return экземпляр UserStore.
     * */
    public static UserStore getInstance() {
        return INSTANCE;
    }

    /**
     * Содание соединения с базой данных.
     * @return соединение (null, если соединеие не установлено)
     * */
    private Connection tryConnection() {
        Connection con = null;
        try {
            Properties properties = new Properties();

            InputStream propertyFile = UserStore.class.getResource("/config.properties").openStream();
            properties.load(propertyFile);
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));

            table = properties.getProperty("table");
        } catch (IOException | SQLException | ClassNotFoundException e) {
            LOGGER.error(e);
        }
        return con;
    }

    /**
     * Возвращает пользователя по заданному логину.
     * @param login - логин
     * @return user (null, если поиск не дал результатов)
     * @throws SQLException - ошибка SQL запроса
     * */
    public User getUser(String login) throws SQLException {
        User user = null;
        try (PreparedStatement ps = dbConnection.prepareStatement(String.format("SELECT * FROM %s WHERE login = ?;", table))) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String lg = rs.getString("login");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    Date date = new Date(rs.getTimestamp("date").getTime());
                    user = new User(name, lg, email, date);
                }
            }

        }
        return user;
    }

    /**
     * Добавление пользователя.
     * @param user - пользователь
     * @return true, если пользователь был добавлен
     * @throws SQLException - ошибка SQL запроса
     * */
    public boolean insert(User user) throws SQLException {
        boolean result;
        try (PreparedStatement ps = dbConnection.prepareStatement(String.format("INSERT INTO %s (login, name, email) VALUES(?, ?, ?);", table))) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            try {
                ps.execute();
                result = true;
            } catch (SQLException e) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Обновление пользователя.
     * @param updatedUser - обновленныйй пользователь
     * @throws SQLException - ошибка SQL запроса
     * */

    public void update(User updatedUser) throws SQLException {
        try (PreparedStatement ps = dbConnection.prepareStatement(String.format("INSERT INTO %s (login, name, email) VALUES(?, ?, ?) ON CONFLICT (login) DO UPDATE SET name=EXCLUDED.name, email=EXCLUDED.email, date=NOW();", table))) {
            ps.setString(1, updatedUser.getLogin());
            ps.setString(2, updatedUser.getName());
            ps.setString(3, updatedUser.getEmail());
            ps.execute();
        }
    }

    /**
     * Удаление пользователя.
     * @param login - логин пользователя
     * @throws SQLException - ошибка SQL запроса
     * */
    public void delete(String login) throws SQLException {
        try (PreparedStatement ps = dbConnection.prepareStatement(String.format("DELETE FROM %s WHERE login=?;", table))) {
            ps.setString(1, login);
            ps.execute();
        }
    }
    /**
     * Закрывает соединение с базой данных.
     * @throws SQLException - ошибка закрытия соединения
     * */
    public void close() throws SQLException {
        if (dbConnection != null) {
            dbConnection.close();
        }
    }
}
