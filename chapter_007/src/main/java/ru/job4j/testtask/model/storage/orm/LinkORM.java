package ru.job4j.testtask.model.storage.orm;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;


/**
 * Class LinkORM описывает связывание объектов в базе данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 29.12.2017
 * */
 class LinkORM {
    /** вспомогательные операции ORM. */
    private final AuxiliaryORM auxiliaryORM;

    /**
     * Конструктор класса LinkORM.
     * @param con - соединение с базой данных
     */
    LinkORM(Connection con) {
        this.auxiliaryORM = new AuxiliaryORM(con);
    }

    /**
     * Задает связь между элементом коллекции и значением указанного поля элемента коллекции.
     * @param elements - коллекция элементов
     * @param field - комплексное поле элемента коллекции
     * @throws ORMException - шибка выполнения рперации
     * */
    void setElementComplexFieldValueLink(Collection elements, Field field) throws ORMException {
        try {
            if (elements != null && elements.size() != 0) {

                /* образец элемента коллекции*/
                Object sample =  elements.iterator().next();
                /*имя класса элемента коллекции */
                String elementClassName = sample.getClass().getSimpleName().toLowerCase();
                /* имя поля элемента коллекции */
                String fieldName = field.getName().toLowerCase();
                 /*название таблицы*/
                String tableName = elementClassName + fieldName;
                 /*название ID аргумента элемента в таблице*/
                String elementIDTableArgument = elementClassName + "_id";
                /*id поле элемента коллекции*/
                Field idFieldElement = sample.getClass().getDeclaredField(AuxiliaryORM.ID);
                idFieldElement.setAccessible(true);

                if (Collection.class.isAssignableFrom(field.getType())) { //тип поля принадлежит к коллекции
                    /*аргумент типа коллекции, являющейся типом поля*/
                    Class argumentType = auxiliaryORM.getFieldArgumentType(field);
                    /*название ID аргумента значения поля в таблице*/
                    String fieldValueIDTableArgument = argumentType.getSimpleName().toLowerCase() + "_id";

                    StringBuilder sql = new StringBuilder(String.format("INSERT INTO %s (%s, %s) ", tableName, elementIDTableArgument, fieldValueIDTableArgument));
                    sql.append(String.format("VALUES (?, ?) ON CONFLICT (%s, %s) DO NOTHING", elementIDTableArgument, fieldValueIDTableArgument));
                    /*поле ID значения поля*/
                    Field idFieldOfFieldValue = argumentType.getDeclaredField(AuxiliaryORM.ID);

                    idFieldOfFieldValue.setAccessible(true);
                    try (PreparedStatement ps = auxiliaryORM.getConnection().prepareStatement(sql.toString())) {
                        for (Object element : elements) {
                            Collection fieldValues = (Collection) auxiliaryORM.getFieldValue(element, field);
                            for (Object fieldValue : fieldValues) {
                                auxiliaryORM.substituteValueToPreparedStatement(ps, 1, idFieldElement.get(element), idFieldElement.getType());
                                auxiliaryORM.substituteValueToPreparedStatement(ps, 2, idFieldOfFieldValue.get(fieldValue), idFieldOfFieldValue.getType());
                                ps.addBatch();
                            }
                        }
                        ps.executeBatch();
                    }
                } else { //тип поля принадлежит к одиночному объекту
                     /*название ID аргумента значения поля в таблице*/
                    String fieldValueIDTableArgument = field.getType().getSimpleName().toLowerCase() + "_id";
                    StringBuilder sql = new StringBuilder(String.format("INSERT INTO %s (%s, %s) ", tableName, elementIDTableArgument, fieldValueIDTableArgument));
                    sql.append(String.format("VALUES (?, ?) ON CONFLICT (%s, %s) DO NOTHING", elementIDTableArgument, fieldValueIDTableArgument));
                    /*поле ID значения поля*/
                    Field idFieldOfFieldValue = field.getType().getDeclaredField(AuxiliaryORM.ID);
                    idFieldOfFieldValue.setAccessible(true);

                    try (PreparedStatement ps = auxiliaryORM.getConnection().prepareStatement(sql.toString())) {
                        for (Object element : elements) {
                            Object fieldValue = auxiliaryORM.getFieldValue(element, field);
                            if (fieldValue != null) {
                                auxiliaryORM.substituteValueToPreparedStatement(ps, 1, idFieldElement.get(element), idFieldElement.getType());
                                auxiliaryORM.substituteValueToPreparedStatement(ps, 2, idFieldOfFieldValue.get(fieldValue), idFieldOfFieldValue.getType());
                                ps.addBatch();
                            }
                        }
                        ps.executeBatch();
                    }
                }
            }
        } catch (SQLException | NoSuchFieldException | IllegalAccessException | ClassNotFoundException e) {
            ORMException oe = new ORMException();
            oe.initCause(e);
            throw oe;

        }
    }

    /**
     * Разрушает связь между элементом коллекции и значением указанного поля элемента коллекции.
     * @param elements - коллекция элементов
     * @param field - комплексное поле элемента коллекции
     * @throws ORMException - шибка выполнения рперации
     * */
    void removeElementComplexFieldValueLink(Collection elements, Field field) throws ORMException {
        if (elements != null && elements.size() != 0) {

            /* образец элемента коллекции*/
            Object sample =  elements.iterator().next();
            /*имя класса элемента коллекции */
            String elementClassName = sample.getClass().getSimpleName().toLowerCase();
            /* имя поля элемента коллекции */
            String fieldName = field.getName().toLowerCase();
            /*название таблицы*/
            String tableName = elementClassName + fieldName;
            /*название ID аргумента элемента в таблице*/
            String elementIDTableArgument = elementClassName + "_id";

            String sql = String.format("DELETE FROM %s WHERE %s = ?", tableName, elementIDTableArgument);



            try (PreparedStatement ps = auxiliaryORM.getConnection().prepareStatement(sql)) {
                /*id поле элемента коллекции*/
                Field idFieldElement = sample.getClass().getDeclaredField(AuxiliaryORM.ID);
                for (Object element : elements) {
                    int elementId = (Integer) auxiliaryORM.getFieldValue(element, idFieldElement);
                    ps.setInt(1, elementId);
                    ps.addBatch();
                }
                ps.executeBatch();
            } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
                ORMException oe = new ORMException();
                oe.initCause(e);
                throw oe;
            }
        }
    }

    /**
     * Возвращает связь между элементом коллекции и значением указанного поля элемента коллекции.
     * @param elements - коллекция элементов
     * @param field - комплексное поле элемента коллекции
     * @return отображение id элемента - множество id значений поля, связанного с элементом
     * @throws ORMException - шибка выполнения рперации
     * */
    Map<Integer, Set<Integer>> getElementComplexFieldValueLink(Collection elements, Field field) throws ORMException {
        try {
            HashMap<Integer, Set<Integer>> elementComplexFieldValueLink = new HashMap<>();
            if (elements != null && elements.size() != 0) {
                /* образец элемента коллекции*/
                Object sample =  elements.iterator().next();
                /*множество значений id элементов коллекции*/
                Set<Integer> idOfElements = new HashSet<>();
                /*id поле элемента коллекции*/
                Field idFieldElement = sample.getClass().getDeclaredField(AuxiliaryORM.ID);

                for (Object element : elements) {
                    idOfElements.add((Integer) auxiliaryORM.getFieldValue(element, idFieldElement));
                }

                int size = idOfElements.size();

                if (size != 0) {
                    /*имя класса элемента коллекции */
                    String elementClassName = sample.getClass().getSimpleName().toLowerCase();
                    /* имя поля элемента коллекции */
                    String fieldName = field.getName().toLowerCase();
                    /*название таблицы*/
                    String tableName = elementClassName + fieldName;
                    /*название ID аргумента элемента в таблице*/
                    String elementIDTableArgument = elementClassName + "_id";

                    StringBuilder sqlBuilder = new StringBuilder();
                    sqlBuilder.append(String.format("SELECT * FROM %s WHERE %s IN (", tableName, elementIDTableArgument));
                    for (int i = 0; i < size; i++) {
                        if (i != 0) {
                            sqlBuilder.append(",?");
                        } else {
                            sqlBuilder.append("?");
                        }
                    }
                    sqlBuilder.append(");");

                    String sql = sqlBuilder.toString();
                    try (PreparedStatement ps = auxiliaryORM.getConnection().prepareStatement(sql)) {
                        int i = 0;
                        for (Integer id : idOfElements) {
                            auxiliaryORM.substituteValueToPreparedStatement(ps, ++i, id, Integer.class);
                        }

                        String fieldValueIDTableArgument;
                        if (Collection.class.isAssignableFrom(field.getType())) {
                            fieldValueIDTableArgument = auxiliaryORM.getFieldArgumentType(field).getSimpleName() + "_id";
                        } else {
                            fieldValueIDTableArgument = field.getType().getSimpleName() + "_id";
                        }
                        /* обработка результата запроса - извлечение id значений поля, связанных с соответствующим элементов коллекции*/
                        try (ResultSet rs = ps.executeQuery()) {
                            while (rs.next()) {
                                Integer idElement = rs.getInt(elementIDTableArgument);
                                Integer idFieldValue = rs.getInt(fieldValueIDTableArgument);
                                Set<Integer> fieldValuesID = elementComplexFieldValueLink.get(idElement);

                                if (fieldValuesID == null) {
                                    fieldValuesID = new HashSet<>();
                                    elementComplexFieldValueLink.put(idElement, fieldValuesID);
                                }
                                fieldValuesID.add(idFieldValue);
                            }
                        }
                    }
                }
            }
            return elementComplexFieldValueLink;
        } catch (SQLException | NoSuchFieldException | IllegalAccessException | ClassNotFoundException e) {
            ORMException oe = new ORMException();
            oe.initCause(e);
            throw oe;
        }

    }
}
