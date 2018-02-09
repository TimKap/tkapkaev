package ru.job4j.demonstrate.annotation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Class User описывает модель пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 09.02.2018
 * */
@Component("user")
public class User {
    /** имя пользователя. */
    private String name;
    /** возраст пользователя. */
    private int age;
    /** пароль.*/
    private String password;
    /** паспорт. */
    private IPassport passport;


    /**
     * Конструктор класса User.
     * @param name - имя пользователя
     * @param password - пароль пользователя.
     * @param passport - паспорт пользователя.
     * */
    public User(@Value("NoName")String name, @Value("password")String password, @Qualifier("russianPassport")IPassport passport) {
        this.name = name;
        this.password = password;
        this.passport = passport;
    }

    /**
     * Возвращает имя пользователя.
     * @return имя пользователя.
     * */
    public String getName() {
        return name;
    }
    /**
     * Задает имя пользователя.
     * @param name - имя пользователя
     * */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает возраст пользователя.
     * @return возраст пользователя.
     * */
    public int getAge() {
        return age;
    }

    /**
     * Задаетвозраст пользователя.
     * @param age - возраст пользователя
     * */
    @Value("1")
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Возвращает пароль пользователя.
     * @return пароль пользователя.
     * */
    public String getPassword() {
        return password;
    }

    /**
     * Задает пароль пользователя.
     * @param password - пароль пользователя
     * */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Возвращает паспотр пользователя.
     * @return паспорт пользователя.
     * */
    public IPassport getPassport() {
        return passport;
    }

    /**
     * Задает паспорт пользователя.
     * @param passport - паспорт пользователя
     * */

    public void setPassport(IPassport passport) {
        this.passport = passport;
    }
}
