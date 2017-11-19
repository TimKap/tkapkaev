package ru.job4j.servletsform;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Интерфейс обеспечивает преобразование ResultSet в объект.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 15.11.2017
 * @param <R> - тип возвращаемого объекта.
 * */

public interface IResultSetConverter<R> {
    /**
     * Преобразование ResultSet в объект.
     * @param resultSet - результат запроса
     * @return объект
     * @throws SQLException - ошибка преобразования ResultSet в объект
     * */
    R apply(ResultSet resultSet) throws SQLException;
}
