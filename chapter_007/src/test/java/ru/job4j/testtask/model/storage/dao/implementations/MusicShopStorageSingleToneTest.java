package ru.job4j.testtask.model.storage.dao.implementations;

import org.junit.Test;
import ru.job4j.testtask.model.storage.dao.interfaces.IAddressDAO;
import ru.job4j.testtask.model.storage.dao.interfaces.IMusicShopStorage;
import ru.job4j.testtask.model.storage.dao.interfaces.IMusicStyleDAO;
import ru.job4j.testtask.model.storage.dao.interfaces.IUserDAO;
import ru.job4j.testtask.model.storage.dao.interfaces.IRoleDAO;


/**
 * Class MusicShopStorageSingleToneTest содержит тесты для MusicShopStorageSingleTone.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 22.12.2017

 * */
public class MusicShopStorageSingleToneTest {
    /**
     * Тест для getStorage.
     * @throws Exception -
     * */
    @Test
    public void whenGetStorageThenGetMusicShopStorage() throws Exception {
        IMusicShopStorage storage = MusicShopStorageSingleTone.getStorage();
    }

    /**
     * Тест для getUserDAO.
     * @throws Exception -
     * */
    @Test
    public void whenGetIUserDAOThenGetUserDAOImplementation() throws Exception {
        IUserDAO users = MusicShopStorageSingleTone.getStorage().getUserDAO();
    }

    /**
     * Тест для getAddressDAO.
     * @throws Exception -
     * */
    @Test
    public void whenGetIAddressDAOThenGetAddressDAOImplementation() throws Exception {
        IAddressDAO adresses = MusicShopStorageSingleTone.getStorage().getAddressDAO();
    }

    /**
     * Тест для getMusicTypeDAO.
     * @throws Exception -
     * */
    @Test
    public void whenGetIMusicTypeDAOThenGetMusicTypeDAOImplementation() throws Exception {
        IMusicStyleDAO musictype = MusicShopStorageSingleTone.getStorage().getMusicStyleDAO();
    }
    /**
     * Тест для getRoleDAO.
     * @throws Exception -
     * */
    @Test
    public void whenGetIRoleDAOThenGetRoleDAOImplementation() throws Exception {
        IRoleDAO roles = MusicShopStorageSingleTone.getStorage().getRoleDAO();
    }


}