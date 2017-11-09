package ru.job4j.servletsform;


import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.firstservlets.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Date;

/**
 * Class AdvancedUserStore описывает хранилище пользователей.
 * @author Timur Kapkaev (tikmur.kap@yandex.ru)
 * @version $ID$
 * @since 09.11.2017
 * */
public class AdvancedUserStore {

    /** экземпляр AdvancedUserStore. */
    private static final AdvancedUserStore instance = new AdvancedUserStore();

    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(AdvancedUserStore.class);

    /** пул соединений. */
    private static BasicDataSource connectionPool;

    /** таблица пользователей. */
    private static String table;


    /**
     * Конструктор класса AdvancedUserStore.
     * */
    private AdvancedUserStore() {
        try {
            connectionPool = createConnectionPool();
            table = extractTableName();
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    /**
     * Возвращает экземпляр AdvancedUserStore.
     * @return экземпляр AdvancedUserStore
     * */
    public static AdvancedUserStore getInstance() {
        return instance;
    }

    /**
     * Возвращает пул соединений.
     * @return возвращает пул соединений.
     * @throws IOException - ошибка конфигурационного файла.
     * */
    private BasicDataSource createConnectionPool() throws IOException {
        InputStream propertyFile = AdvancedUserStore.class.getResourceAsStream("/config.properties");
        Properties properties = new Properties();
        properties.load(propertyFile);
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        BasicDataSource pool = new BasicDataSource();
        pool.setDriverClassName("org.postgresql.Driver");
        pool.setUrl(url);
        pool.setUsername(user);
        pool.setPassword(password);
        return pool;
    }

    /**
     * Извлекает имя таблицы пользователей из конфигурационного файла.
     * @return имя таблицы пользователей
     * @throws IOException - ошибка конфигурационного файла
     * */
    private String extractTableName() throws IOException {
        InputStream propertyFile = AdvancedUserStore.class.getResourceAsStream("/config.properties");
        Properties properties = new Properties();
        properties.load(propertyFile);
        return properties.getProperty("table");
    }

    /**
     * Возвращает соединение с базой данных.
     * @return соединение с базой данных.
     * @throws SQLException - ошибка соединения
     * */
    private Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    /**
     * Возвращает всех пользователей из базы данных.
     * @return пользователи базы данных.
     * */
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try ( Connection con = getConnection();
              Statement st = con.createStatement();
              ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s;", table))) {
            while (rs.next()) {
                String login = rs.getString("login");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date date = new Date(rs.getTimestamp("date").getTime());
                users.add(new User(name, login, email, date));
            }
        }
        return users;
    }

    /**
     * Добавление пользователя.
     * @param user - пользователь
     * @return true, если пользователь был добавлен
     * @throws SQLException - ошибка SQL запроса
     * */
    public boolean insert(User user) throws SQLException {
        boolean result;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(String.format("INSERT INTO %s (login, name, email) VALUES(?, ?, ?);", table))) {
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
        try (Connection con =  getConnection();
             PreparedStatement ps =con.prepareStatement(String.format("INSERT INTO %s (login, name, email) VALUES(?, ?, ?) ON CONFLICT (login) DO UPDATE SET name=EXCLUDED.name, email=EXCLUDED.email, date=NOW();", table))) {
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
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(String.format("DELETE FROM %s WHERE login=?;", table))) {
            ps.setString(1, login);
            ps.execute();
        }
    }
 }
