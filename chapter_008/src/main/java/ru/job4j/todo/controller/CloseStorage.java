package ru.job4j.todo.controller;

import ru.job4j.todo.storage.Storage;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Class CloseStorage описывает закрытие ресурса SessionFactory.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 15.01.2018
 */
@WebListener()
public class CloseStorage implements ServletContextListener {
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
        Storage.getInstance().close();
    }
}
