package ru.job4j.testtask;

/**
 * Class User описывает пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 24.06.2017
 */
public class User {
    /** Имя пользователя.*/
    private String name;
    /** Пасопрт пользователя. */
    private int passport;

    /**
     * Коснтруктор для класса User.
     * @param name - имя
     * @param passport - паспорт
     * */
    public User(String name, int passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Возвращает имя пользователя.
     * @return  имя
     * */
    public String getName() {
        return name;
    }
    /**
     * Возвращает имя пользователя.
     * @return  паспорт
     * */
    public int getPassport() {
        return passport;
    }

    /**
     * Правило сравнения объектов класса User.
     * @param o - объект, с котором выполняется сравнение.
     * @return логический результат сравнения
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (passport != user.passport) {
            return false;
        }
        return name != null ? name.equals(user.name) : user.name == null;
    }

    /**
     * Возвращает значение хеш-функции для класса User.
     * @return хеш-ключ
     * */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + passport;
        return result;
    }
}
