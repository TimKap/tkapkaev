package ru.job4j.testtask.model.storage.dao.implementations;

import org.apache.commons.dbcp.BasicDataSource;

import ru.job4j.testtask.model.entities.Role;
import ru.job4j.testtask.model.storage.dao.exception.OperationException;
import ru.job4j.testtask.model.storage.dao.interfaces.IRoleDAO;
import ru.job4j.testtask.model.storage.orm.ORMException;
import ru.job4j.testtask.model.storage.orm.SimplifyORM;
import ru.job4j.testtask.model.storage.transaction.Transaction;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
/**
 * Class RoleDataBase описывает хранилище ролей в базе данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 13.12.2017
 * */
 class RoleDataBase extends EntityDataBase<Role> implements IRoleDAO {
    /**
     * Конструктор RoleDatabase.
     * @param connectionPool - пул соединений с базой данных
     * */
    RoleDataBase(BasicDataSource connectionPool) {
        super(connectionPool);
    }

    /**
     * Конструктор RoleDataBase.
     * @param connection - соединение с базой данных
     * */
    RoleDataBase(Connection connection) {
        super(connection);
    }



    /**
     * Возвращает список всех ролей.
     * @return список ролей
     * @throws OperationException - ошибка выполнения операции
     * */
    @Override
    public List<Role> getAllElements() throws OperationException {
        ArrayList<Role> roles = new ArrayList<>();
        try (Connection con = getConnection()) {
            Transaction<Collection<Role>> getAllRoles = new Transaction<Collection<Role>>(con) {
                @Override
                public Collection<Role> runStatements() throws SQLException {
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        Set<Integer> rolesId = simplifyORM.getAllElementsId(Role.class);
                        HashMap<String, Set> matches = new HashMap<>();
                        matches.put("id", rolesId);
                        return  simplifyORM.get(matches, Role.class);
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };
            getAllRoles.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            roles.addAll(getAllRoles.execute());
            return roles;
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }
    }

    /**
     * Возвращает роль по ключу.
     * @param key - ключ (ключ роли - ее название)
     * @return роль (null, если роль не найдена)
     * @throws OperationException - ошибка оп ерации возвращения адреса
     * */
    @Override
    public Role get(String key) throws OperationException {
        try (Connection con = getConnection()) {
            Transaction<Role> getRole = new Transaction<Role>(con) {
                @Override
                public Role runStatements() throws SQLException {
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        HashSet<Integer> rolesId =  new HashSet<>();
                        rolesId.add(Integer.valueOf(key));
                        HashMap<String, Set> matches = new HashMap<>();
                        matches.put("id", rolesId);
                        Iterator<Role> iterator =  simplifyORM.get(matches, Role.class).iterator();
                        return iterator.hasNext() ? iterator.next() : null;
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }

                }
            };
            getRole.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            return getRole.execute();
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }
    }

    /**
     *  Добавляет роль в базу данных.
     *  @param element - добавляемая роль
     *  @return добавленная роль
     *  @throws OperationException - ошибка выполнения операции
     * */
    @Override
    public Role add(Role element) throws OperationException {
        try (Connection con = getConnection()) {
            Transaction addRole = new Transaction(con) {
                @Override
                public Object runStatements() throws SQLException {
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        ArrayList<Role> roles = new ArrayList<>();
                        roles.add(element);
                        simplifyORM.insert(roles, Role.class);
                        return null;
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };

            addRole.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            addRole.execute();
            return element;
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }
    }

    /**
     *  Удаляет роль из базы данных.
     *  @param element - удаляемая роль
     *  @return удаленный роль
     *  @throws OperationException - ошибка выполнения операции
     * */
    @Override
    public Role remove(Role element) throws OperationException {
        try (Connection con = getConnection()) {
            Transaction removeRole = new Transaction(con) {
                @Override
                public Object runStatements() throws SQLException {
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        ArrayList<Role> roles = new ArrayList<>();
                        roles.add(element);
                        simplifyORM.remove(roles, Role.class);
                        return null;
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };
            removeRole.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            removeRole.execute();
            return element;
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }
    }

    /**
     * Update role with specific id.
     * @param modification модифицированный адрес.
     * @return обновленную роль
     * @throws OperationException - ошибка выполнения операции
     * */
    @Override
    public Role update(Role modification) throws OperationException {
        try (Connection con = getConnection()) {
            Transaction updateRole = new Transaction(con) {
                @Override
                public Object runStatements() throws SQLException {
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        ArrayList<Role> roles = new ArrayList<>();
                        roles.add(modification);
                        simplifyORM.update(roles, Role.class);
                        return null;
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };
            updateRole.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            updateRole.execute();
            return modification;
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }
    }

    /**
     * Clear table with roles.
     * @throws SQLException - ошибка операции
     * */
    void removeAll() throws SQLException {
        try (Connection con = getConnection();
             Statement st = con.createStatement()) {
            st.execute("DELETE FROM roles");
        }
    }
}
