package ru.job4j.testtask.model.storage.orm;

import java.lang.reflect.Field;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;

import java.util.HashSet;


/**
 * Class SimplifyORM represent simplified ORM.
 * @author Timur Kapkaev(timur.kap@yandex.ru)
 * @version $ID$
 * @since 27.12.2017
 * */
public class SimplifyORM {
    /** database connection.*/
    private final Connection con;

    /** операция добавления ORM.*/
    private final InsertAtomicORM insertAtomicORM;
    /** операция обновления ORM.*/
    private final UpdateAtomicORM updateAtomicORM;
    /** операция возвращения ORM. */
    private final GetAtomicORM getAtomicORM;
    /** операци удаления ORM.*/
    private final RemoveORM removeORM;
    /** операция установления связи между объектами. */
    private final LinkORM linkORM;
    /** вспомогательные операции ORM. */
    private final AuxiliaryORM auxiliaryORM;

    /**
     * SimplifyORM class constructor.
     * @param con - connection with database.
     * */
    public SimplifyORM(Connection con) {
        this.con = con;
        this.insertAtomicORM = new InsertAtomicORM(con);
        this.updateAtomicORM = new UpdateAtomicORM(con);
        this.getAtomicORM = new GetAtomicORM(con);
        this.linkORM = new LinkORM(con);
        this.auxiliaryORM = new AuxiliaryORM(con);
        this.removeORM = new RemoveORM(con);
    }


