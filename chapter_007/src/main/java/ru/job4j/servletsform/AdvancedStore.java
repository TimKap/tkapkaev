package ru.job4j.servletsform;


import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


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


/**
 * Class AdvancedUserStore описывает хранилище пользователей.
 * @author Timur Kapkaev (tikmur.kap@yandex.ru)
 * @version $ID$
 * @since 09.11.2017
 * @param <E> - тип объекта с которым работает база данных
 * */
public abstract class AdvancedStore<E extends ICRUDQueries> {

    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(AdvancedStore.class);

    /** пул соединений. */
    private  BasicDataSource connectionPool;

    /** таблица пользователей. */
    private String table;

    /** Преобразователь результата запроса в объект. */
    private final IResultSetConverter<E> converter;



    /**
     * Конструктор класса AdvancedUserStore.
     * @param converter - преобразователь результата запроса в объект.
     * */
    public AdvancedStore(IResultSetConverter<E> converter) {
        this.converter = converter;
        try {
            this.connectionPool = createConnectionPool();
            this.table = extractTableName();
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    /**
     * Возвращает пул соединений.
     * @return возвращает пул соединений.
     * @throws IOException - ошибка конфигурационного файла.
     * */
    private BasicDataSource createConnectionPool() throws IOException {
        InputStream propertyFile = AdvancedStore.class.getResourceAsStream("/config.properties");
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
    protected abstract String extractTableName() throws IOException;
    /**
     * Возвращает соединение с базой данных.
     * @return соединение с базой данных.
     * @throws SQLException - ошибка соединения
     * */
    protected Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    /**
     * Возвращает всех пользователей из базы данных.
     * @return пользователи базы данных.
     * @throws SQLException - ошибка запроса
     * */
    public List<E> getAllUsers() throws SQLException {
        List<E> users = new ArrayList<>();
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s;", table))) {
            while (rs.next()) {
                users.add(converter.apply(rs));
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
    public boolean insert(E user) throws SQLException {
        boolean result;
        try (Connection con = getConnection();
             PreparedStatement ps = user.insert(con, table)) {
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
     * @param user - обновленныйй пользователь
     * @throws SQLException - ошибка SQL запроса
     * */

    public void update(E user) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement ps = user.update(con, table)) {
             ps.execute();
        }
    }

    /**
     * Удаление пользователя.
     * @param user - удаляемый пользователь
     * @throws SQLException - ошибка SQL запроса
     * */
    public void delete(E user) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement ps = user.delete(con, table)) {
            ps.execute();
        }
    }

    /**
     * Ищет пользователя по первичному ключу.
     * @param primaryKey - первичный ключ.
     * @return объект, соответствующий первичному ключу (null - если объект не найден)
     * @throws SQLException - ошибка поиска пользователя по первичному ключу
     * */
    public E searchByPrimaryKey(PrimaryKey primaryKey) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement ps = primaryKey.searchQuery(connection, table);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? converter.apply(rs) : null;
        }
    }
}

