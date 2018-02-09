package ru.job4j.demonstrate.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Class DemonstrateCreateUser описывает создание пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 09.02.2018
 * */
public class DemonstrateCreateUser {
    /**
     * Демонстрация создания пользователя.
     * @param args - аргументы командной строки.
     * */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("annotation-spring-context.xml");
        User user = context.getBean(User.class);
        System.out.println("name: " + user.getName());
        System.out.println("age: " + user.getAge());
        System.out.println("password: " + user.getPassword());
        System.out.println("passport: " + user.getPassport().getInformation());
    }
}
