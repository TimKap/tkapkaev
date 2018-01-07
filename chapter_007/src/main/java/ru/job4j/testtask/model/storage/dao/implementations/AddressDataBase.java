package ru.job4j.testtask.model.storage.dao.implementations;

import org.apache.commons.dbcp.BasicDataSource;
import ru.job4j.testtask.model.entities.Address;
import ru.job4j.testtask.model.storage.dao.exception.OperationException;
import ru.job4j.testtask.model.storage.dao.interfaces.IAddressDAO;
import ru.job4j.testtask.model.storage.orm.ORMException;
import ru.job4j.testtask.model.storage.orm.SimplifyORM;
import ru.job4j.testtask.model.storage.transaction.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;


/**
 * Class  AddressDataBase описывает хранилище адресов в базе данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 13.12.2017
 * */
   class AddressDataBase extends EntityDataBase<Address> implements IAddressDAO {


    /**
     * Конструктор AddressDataBase.
     * @param connectionPool - пул соединений с базой данных
     * */
    AddressDataBase(BasicDataSource connectionPool) {
        super(connectionPool);
    }

    /**
     * Конструктор AddressDataBase.
     * @param connection - соединение с базой данных
     * */
    AddressDataBase(Connection connection) {
        super(connection);
    }

    /**
     * Возвращает список всех адресов.
     * @return список адресов
     * @throws OperationException - ошибка выполнения операции
     * */
    @Override
    public List<Address> getAllElements() throws OperationException {

        try (Connection con = getConnection()) {
            Transaction<Collection<Address>> getAllAddresses = new Transaction<Collection<Address>>(con) {
                @Override
                public Collection<Address> runStatements() throws SQLException {
                    SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                    try {
                        Set<Integer> addressesId = simplifyORM.getAllElementsId(Address.class);
                        HashMap<String, Set> matches = new HashMap<>();
                        matches.put("id", addressesId);
                        return simplifyORM.get(matches, Address.class);
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };
            getAllAddresses.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            ArrayList<Address> addresses = new ArrayList<>();
            addresses.addAll(getAllAddresses.execute());
            return addresses;
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }
    }

    /**
     * Возвращает адрес по ключу.
     * @param key - ключ (ключ адреса - его id)
     * @return адрес (null,сли адрес не найден)
     * @throws OperationException - ошибка оп ерации возвращения адреса
     * */
    @Override
    public Address get(String key) throws OperationException {
        try (Connection con = getConnection()) {
            Transaction<Address> getAddress = new Transaction<Address>(con) {
                @Override
                public Address runStatements() throws SQLException {
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        Set<Integer> addressId = new HashSet<>();
                        addressId.add(Integer.valueOf(key));
                        HashMap<String, Set> matches = new HashMap<>();
                        matches.put("id", addressId);
                        Collection<Address> addresses = simplifyORM.get(matches, Address.class);
                        Iterator<Address> iterator = addresses.iterator();
                        return iterator.hasNext() ? iterator.next() : null;
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };
            getAddress.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            return getAddress.execute();
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }
    }

    /**
     *  Добавляет адрес в базу данных.
     *  @param element - добавляемый адрес
     *  @return добавленный адрес
     *  @throws OperationException - ошибка выполнения операции
     * */
    @Override
    public Address add(Address element) throws OperationException {
        try (Connection con = getConnection()) {
            Transaction addAddress = new Transaction(con) {
                @Override
                public Object runStatements() throws SQLException {
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        ArrayList<Address> addresses = new ArrayList<>();
                        addresses.add(element);
                        simplifyORM.insert(addresses, Address.class);
                        return null;
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };
            addAddress.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            addAddress.execute();
            return element;
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }
    }

    /**
     *  Удаляет адрес из базы данных.
     *  @param element - удаляемый адрес
     *  @return удаленный адрес
     *  @throws OperationException - ошибка выполнения операции
     * */
    @Override
    public Address remove(Address element) throws OperationException {
        try (Connection con = getConnection()) {
            Transaction removeAddress = new Transaction(con) {
                @Override
                public Object runStatements() throws SQLException {
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        ArrayList<Address> addresses = new ArrayList<>();
                        addresses.add(element);
                        simplifyORM.remove(addresses, Address.class);
                        return null;
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };
            removeAddress.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            removeAddress.execute();
            return element;
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }
    }

    /**
     * Обновляет адре в базе данных.
     * @param modification модифицированный адрес.
     * @return модифицированный адрес.
     * @throws OperationException - ошибка выполнения операции
     * */
    @Override
    public Address update(Address modification) throws OperationException {
        try (Connection con = getConnection()) {
            Transaction updateAddress = new Transaction(con) {
                @Override
                public Object runStatements() throws SQLException {
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        ArrayList<Address> addresses = new ArrayList<>();
                        addresses.add(modification);
                        simplifyORM.update(addresses, Address.class);
                        return null;
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };
            updateAddress.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            updateAddress.execute();
            return modification;
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }
    }

    /**
     * Очищает таблицу адресов.
     * @throws SQLException - ошибка операции
     * */
    void removeAll() throws SQLException {
        try (Connection con = getConnection();
            Statement st = con.createStatement()) {
            st.execute("DELETE FROM addresses");
        }
    }

}
