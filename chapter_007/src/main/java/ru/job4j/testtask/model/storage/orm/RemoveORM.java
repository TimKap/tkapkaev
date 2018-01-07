package ru.job4j.testtask.model.storage.orm;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Class RemoveORM рписывает операцию удаления объекта из базы данных.
 * @author Timur Kapakev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 02.01.2018
 * */
public class RemoveORM {
    /** вспомогательные операции ORM. */
    private final AuxiliaryORM auxiliaryORM;
    /**
     * Конструктор класса InsertAtomicORM.
     * @param con - соединение с базой данных.
     * */
    RemoveORM(Connection con) {
        this.auxiliaryORM = new AuxiliaryORM(con);
    }

    /**
     * Удаляет коллекцию объектов.
     * @param elements - коллекция элементов
     * @param elementClass - класс элемента коллекции
     * @throws ORMException - невозможно выполнить операцию
     * @param <E> - тип элемента коллекции
     * */
    public <E> void execute(Collection<E> elements, Class<E> elementClass) throws ORMException {
        try {
            Field idField = elementClass.getDeclaredField(AuxiliaryORM.ID);
            Set<Integer> elementsId = new HashSet<>();
            for (E element : elements) {
                elementsId.add((Integer) auxiliaryORM.getFieldValue(element, idField));
            }
            /*имя таблицы, содержащей объекты */
            StringBuilder tableName = new StringBuilder(elementClass.getSimpleName().toLowerCase());
            if (tableName.lastIndexOf("s") == tableName.length() - 1) {
                tableName.append("es");
            } else {
                tableName.append("s");
            }

            String sql = createDeleteRequest(tableName.toString(), elementsId.size());
            try (PreparedStatement ps = auxiliaryORM.getConnection().prepareStatement(sql)) {
                int i = 0;
                for (Integer id : elementsId) {
                    ps.setInt(++i, id);
                }
                ps.execute();
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
     * @param size - количество удаляемых объектов
     * @return запроса на добавление элемента
     */
    private String createDeleteRequest(String tableName, int size) {
        StringBuilder pattern = new StringBuilder();
        boolean isFirst = true;
        for (int i = 0; i < size; i++) {
            if (!isFirst) {
                pattern.append(", ?");
            } else {
                pattern.append("?");
                isFirst = false;
            }
        }
        return String.format("DELETE FROM %s WHERE %s IN (%s);", tableName, AuxiliaryORM.ID, pattern.toString());
    }
}
