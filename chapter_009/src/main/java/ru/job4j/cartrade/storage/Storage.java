package ru.job4j.cartrade.storage;

import ru.job4j.cartrade.storage.dao.IAdvertisementDAO;
import ru.job4j.cartrade.storage.dao.ICarDAO;
import ru.job4j.cartrade.storage.dao.IPhotoDAO;
import ru.job4j.cartrade.storage.dao.IUserDAO;
import ru.job4j.cartrade.storage.dao.hibernatedao.AdvertisementDAO;
import ru.job4j.cartrade.storage.dao.hibernatedao.CarDAO;
import ru.job4j.cartrade.storage.dao.hibernatedao.PhotoDAO;
import ru.job4j.cartrade.storage.dao.hibernatedao.UserDAO;

/**
 * Class Storage описывает хранилище данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 23.01.2018
 * */
public class Storage {
    /** рвспомогательный объект работы с hibernate. */
    private static final HibernateUtil HIBERNATE_UTIL = HibernateUtil.getInstance();
    /** экземпляр Storage. */
    private static final Storage INSTANCE = new Storage();

    /**
     * Конструктор Storage.
     * */
    private Storage() {

    }
    /**
     * Возвращает экземпляр Storage.
     * @return экземпляр Storage.
     * */
    public static Storage getInstance() {
        return INSTANCE;
    }
    /**
     * Открывает хранилище.
     * */
    public void open() {
        HIBERNATE_UTIL.beginTransaction();
    }

    /**
     * Возвращает реализацию IUserDAO.
     * @return  реализацию IUserDAO
     *
     * */
    public IUserDAO getUserDAO() {
        return HIBERNATE_UTIL.isTransactionOpen() ? new UserDAO(HIBERNATE_UTIL.getSession()) : null;
    }
    /**
     * Возвращает реализацию ICarDAO.
     * @return  реализацию ICarDAO
     *
     * */
    public ICarDAO getCarDAO() {
        return HIBERNATE_UTIL.isTransactionOpen() ? new CarDAO(HIBERNATE_UTIL.getSession()) : null;
    }

    /**
     * Возвращает реализацию IPhotoDAO.
     * @return  реализацию IPhotoDAO
     *
     * */
    public IPhotoDAO getPhotoDAO() {
        return HIBERNATE_UTIL.isTransactionOpen() ? new PhotoDAO(HIBERNATE_UTIL.getSession()) : null;
    }

    /**
     * Возвращает реализацию IAdvertisementDAO.
     * @return  реализацию IAdvertisementDAO
     *
     * */
    public IAdvertisementDAO getAdvertisementDAO() {
        return HIBERNATE_UTIL.isTransactionOpen() ? new AdvertisementDAO(HIBERNATE_UTIL.getSession()) : null;
    }

    /**
     * Потдтверждает операции.
     * */
    public void submit() {
        HIBERNATE_UTIL.commitTransaction();
        HIBERNATE_UTIL.closeSession();
    }
    /**
     * Прекращает работу с хранилищем.
     * */
    public void close() {
        HIBERNATE_UTIL.closeSessionFactory();
    }

}
