package ru.job4j.ioc.storage;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import ru.job4j.ioc.model.User;


/**
 * Class JdbcStorage описывает хранилище эелементов в базе данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 08.02.2018

 * */
public class JdbcStorage implements IStorage<User> {
    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(JdbcStorage.class);
    /** пул соединений с базой данных. */
    private final BasicDataSource connectionPool;
    /**
     * Конструктор JdbcStorage.
     * @param connectionPool - пул соединений с базой данных.
     * */
    public JdbcStorage(BasicDataSource connectionPool) {
        this.connectionPool = connectionPool;
    }
    /**
     * Возвращает соединение с базой данных.
     * @return соединение с базой данных
     * @throws SQLException - ошибка возвращения соединения с базой данных.
     * */
    private Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }
    /**
     * Сохраняет элемент.
     * @param element - сохраняемый элемент
     * @return сохраненный элемент (null, если не удалось выполнить сохранение)
     * */
    @Override
    public User persist(User element) {
        User result = null;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO users (login, surname, password) VALUES(?, ?, ?);", new String[]{"id"})) {
            ps.setString(1, element.getLogin());
            ps.setString(2, element.getSurname());
            ps.setString(3, element.getPassword());
            ps.execute();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    element.setId(rs.getLong(1));
                }
            }
            result = element;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return result;
    }
    /**
     * Возвращает элемент из хранилища.
     * @param id - id возвращаемого элемента
     * @return элемент хранилища (null, если элемента не существует)
     * */
    @Override
    public User get(long id) {
        User result = null;
        try (Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT FROM users WHERE id = ?;")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = new User(rs.getString("login"), rs.getString("surname"), rs.getString("password"));
                    result.setId(rs.getLong("id"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return result;
    }
    /**
     * Удаляет элемент из хранилища.
     * @param element - удаляемый элемент
     * @return удаленный элемент (null, при неудачном удалении)
     * */
    @Override
   public User remove(User element) {
        User result = null;
        try (Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id = ?;")) {
            ps.setLong(1, element.getId());
            ps.execute();
            result = element;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return result;
    }
    /**
     * Обновляет элемент.
     * @param element - обновленный элемент
     * @return обновленный элемент (null, при неудачном обновлении)
     * */
    @Override
    public User update(User element) {
        User result = null;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE users SET login = ?, surname = ?, password = ? WHERE id =?;")) {
            ps.setString(1, element.getLogin());
            ps.setString(2, element.getSurname());
            ps.setString(3, element.getPassword());
            ps.setLong(4, element.getId());
            ps.execute();
            result = element;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return result;
    }
}
