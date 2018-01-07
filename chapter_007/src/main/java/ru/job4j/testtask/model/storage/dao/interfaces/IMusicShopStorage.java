package ru.job4j.testtask.model.storage.dao.interfaces;

/**
 * Interface IMusicShopStorage описывает взаимодействие с хранилищем музыкального магазина.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 13.12.2017
 * */
public interface IMusicShopStorage {

    /**
     * Возвращает объект DAO  User.
     * @return DAO User.
     * */
    IUserDAO getUserDAO();
    /**
     * Возвращает DAO Address.
     * @return DAO Address
     * */
    IAddressDAO getAddressDAO();
    /**
     * Возвращает DAO MusicType.
     * @return DAO MusicType
     * */
    IMusicStyleDAO getMusicStyleDAO();
    /**
     * Возвращает DAO Role.
     * @return DAO Role
     * */
    IRoleDAO getRoleDAO();

}
