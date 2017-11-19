package ru.job4j.servletsform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class PrimaryKey описывает первичный ключ.
 * @author Timur Kapkaev (timur,kap@yandex.ru)
 * @version $ID$
 * @since 17.11.2017
 * */
public abstract class PrimaryKey {
    /**
     * Запрос поиска по первичному ключу.
     * @param connection - соединение с базой данных
     * @param table - таблица
     * @return запрос поиска по первичнному ключу
     * @throws SQLException - ошибка поиска.
     * */
    public abstract PreparedStatement searchQuery(Connection connection, String table) throws SQLException;
}
