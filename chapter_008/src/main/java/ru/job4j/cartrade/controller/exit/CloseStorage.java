package ru.job4j.cartrade.controller.exit;
import ru.job4j.cartrade.storage.Storage;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Class CloseStorage описывает завершение работы с хранилищем при разрушении ServletContext.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 25.01.20118
 * */
@WebListener
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
