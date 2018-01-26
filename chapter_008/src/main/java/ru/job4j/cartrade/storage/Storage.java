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
    private final HibernateUtil hibernateUtil = new HibernateUtil();
    /**
     * Открывает хранилище.
     * */
    public void open() {
        hibernateUtil.beginTransaction();
    }

    /**
     * Возвращает реализацию IUserDAO.
     * @return  реализацию IUserDAO
     *
     * */
    public IUserDAO getUserDAO() {
        return hibernateUtil.isTransactionOpen() ? new UserDAO(hibernateUtil.getSession()) : null;
    }
    /**
     * Возвращает реализацию ICarDAO.
     * @return  реализацию ICarDAO
     *
     * */
    public ICarDAO getCarDAO() {
        return hibernateUtil.isTransactionOpen() ? new CarDAO(hibernateUtil.getSession()) : null;
    }

    /**
     * Возвращает реализацию IPhotoDAO.
     * @return  реализацию IPhotoDAO
     *
     * */
    public IPhotoDAO getPhotoDAO() {
        return hibernateUtil.isTransactionOpen() ? new PhotoDAO(hibernateUtil.getSession()) : null;
    }

    /**
     * Возвращает реализацию IAdvertisementDAO.
     * @return  реализацию IAdvertisementDAO
     *
     * */
    public IAdvertisementDAO getAdvertisementDAO() {
        return hibernateUtil.isTransactionOpen() ? new AdvertisementDAO(hibernateUtil.getSession()) : null;
    }

    /**
     * Потдтверждает операции.
     * */
    public void submit() {
        hibernateUtil.commitTransaction();
        hibernateUtil.closeSession();
    }
    /**
     * Прекращает работу с хранилищем.
     * */
    public void close() {
        hibernateUtil.closeSessionFactory();
    }

}
