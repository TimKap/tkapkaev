package ru.job4j.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.ioc.model.User;
import ru.job4j.ioc.storage.UserDAO;

/**
 * Class ImportUser описывает добавление пользователей в базу данных.
 * @author timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 08.02.2018
 * */
public class ImportUser {
    /**
     * Добавляет пользователя в базу данных.
     * @param args - аргументы командной строки
     * */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserDAO userDAO = context.getBean(UserDAO.class);
        User tom = new User("Tom", "Garison", "tom");
        userDAO.persist(tom);
    }
}
