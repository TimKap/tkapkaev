package ru.job4j.sortirovka;

/**
 * Class описывает пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 23.06.2017
 */
public class User implements Comparable<User> {
    /** Имя поль зователя.*/
    private String name;
    /** Возраст пользователя.*/
    private int age;

    /**
     * Конструктор класса User.
     * @param name - имя
     * @param age - возраст
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    /**
     * @return возвращает имя
     * */
    public String getName() {
        return name;
    }

    /**
     * @return возвращает возраст
     * */
    public int getAge() {
        return age;
    }

    /**
     * Реализация сравнения.
     * @param o - объект, с которым выполняется сравнение.
     * @return результат сравнения
     * */
    @Override
    public int compareTo(User o) {
        return ((Integer) age).compareTo(o.getAge());
    }

}
