package ru.job4j.testtask.model.storage.orm;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Class UpdateAtomicORM описывает операцию обновления атомарных полей коллекции элементов в базе данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 28.12.2017
 * */

class UpdateAtomicORM {

    /** вспомогательные операции ORM. */
    private final AuxiliaryORM auxiliaryORM;
    /** операция добавления элементов. */
    private final InsertAtomicORM insertORM;
    /**
     * Конструктор UpdateAtomicORM.
     * @param con - соединение сс базой данных.
     * */
    UpdateAtomicORM(Connection con) {
        this.auxiliaryORM = new AuxiliaryORM(con);
        this.insertORM = new InsertAtomicORM(con);
    }

    /**
     * Обновление атомарных полей элементов коллекции.
     * (элементами коллекции могут быть только одиночные объекты)
     * @param elements - коллекция добавляемых элементов
     * @param elementClass - тип элемента коллеции
     * @throws ORMException - ошибка выполнения операции добавления объекта
     * @param <E> - тип элемента обновляемой коллекции
     * */
   <E> void execute(Collection<E> elements, Class<E> elementClass) throws ORMException {
       try {
            /* обновить коллекцию элементов, если в ней есть элементы */
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

               /*id поле элемента коллекции*/
               Field idField = elementClass.getDeclaredField(AuxiliaryORM.ID);

               /*sql запрос на обновление элемнтов*/
               String sql = createUpdateRequest(tableName.toString(), atomicFields);
               /*подготовка и выполнение запроса на обновление атомарных полей элементов в базе данных*/
               try (PreparedStatement ps = auxiliaryORM.getConnection().prepareStatement(sql)) {
                   for (Object element : elements) {
                       int elementID = (Integer) auxiliaryORM.getFieldValue(element, idField);
                       if (elementID != 0) {
                           int i = 0;
                           for (Field field : atomicFields) {
                               Object value = auxiliaryORM.getFieldValue(element, field);
                               auxiliaryORM.substituteValueToPreparedStatement(ps, ++i, value, field.getType());
                           }
                           auxiliaryORM.substituteValueToPreparedStatement(ps, ++i, auxiliaryORM.getFieldValue(element, idField), idField.getType());
                           ps.addBatch();
                       }
                   }
                   ps.executeBatch();
               }
               /* Вставить незарегистрированные элементы */
               insertORM.execute(elements, elementClass);
           }

       } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
           ORMException oe = new ORMException();
           oe.initCause(e);
           throw oe;
       }
    }
    /**
     * Формирует запрос на обновление объекта.
     * @param tableName - имя таблицы
     * @param arguments - аргументы таблицы
     * @return запрос на обновление объекта
     * */
    private String createUpdateRequest(String tableName, Field[] arguments) {
        StringBuilder pattern = new StringBuilder();
        boolean isFirst = true;

        for (Field argument : arguments) {
            if (!isFirst) {
                pattern.append(", " + argument.getName() + "=?");
            } else {
                pattern.append(argument.getName() + "=?");
                isFirst = false;
            }
        }
        return String.format("UPDATE %s SET %s WHERE id=?;", tableName, pattern.toString());
    }

}
