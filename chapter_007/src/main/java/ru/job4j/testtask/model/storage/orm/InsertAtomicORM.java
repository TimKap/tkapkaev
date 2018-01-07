package ru.job4j.testtask.model.storage.orm;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Class InsertAtomicORM описывает операцию добавления в базу данных атомарных полей коллекции элементов.
 * (объектом может быть либо одиночный объект либо объект коллекция)
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 28.12.2017
 * */
class InsertAtomicORM {
    /** вспомогательные операции ORM. */
    private final AuxiliaryORM auxiliaryORM;
    /**
     * Конструктор класса InsertAtomicORM.
     * @param con - соединение с базой данных.
     * */
    InsertAtomicORM(Connection con) {
        this.auxiliaryORM = new AuxiliaryORM(con);
    }

    /**
     * Добавление атомарных полей элементов коллекции.
     * (элементами коллекции могут быть только одиночные объекты)
     * @param elements - коллекция добавляемых элементов
     * @param elementClass - тип элемента коллеции
     * @throws ORMException - ошибка выполнения операции добавления объекта
     * @param <E> - тип добавляемого объекта
     * */
   <E> void execute(Collection<E> elements, Class<E> elementClass) throws ORMException {
        try {
            /* добавить коллекцию элементов, если в ней есть элементы */
            if (elements != null && elements.size() != 0) {
                /*сформировать имя таблицы, в которую будут добаляться атомарные поля элементов коллеции*/
                /*имя таблицы = имя типа елемента коллеции, взятое во множественном числе*/
                StringBuilder tableName = new StringBuilder(elementClass.getSimpleName().toLowerCase());
                if (tableName.lastIndexOf("s") == tableName.length() - 1) {
                    tableName.append("es");
                } else {
                    tableName.append("s");
                }

                /* атомарные поля элемента коллекции*/
                Field[] atomicFields = auxiliaryORM.getEffectiveAtomicFields(elementClass);

                /* id поле элемента коллекции */
                Field idField = elementClass.getDeclaredField(AuxiliaryORM.ID);

                /* sql запрос на добавление элементов в таблицу */
                String sql = createInsertRequest(tableName.toString(), atomicFields);

                /*подготовка и выполнение запроса на добавление атомарных полей элементов в базу данных*/
                try (PreparedStatement ps = auxiliaryORM.getConnection().prepareStatement(sql, new String[]{AuxiliaryORM.ID})) {
                    ArrayList insertedElements = new ArrayList();
                    for (Object element : elements) {
                        int elementID = (Integer) auxiliaryORM.getFieldValue(element, idField);
                        if (elementID == 0) {
                            boolean isInserted = false;
                            /*выполнить проверку, что объект уже был добавлен (id = 0, но объект с такой же ссылкой был уже добавлен в запрос)*/
                            for (Object insertedElement : insertedElements) {
                                if (insertedElement == element) {
                                    isInserted = true;
                                }
                            }
                            if (!isInserted) {
                                insertedElements.add(element);
                                int i = 0;
                                for (Field field : atomicFields) {
                                    Object fieldValue = auxiliaryORM.getFieldValue(element, field);
                                    auxiliaryORM.substituteValueToPreparedStatement(ps, ++i, fieldValue, field.getType());
                                }
                                ps.addBatch();
                            }
                        }
                    }
                    ps.executeBatch();
                    /* установить элементу зарегистрированный адрес */
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        /*извлечь значение зарегистрированного в базе данных id*/
                        for (Object element : elements) {
                            int elementID = (Integer) auxiliaryORM.getFieldValue(element, idField);
                            if (elementID == 0) {
                                rs.next();
                                idField.setAccessible(true);
                                idField.setInt(element, rs.getInt(AuxiliaryORM.ID));
                            }
                        }
                    }
                }
            }

        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            ORMException oe = new ORMException();
            oe.initCause(e);
            throw oe;
        }
    }

    /**
     * Формирование запроса на добавление элемента в базу данных.
     * @param tableName - имя таблицы базы данных
     * @param arguments - аргументы таблицы
     * @return запроса на добавление элемента
     */
    private String createInsertRequest(String tableName, Field[] arguments) {
        StringBuilder tableHeader = new StringBuilder();
        StringBuilder pattern = new StringBuilder();
        boolean isFirst = true;
        for (Field argument : arguments) {
            if (!isFirst) {
                tableHeader.append(", " + argument.getName().toLowerCase());
                pattern.append(", ?");
            } else {
                tableHeader.append(argument.getName().toLowerCase());
                pattern.append("?");
                isFirst = false;
            }
        }
        return String.format("INSERT INTO %s (%s) VALUES (%s);", tableName, tableHeader.toString(), pattern.toString());
    }
}
