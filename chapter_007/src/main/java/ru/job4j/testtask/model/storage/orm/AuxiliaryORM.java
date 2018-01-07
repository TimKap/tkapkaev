package ru.job4j.testtask.model.storage.orm;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Class AuxiliaryORM общие методы для работы с ORM.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 28.12.2017
 * */
 class AuxiliaryORM {
    /** соединение с базой данных .*/
    private Connection con;

    /** id. */
     public static final String ID = "id";

    /**
     * Конструктор класса AuxiliaryORM.
     * @param con - соединение с базой данных.
     * */
     AuxiliaryORM(Connection con) {
        this.con = con;
    }

    /**
     * Возвращает соединение с базой данных.
     * @return соединение с базой данных
     * */
    public Connection getConnection() {
        return con;
    }

    /**
     * Проверка поля объекта на  атомарность.
     * @param field - поле объекта
     * @return true, если поле имеет атомарный тип
     * */
   public  boolean isAtomic(Field field) {
        Class type = field.getType();
        return type.isPrimitive() || (Boolean.class.isAssignableFrom(type) || Byte.class.isAssignableFrom(type)
                ||
                Short.class.isAssignableFrom(type) || Integer.class.isAssignableFrom(type) || Long.class.isAssignableFrom(type)
                ||
                Float.class.isAssignableFrom(type) || Double.class.isAssignableFrom(type) || String.class.isAssignableFrom(type));
    }

    /**
     * Возвращает поле объекта по его имени.
     * @param obj - объект
     * @param fieldName - имя поля
     * @return поле объекта.
     * @throws NoSuchFieldException - объект не имеет указанного поля
     * */
   public  Field getField(Object obj, String fieldName) throws NoSuchFieldException {
       Field[] fields = obj.getClass().getDeclaredFields();
       for (Field field : fields) {
            if (field.getName().toLowerCase().equals(fieldName)) {
                return field;
            }
        }
        throw new NoSuchFieldException();
    }

    /**
     * Возвращает значение поля объекта.
     * @param obj - объект
     * @param field - поле объекта
     * @throws NoSuchFieldException - объект не имеет указанного поля
     * @throws IllegalAccessException - ошибка доступа к полю объекта
     * @return значение поля
     * */
    public Object getFieldValue(Object obj, Field field) throws NoSuchFieldException, IllegalAccessException {
        field.setAccessible(true);
        return field.get(obj);

    }

    /**
     * Возвращает атомарные поля rkfccf.
     * (за исключением id)
     * @param clazz - класс
     * @return массив  атомарных полей полей
     * @throws NoSuchFieldException - невозможнополучить значение поля
     * */
     public Field[] getEffectiveAtomicFields(Class clazz) throws NoSuchFieldException {
        Field[] fields = clazz.getDeclaredFields();

        Field[] buf = new Field[fields.length];
        Field idField = clazz.getDeclaredField(ID);
        int i = 0;
        for (Field field : fields) {
            if (isAtomic(field) && !field.equals(idField)) {
                buf[i++] = field;
            }
        }
        return Arrays.copyOf(buf, i);
    }

    /**
     * Возвращает атомарные поля rkfccf.
     * (за исключением id)
     * @param clazz - класс
     * @return массив  атомарных полей полей
     * @throws NoSuchFieldException - невозможнополучить значение поля
     * */
   public Field[] getComplexFields(Class clazz) throws NoSuchFieldException {
        Field[] fields = clazz.getDeclaredFields();
        Field[] buf = new Field[fields.length];
        int i = 0;
        for (Field field : fields) {
            if (!isAtomic(field)) {
                buf[i++] = field;
            }
        }
        return Arrays.copyOf(buf, i);
    }

    /**
     * Подставляет значение в prepared statement.
     * @param preparedStatement - prepared statement
     * @param position - позици добавляемого значения
     * @param value - добавляемое значение
     * @param valueType - тип добавляемого значения
     * @throws SQLException - ошибка добавления значения
     * */
     public void substituteValueToPreparedStatement(PreparedStatement preparedStatement, int position, Object value, Class valueType) throws SQLException {


        if (Integer.class.isAssignableFrom(valueType) || Integer.TYPE.isAssignableFrom(valueType)) {
            if (value != null) {
                preparedStatement.setInt(position, (Integer) value);
            } else {
                preparedStatement.setNull(position, Types.INTEGER);
            }
            return;
        }
        if (String.class.isAssignableFrom(valueType)) {
            if (value != null) {
                preparedStatement.setString(position, (String) value);
            } else {
                preparedStatement.setNull(position, Types.VARCHAR);
            }
            return;
        }
        if (Float.class.isAssignableFrom(valueType) || Float.TYPE.isAssignableFrom(valueType)) {
            if (value != null) {
                preparedStatement.setFloat(position, (Float) value);
            } else {
                preparedStatement.setNull(position, Types.FLOAT);
            }
            return;
        }
        if (Double.class.isAssignableFrom(valueType) || Double.TYPE.isAssignableFrom(valueType)) {
            if (value != null) {
                preparedStatement.setDouble(position, (Double) value);
            } else {
                preparedStatement.setNull(position, Types.DOUBLE);
            }
            return;
        }
        if (Boolean.class.isAssignableFrom(valueType) || Boolean.TYPE.isAssignableFrom(valueType)) {
            if (value != null) {
                preparedStatement.setBoolean(position, (Boolean) value);
            } else {
                preparedStatement.setNull(position, Types.BOOLEAN);
            }
            return;
        }
        if (Long.class.isAssignableFrom(valueType) || Long.TYPE.isAssignableFrom(valueType)) {
            if (value != null) {
                preparedStatement.setLong(position, (Long) value);
            } else {
                preparedStatement.setNull(position, Types.BIGINT);
            }
            return;
        }
        if (Short.class.isAssignableFrom(valueType) || Short.TYPE.isAssignableFrom(valueType)) {
            if (value != null) {
                preparedStatement.setShort(position, (Short) value);
            } else {
                preparedStatement.setNull(position, Types.SMALLINT);
            }
            return;
        }
    }


    /**
     * Вовзращает значение поля объекта из результата запроса.
     * @param resultSet - результат запроса
     * @param field - поле объекта
     * @return значение аргумента
     * @throws SQLException - ошибка запроса
     * */
    public Object extractFieldValueFromResultSet(ResultSet resultSet, Field field) throws SQLException {
        Class fieldType = field.getType();
        String fieldName = field.getName();
        if (Integer.class.isAssignableFrom(fieldType) || Integer.TYPE.isAssignableFrom(fieldType)) {
            return resultSet.getInt(fieldName);
        }
        if (String.class.isAssignableFrom(fieldType)) {
            return resultSet.getString(fieldName);
        }
        if (Float.class.isAssignableFrom(fieldType) || Float.TYPE.isAssignableFrom(fieldType)) {
            return resultSet.getFloat(fieldName);
        }
        if (Double.class.isAssignableFrom(fieldType) || Double.TYPE.isAssignableFrom(fieldType)) {
            return resultSet.getDouble(fieldName);
        }

        if (Boolean.class.isAssignableFrom(fieldType) || Boolean.TYPE.isAssignableFrom(fieldType)) {
            return resultSet.getBoolean(fieldName);
        }
        if (Long.class.isAssignableFrom(fieldType) ||  Long.TYPE.isAssignableFrom(fieldType)) {
            return resultSet.getLong(fieldName);
        }

        if (Short.class.isAssignableFrom(fieldType) || Short.TYPE.isAssignableFrom(fieldType)) {
            return resultSet.getLong(fieldName);
        }

        return null;

    }

    /**
     * Возвращает значенмя полей элементов коллекции.
     * @param elements - коллекция объектов
     * @param field - поле элемента коллекции
     * @return коллекция значения поля элементов коллекции
     * @throws NoSuchFieldException - элементы коллекции не имеют указанного поля
     * @throws IllegalAccessException - нельзя получить значение поля элекмента коллекции
     * */
    public Collection getValuesFieldsOfElements(Collection elements, Field field) throws NoSuchFieldException, IllegalAccessException {
        ArrayList fieldValues = new ArrayList();
        if (Collection.class.isAssignableFrom(field.getType())) {
            /*типом поля - коллекция*/
            for (Object element : elements) {
                Collection fieldValue = (Collection) getFieldValue(element, field);
                if (fieldValue != null) {
                    fieldValues.addAll(fieldValue);
                }
            }
        } else {
            /*тип поля - единственный объект*/
            for (Object element : elements) {
                Object fieldValue = getFieldValue(element, field);
                if (fieldValue != null) {
                    fieldValues.add(fieldValue);
                }
            }
        }
        return fieldValues;
    }

    /**
     * Возвращает аргумент типа параметризованного поля.
     * @param paramField - параметризованное поле
     * @return Class аргумент типа коллекции (null, если поле не параметризовано или параметр нельзя определить)
     * @throws ClassNotFoundException - если невозможно определить класс.
     * */
    public Class getFieldArgumentType(Field paramField) throws ClassNotFoundException {
        Type fieldType = paramField.getGenericType();
        if (fieldType instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) fieldType;
            Type argumentType = pType.getActualTypeArguments()[0];
            return Class.forName(argumentType.getTypeName());
        } else {
            return null;
        }
    }

}
