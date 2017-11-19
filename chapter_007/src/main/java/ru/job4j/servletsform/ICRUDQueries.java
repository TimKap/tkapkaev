package ru.job4j.servletsform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Интерфейс ICRUDQueries описывает формирование запросов основных операций работы с БД.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 16.11.2017
 * */
public interface ICRUDQueries {
    /**
     * Формирование запроса на добавление пользователя.
     * @param connection - соединение
     * @param table - таблица
     * @return запрос на добавление пользователя
     * @throws SQLException - ошибка операции вставки
     * */
    PreparedStatement insert(Connection connection, String table) throws SQLException;

    /**
     * Формирование запроса на удаление пользователя.
     * @param connection - соединение
     * @param table - таблица
     * @return запрос на добавление пользователя
     * @throws SQLException - ошибка операции удаления
     * */
    PreparedStatement delete(Connection connection, String table) throws SQLException;

    /**
     * Формирование запроса на добавление пользователя.
     * @param connection - соединение
     * @param table - таблица
     * @return запрос на добавление пользователя
     * @throws SQLException - ошибка операции обновления
     * */
    PreparedStatement update(Connection connection, String table) throws SQLException;
}