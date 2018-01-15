package ru.job4j.todo.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Class CloseSessionFactory описывает закрытие ресурса SessionFactory.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 15.01.2018
 */
@WebListener()
public class CloseSessionFactory implements ServletContextListener {
    /**
     * Вызывается при наступлении события - инициализация объекта ServletContext.
     * @param varl - объект, содержащий ServletContext
     * */
    @Override
    public void contextInitialized(ServletContextEvent varl) {
    }

    /**
     * Закрывает SessionFactory в случае разрушения объекта ServletContext.
     * @param varl - объект, содержащий ServletContext
     * */
    @Override
    public void contextDestroyed(ServletContextEvent varl) {
        SessionFactorySingletone.getSessionFactory().close();
    }
}
