package ru.job4j.testtask.model.storage.orm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;


/**
 * Class GetAtomicORM описывает операцию возвращения объекта с атомарными полями из базы данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 28.12.2017
 * */
class GetAtomicORM {

    /** вспомогательные операции ORM. */
    private final AuxiliaryORM auxiliaryORM;

    /**
     * Конструктор класса GetAtomicORM.
     * @param con - соединение с базой данных
     */
    GetAtomicORM(Connection con) {
        this.auxiliaryORM = new AuxiliaryORM(con);
    }

    /**
     * Возвращает коллекцмию элементов с атомарными полями, значения полей которрых удовлетворяют заданным.
     * @param fieldsMatches - название полей и их значения, которым должны удовлетворять искомые элементы
     * @param elementClass - класс возвращаемых объектов
     * @return коллекцмию элементов с атомарными полями
     * @throws ORMException - ошибка выполнения поиска
     * @param <E> - тип элемента коллекции
     */
    <E> Collection<E> execute(Map<String, Set> fieldsMatches, Class<E> elementClass) throws ORMException {
        try {
            /*имя таблицы, содержащей объекты */
            StringBuilder tableName = new StringBuilder(elementClass.getSimpleName().toLowerCase());
            if (tableName.lastIndexOf("s") == tableName.length() - 1) {
                tableName.append("es");
            } else {
                tableName.append("s");
            }

            /* sql запрос на поиск элементов в таблице */
            String sql = createModifiedGetRequest(tableName.toString(), fieldsMatches);
            /* найденные элементы*/
            ArrayList<E> elements = new ArrayList<>();

            if (!sql.equals("")) { // подготовлен корректный запрос

                try (PreparedStatement ps = auxiliaryORM.getConnection().prepareStatement(sql)) {
                    int i = 0;
                    for (String fieldName : fieldsMatches.keySet()) {
                        for (Object fieldValue : fieldsMatches.get(fieldName)) {
                            auxiliaryORM.substituteValueToPreparedStatement(ps, ++i, fieldValue, fieldValue.getClass());
                        }
                    }
                    /*извлечение элементов из результата запроса*/
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            /* сформировать элемент */
                            Constructor<E> elementConstructor = elementClass.getDeclaredConstructor();
                            elementConstructor.setAccessible(true);
                            E element = elementConstructor.newInstance();

                            /* задать значения атомарных полей элемента*/
                            Field[] atomicFields = auxiliaryORM.getEffectiveAtomicFields(elementClass);
                            for (Field field : atomicFields) {
                                field.setAccessible(true);
                                field.set(element, auxiliaryORM.extractFieldValueFromResultSet(rs, field));
                            }
                            /* id поле элемента*/
                            Field idField = auxiliaryORM.getField(element, AuxiliaryORM.ID);
                            idField.setAccessible(true);
                            idField.set(element, auxiliaryORM.extractFieldValueFromResultSet(rs, idField));
                            /*добавить сформированный элемент в возвращаемый список элементов*/
                            elements.add(element);
                        }
                    }
                }
            }
            return elements;
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException  | NoSuchFieldException e) {
            ORMException oe = new ORMException();
            oe.initCause(e);
            throw oe;
        }



    }

    /**
     * Формирование запроса для поиска элементов.
     * @param tableName - имя таблицы базы данных
     * @param fieldsMatches - название полей и их значения, которым должны удовлетворять искомые элементы
     * @return запроса для поиска элементов ("", если запрос не содержит параметров поиска)
     */
    private String createModifiedGetRequest(String tableName, Map<String, Set> fieldsMatches) {
        StringBuilder searchList = new StringBuilder();
        boolean first = true;
        for (String str : fieldsMatches.keySet()) {
            int size = fieldsMatches.get(str).size();
            if (size != 0) {
                if (!first) {
                    searchList.append(" AND " + str.toLowerCase() + " IN (");
                } else {
                    first = false;
                    searchList.append(str.toLowerCase() + " IN (");
                }
                for (int i = 0; i < size; i++) {
                    if (i != 0) {
                        searchList.append(",?");
                    } else {
                        searchList.append("?");
                    }
                }
                searchList.append(")");
            }
        }
        return searchList.toString().equals("") ? "" : String.format("SELECT * FROM %s WHERE %s FOR UPDATE;", tableName, searchList.toString());
    }
}


