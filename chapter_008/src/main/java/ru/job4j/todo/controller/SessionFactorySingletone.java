package ru.job4j.todo.controller;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * Class SessionFactorySingleton олписывает получение единственного объекта.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 15.01.2018
 * */
public class SessionFactorySingletone {
    /** реализация SessionFactory. */
    private static final SessionFactory INSTANCE = new Configuration().configure().buildSessionFactory();
    /**
     * Возвращает экземпляр SessionFactory.
     * @return экземпляр SessionFactory
     * */
    public static SessionFactory getSessionFactory() {
        return INSTANCE;
    }

}
