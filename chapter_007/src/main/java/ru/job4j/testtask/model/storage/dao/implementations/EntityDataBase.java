package ru.job4j.testtask.model.storage.dao.implementations;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class EntityDataBase описывает работу с сущностью, хранимой в базе данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 13.12.2017
 * @param <E> - тип сущности базы данных
 * */
 abstract class EntityDataBase<E> {
    /** пул соединений с базой данных. */
    private final BasicDataSource connectionPool;

    /** соединение с базой данных. */
    private final  Connection connection;

    /**
     * Конструктор EntityDataBase.
     * @param connectionPool - пул соедимнений с базой данных.
     * */
    EntityDataBase(BasicDataSource connectionPool) {
        this.connectionPool = connectionPool;
        this.connection = null;
    }

    /**
     * Конструктор EntityDataBase.
     * @param connection - cоедимнение с базой данных.
     * */
    EntityDataBase(Connection connection) {
        this.connectionPool = null;
        this.connection = connection;
    }

    /**
     * Возвращает соединение с базой данных.
     * @return соединение с базой данных
     * @throws SQLException - ошибка получения соединения с базой данных.
     * */

    Connection getConnection() throws SQLException {
        return connectionPool != null ? connectionPool.getConnection() : connection;
    }
}
