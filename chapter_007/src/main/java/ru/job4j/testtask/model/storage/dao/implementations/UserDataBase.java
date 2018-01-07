package ru.job4j.testtask.model.storage.dao.implementations;

import org.apache.commons.dbcp.BasicDataSource;

import ru.job4j.testtask.model.entities.Address;
import ru.job4j.testtask.model.entities.MusicStyle;
import ru.job4j.testtask.model.entities.Role;
import ru.job4j.testtask.model.entities.User;
import ru.job4j.testtask.model.storage.dao.exception.OperationException;
import ru.job4j.testtask.model.storage.dao.interfaces.IUserDAO;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import ru.job4j.testtask.model.storage.orm.ORMException;
import ru.job4j.testtask.model.storage.orm.SimplifyORM;
import ru.job4j.testtask.model.storage.transaction.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.Set;
import java.util.Collection;
import java.util.List;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;


/**
 * Class UserDataBase описывает хранилище пользователей в базе данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 13.12.2017
 * */
 class UserDataBase extends EntityDataBase<User> implements IUserDAO {

    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(UserDataBase.class);

    /**
     * Конструктор UserDataBase.
     * @param connectionPool - пул соединений
     * */
    UserDataBase(BasicDataSource connectionPool) {
        super(connectionPool);
    }

    /**
     * Возвращает из хранилища список всех пользователей.
     * @return список пользователей
     * @throws OperationException - ошибка выполнения операции
     * */
    @Override
    public List<User> getAllElements() throws OperationException {

        try (Connection con = getConnection()) {
            Transaction<Collection<User>> getAllUsers = new Transaction<Collection<User>>(con) {
                @Override
                public Collection<User> runStatements() throws SQLException {
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        Set<Integer> usersID = simplifyORM.getAllElementsId(User.class);
                        HashMap<String, Set> matches = new HashMap<>();
                        matches.put("id", usersID);
                        return simplifyORM.get(matches, User.class);
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };
            getAllUsers.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            ArrayList<User> users = new ArrayList<>();
            users.addAll(getAllUsers.execute());
            return users;
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }
    }

    /**
     * Возвращает пользователя по ключу.
     * @param key - (key - id пользователя)
     * @return пользователя (null, если пользователь не обнаружен).
     * @throws OperationException - ошибка выполнения операции
     */
    @Override
    public User get(String key) throws OperationException {

        try (Connection con = getConnection()) {
            Transaction<User> getUser = new Transaction<User>(con) {
                @Override
                public User runStatements() throws SQLException {
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        HashSet<Integer> usersId = new HashSet<>();
                        usersId.add(Integer.valueOf(key));
                        HashMap<String, Set> matches = new HashMap<>();
                        matches.put("id", usersId);
                        Iterator<User> iterator = simplifyORM.get(matches, User.class).iterator();
                        return iterator.hasNext() ? iterator.next() : null;
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };
            getUser.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            return getUser.execute();
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }
    }


    /**
     * Добавляет элемент в хранилище.
     * @param element - добавляемый в хранилище элемент
     * @return добавленный элемнт.
     * @throws OperationException - ошибка выполнения операции
     * */
    @Override
    public User add(User element) throws OperationException {
        try (Connection con = getConnection()) {

            Transaction addUser = new Transaction(con) {
                @Override
                public Object runStatements() throws SQLException {
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        ArrayList<User> users = new ArrayList<>();
                        users.add(element);
                        simplifyORM.insert(users, User.class);
                        return null;
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };
            addUser.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            addUser.execute();
            return element;
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }

    }

    /**
     * Удаляет элемент из хранилища.
     * @param element - удаляемый элемент
     * @return удаленный элемент
     * @throws OperationException - ошибка выполнения операции
     * */
    @Override
    public User remove(User element) throws OperationException {
        try (Connection con = getConnection()) {
            Transaction removeUser = new Transaction(con) {
                @Override
                public Object runStatements() throws SQLException {
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        ArrayList<User> users = new ArrayList<>();
                        users.add(element);
                        simplifyORM.remove(users, User.class);
                        return null;
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };
            removeUser.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            removeUser.execute();
            return element;
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }
    }

    /**
     * Ыносит обновление в элемент хранилища.
     * @param modification - обновление
     * @return обновленный элемент
     * @throws OperationException - ошибка операции.
     * */
    @Override
    public User update(User modification) throws OperationException {
        try (Connection con = getConnection()) {
            Transaction updateUser = new Transaction(con) {
                @Override
                public Object runStatements() throws SQLException {
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        ArrayList<User> users = new ArrayList<>();
                        users.add(modification);
                        simplifyORM.update(users, User.class);
                        return null;
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };
            updateUser.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            updateUser.execute();
            return modification;
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }
    }

    /**
     * Очищает пользователей.
     * @throws SQLException - ошибка операции
     * */
    void removeAll() throws SQLException {
        try (Connection con = getConnection();
             Statement st = con.createStatement()) {
            st.execute("DELETE FROM users");
        }
    }
    /**
     * Проверка регистрации пользователя.
     * @param login - логин
     * @param password  - пароль
     * @return true, елси пользователь зарегистрирован.
     * */
    @Override
    public User isCredential(String login, String password) throws OperationException {
        try (Connection con = getConnection()) {
            Transaction<User> validateUser = new Transaction<User>(con) {
                @Override
                public User runStatements() throws SQLException {
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        HashMap<String, Set> matches = new HashMap<>();
                        Set<String> logins = new HashSet<>();
                        Set<String> passwords = new HashSet<>();
                        logins.add(login);
                        passwords.add(password);
                        matches.put("name", logins);
                        matches.put("password", passwords);
                        Iterator<User> iterator = simplifyORM.get(matches, User.class).iterator();
                        return iterator.hasNext() ? iterator.next() : null;
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };

            validateUser.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            return validateUser.execute();
        } catch (SQLException e) {
            OperationException oe = new OperationException();
            oe.initCause(e);
            throw oe;
        }

    }
    /**
     * Возвращает пользователей по адресу.
     * @param address - адрес пользователя
     * @return пользователия с искомым адресом
     * @throws OperationException - ошибка выполнения операции
     * */
    @Override
    public Collection<User> getByAddress(Address address) throws OperationException {
        try (Connection con = getConnection()) {
            Transaction<Collection<User>> getByAddress = new Transaction<Collection<User>>(con) {
                @Override
                public Collection<User> runStatements() throws SQLException {

                    StringBuilder sql = new StringBuilder("SELECT user_id FROM useraddress JOIN addresses ON (address_id = addresses.id) WHERE ");
                    boolean first = true;
                    String country = address.getCountry();
                    String city = address.getCity();
                    String street = address.getStreet();
                    if (country != null || !country.equals("")) {
                        first = false;
                        sql.append("country = ? ");
                    }
                    if (city != null && !city.equals("")) {
                        if (first) {
                            first = false;
                            sql.append("city = ? ");
                        } else {
                            sql.append("AND city = ? ");
                        }
                    }

                    if (street != null && !street.equals("")) {
                        if (first) {
                            sql.append("street = ? ");
                        } else {
                            sql.append("AND street = ? ");
                        }
                    }
                    sql.append(";");
                    Set<Integer> usersId = new HashSet<>();
                    try (PreparedStatement ps = getTransactionConnection().prepareStatement(sql.toString())) {
                        int i = 0;
                        if (country != null && !country.equals("")) {
                            ps.setString(++i, country);
                        }
                        if (city != null && !city.equals("")) {
                            ps.setString(++i, city);
                        }
                        if (street != null && !street.equals("")) {
                            ps.setString(++i, street);
                        }
                        try (ResultSet rs = ps.executeQuery()) {
                            while (rs.next()) {
                                usersId.add(rs.getInt("user_id"));
                            }
                        }
                    }
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        HashMap<String, Set> matches = new HashMap<>();
                        matches.put("id", usersId);
                        return simplifyORM.get(matches, User.class);

                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };
            getByAddress.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            return getByAddress.execute();
        } catch (SQLException e) {
            OperationException oe = new OperationException();
            oe.initCause(e);
            throw oe;
        }
    }

    /**
     * Возвращает пользователей по роли.
     * @param role - роль
     * @return пользователей с заданной ролью.
     * @throws OperationException - ошибка выполнения операции
     * */
    @Override
    public Collection<User> getByRole(Role role) throws OperationException {
        try (Connection con = getConnection()) {
            Transaction<Collection<User>> getByRole = new Transaction<Collection<User>>(con) {
                @Override
                public Collection<User> runStatements() throws SQLException {
                    String sql = "SELECT user_id FROM userrole JOIN roles ON(role_id = roles.id) WHERE role = ?;";
                    HashSet<Integer> usersId = new HashSet<>();
                    try (PreparedStatement ps = getTransactionConnection().prepareStatement(sql)) {
                        ps.setString(1, role.getRole());
                        try (ResultSet rs = ps.executeQuery()) {
                            while (rs.next()) {
                                usersId.add(rs.getInt("user_id"));
                            }
                        }
                    }
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        HashMap<String, Set> matches = new HashMap<>();
                        matches.put("id", usersId);
                        return simplifyORM.get(matches, User.class);
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };
            getByRole.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            return getByRole.execute();
        } catch (SQLException e) {
            OperationException oe = new OperationException();
            oe.initCause(e);
            throw oe;
        }
    }
    /**
     * Возвращает пользователей с заданными музыкальными предпочтениями.
     * @param musicPreferences - музыкальны предпочтения пользователей
     * */
    @Override
    public Collection<User> getByMusicPreferences(Collection<MusicStyle> musicPreferences) throws OperationException {
        try (Connection con = getConnection()) {
            Transaction<Collection<User>> getByMusicPreferences = new Transaction<Collection<User>>(con) {
                @Override
                public Collection<User> runStatements() throws SQLException {
                    StringBuilder sql = new StringBuilder("SELECT user_id, count(style) FROM usermusicpreferences JOIN musicstyles ON (musicstyle_id = musicstyles.id) WHERE style IN ");
                    boolean first = true;
                    for (MusicStyle musciPreference : musicPreferences) {
                        if (first) {
                            first = false;
                            sql.append("(? ");
                        } else {
                            sql.append(", ? ");
                        }
                    }
                    sql.append(") GROUP BY user_id HAVING count(style)=?;");
                    HashSet<Integer> usersId = new HashSet<>();
                    try (PreparedStatement ps = getTransactionConnection().prepareStatement(sql.toString())) {
                        int i = 0;
                        for (MusicStyle musicPreference : musicPreferences) {
                            ps.setString(++i, musicPreference.getStyle());
                        }
                        ps.setInt(++i, musicPreferences.size());
                        try (ResultSet rs = ps.executeQuery()) {
                            while (rs.next()) {
                                usersId.add(rs.getInt("user_id"));
                            }
                        }
                    }
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        HashMap<String, Set> matches = new HashMap<>();
                        matches.put("id", usersId);
                        return simplifyORM.get(matches, User.class);
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }

                }
            };

            getByMusicPreferences.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            return getByMusicPreferences.execute();
        } catch (SQLException e) {
            OperationException oe = new OperationException();
            oe.initCause(e);
            throw oe;
        }
    }

}