    /**
     * Добавление коллекции элементов в базу данных.
     * (элементами коллекции могут быть только одиночные объекты)
     * @param elements - коллекция добавляемых элементов
     * @param elementClass - тип элемента коллеции
     * @throws ORMException - ошибка выполнения операции добавления объекта
     * @param <E> - тип элемента добавляемой коллекции
     * */
    public <E> void insert(Collection<E> elements, Class<E> elementClass) throws ORMException {
        try {
            if (elements != null && elements.size() != 0) {
                /* добавление атомарных полей элементов коллекции*/
                insertAtomicORM.execute(elements, elementClass);
                /* добавление комплексных полей*/
                /* комплексные поля элемента коллекции*/
                Field[] complexFields = auxiliaryORM.getComplexFields(elementClass);

                /* формирование коллекций значений полей элементов коллекции и их добавление в базу данных*/
                for (Field complexField : complexFields) {
                    Collection fieldsValues = auxiliaryORM.getValuesFieldsOfElements(elements, complexField);
                    if (Collection.class.isAssignableFrom(complexField.getType())) {
                        insert(fieldsValues, auxiliaryORM.getFieldArgumentType(complexField));
                    } else {
                        insert(fieldsValues, complexField.getType());
                    }
                }
                /* формирование связи в базе данных между элементом коллекции и значением комплексного поля элемента коллекции*/
                for (Field complexField : complexFields) {
                    linkORM.setElementComplexFieldValueLink(elements, complexField);
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException e) {
            ORMException oe = new ORMException();
            oe.initCause(e);
            throw oe;
        }
    }

    /**
     * Обновление коллекции элементов в базе данных.
     * (элементами коллекции могут быть только одиночные объекты)
     * @param elements - коллекция добавляемых элементов
     * @param elementClass - тип элемента коллеции
     * @throws ORMException - ошибка выполнения операции добавления объекта
     * @param <E> - тип элемента обновляемой коллекции
     * */
    public <E> void update(Collection<E> elements, Class<E> elementClass) throws ORMException {
        try {
            if (elements != null && elements.size() != 0) {
                /*добавление атомарных полей элементов коллекции*/
                updateAtomicORM.execute(elements, elementClass);

                /* обновление комплексных полей*/
                /* комплексные поля элемента коллекции*/
                Field[] complexFields = auxiliaryORM.getComplexFields(elementClass);

               /* формирование коллекций значений полей элементов коллекции и их обновление в базе данных*/
                for (Field complexField : complexFields) {
                    Collection fieldsValues = auxiliaryORM.getValuesFieldsOfElements(elements, complexField);
                    if (Collection.class.isAssignableFrom(complexField.getType())) {
                        update(fieldsValues, auxiliaryORM.getFieldArgumentType(complexField));
                    } else {
                        update(fieldsValues, complexField.getType());
                    }
                }
                /* обновление связи в базе данных между элементом коллекции и значением комплексного поля элемента коллекции*/
                for (Field complexArgument : complexFields) {
                    linkORM.removeElementComplexFieldValueLink(elements, complexArgument);
                    linkORM.setElementComplexFieldValueLink(elements, complexArgument);
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException e) {
            ORMException oe = new ORMException();
            oe.initCause(e);
            throw oe;
        }
    }



    /**
     * Возвращает коллекцмию элементов, значения атомарных полей которрых удовлетворяют заданным.
     * @param fieldsMatches - название полей и их значения, которым должны удовлетворять искомые элементы
     * @param elementClass - класс возвращаемых объектов
     * @throws ORMException - ошибка выполнения поиска
     * @return коллекция элементов
     * @param <E> - тип элемента возвращаеммой коллекции
     */
    public <E> Collection<E> get(Map<String, Set> fieldsMatches, Class<E> elementClass) throws ORMException {
        try {
            /*вернуть элементы с заполненными атомарными полями*/
            Collection<E> elements = getAtomicORM.execute(fieldsMatches, elementClass);

            /* вернуть значения комплексных полей найденных элементов*/
            if (elements.size() != 0) {
                /*комплексные поля элемента коллекеции*/
                Field[] complexFields = auxiliaryORM.getComplexFields(elementClass);
                /*вернуть элементам значения комплексных полей*/
                for (Field complexField : complexFields) {
                    /*вернуть связь элементов коллеции со значениям их комплексного поля*/
                    Map<Integer, Set<Integer>> elementComplexFieldValueLink = linkORM.getElementComplexFieldValueLink(elements, complexField);

                    /*извлечь значения указанного комплексного поля для всех элементов. */
                    /*id значений полей */
                    Set<Integer> fieldValuesID = new HashSet<>();
                    for (Map.Entry<Integer, Set<Integer>> entry : elementComplexFieldValueLink.entrySet()) {
                        fieldValuesID.addAll(entry.getValue());
                    }
                    Class fieldValueClass;
                    if (Collection.class.isAssignableFrom(complexField.getType())) {
                        fieldValueClass = auxiliaryORM.getFieldArgumentType(complexField);
                    } else {
                        fieldValueClass = complexField.getType();
                    }

                    HashMap<String, Set> fieldValuesIDMatches = new HashMap<>();
                    fieldValuesIDMatches.put(AuxiliaryORM.ID, fieldValuesID);

                    /*извлеченные значения комплексного поля*/
                    Collection fieldValues = get(fieldValuesIDMatches, fieldValueClass);

                    /* вернуть элементам значения их комплексного поля*/
                    for (Map.Entry<Integer, Set<Integer>> entry : elementComplexFieldValueLink.entrySet()) {
                        Integer idElement = entry.getKey();
                        /*определить элемент по значению id*/
                        for (E element : elements) {
                            Integer tmpIdElement = (Integer) auxiliaryORM.getFieldValue(element, auxiliaryORM.getField(element, AuxiliaryORM.ID));
                            if (tmpIdElement.equals(idElement)) {
                                Set<Integer> idFieldValues = entry.getValue();
                                for (Integer idFieldValue : idFieldValues) {
                                    /*лпределиьт значение поля по id значения поля*/
                                    for (Object fieldValue : fieldValues) {
                                        Integer tmpIdFieldValue = (Integer) auxiliaryORM.getFieldValue(fieldValue, auxiliaryORM.getField(fieldValue, AuxiliaryORM.ID));
                                        if (tmpIdFieldValue.equals(idFieldValue)) {
                                            Object val = auxiliaryORM.getFieldValue(element, complexField);
                                            if (Collection.class.isAssignableFrom(complexField.getType())) {
                                                Collection c = (Collection) val;
                                                if (c == null) {
                                                    ArrayList list = new ArrayList();
                                                    list.add(fieldValue);
                                                    complexField.setAccessible(true);
                                                    complexField.set(element, list);
                                                } else {
                                                    c.add(fieldValue);
                                                }
                                            } else {
                                                complexField.setAccessible(true);
                                                complexField.set(element, fieldValue);
                                            }
                                            break;
                                        }

                                    }
                                }
                                break;
                            }

                        }
                    }
                }
            }
            return  elements;
        } catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException e) {
            ORMException oe = new ORMException();
            oe.initCause(e);
            throw oe;
        }
    }
    /**
     * Удаляет коллекцию объектов.
     * @param elements - коллекция элементов
     * @param elementClass - класс элемента коллекции
     * @throws ORMException - невозможно выполнить операцию
     * @param <E> - тип элемента коллекции
     * */
    public <E> void remove(Collection<E> elements, Class<E> elementClass) throws ORMException {
        removeORM.execute(elements, elementClass);
    }


    /**
     * Вовзращает все id элементов заданного типа.
     * @param elementClass - класс элемента
     * @return множество id  всех элементов
     * @throws ORMException - ошибка выполнения операции
     * */
    public Set<Integer> getAllElementsId(Class elementClass) throws ORMException {
        StringBuilder tableName = new StringBuilder(elementClass.getSimpleName().toLowerCase());
        if (tableName.lastIndexOf("s") == tableName.length() - 1) {
            tableName.append("es");
        } else {
            tableName.append("s");
        }

        HashSet<Integer> elementsId = new HashSet<>();
        String sql = String.format("SELECT id FROM %s FOR UPDATE;", tableName.toString());
        try (Statement st = auxiliaryORM.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                elementsId.add(rs.getInt(AuxiliaryORM.ID));
            }
            return elementsId;
        } catch (SQLException e) {
            ORMException oe = new ORMException();
            oe.initCause(e);
            throw oe;
        }
    }
}

