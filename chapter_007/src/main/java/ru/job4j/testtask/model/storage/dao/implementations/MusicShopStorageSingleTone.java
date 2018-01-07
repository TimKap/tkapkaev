package ru.job4j.testtask.model.storage.dao.implementations;


import ru.job4j.testtask.model.storage.dao.interfaces.IMusicShopStorage;
import ru.job4j.testtask.model.storage.dao.interfaces.IMusicStyleDAO;
import ru.job4j.testtask.model.storage.dao.interfaces.IRoleDAO;
import ru.job4j.testtask.model.storage.dao.interfaces.IAddressDAO;
import ru.job4j.testtask.model.storage.dao.interfaces.IUserDAO;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Class MusicShopStorageSingleTone описывает хранилище музыкального магазина.
 * Хранилище представлено единственным объектом.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 13.12.2017
 * */
public class MusicShopStorageSingleTone implements IMusicShopStorage {

    /** экземпляр хранилища. */
    private static final MusicShopStorageSingleTone STORAGE = new MusicShopStorageSingleTone();
    /** пул соединений. */
    private BasicDataSource connectionPool;

    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Конструктор MusicShopStorageSingleTone.
     * */
    private MusicShopStorageSingleTone() {
        try {
            this.connectionPool = connectionPool();
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    /**
     * Возаращает пул соединений с базой данных.
     * @return пул соединений
     * @throws IOException - ошибка конфигурационного файла.
     * */
    private BasicDataSource connectionPool() throws IOException {
        Properties properties = new Properties();
        properties.load(MusicShopStorageSingleTone.class.getResourceAsStream("/MusicShopConfig.properties"));
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        BasicDataSource connectionPool = new BasicDataSource();
        connectionPool.setDriverClassName("org.postgresql.Driver");
        connectionPool.setUrl(url);
        connectionPool.setUsername(user);
        connectionPool.setPassword(password);
        return connectionPool;
    }

    /**
     * Возвращает музыкальное хранилище.
     * @return музыкальное хранилище.
     * */
    public static MusicShopStorageSingleTone getStorage() {
        return STORAGE;
    }

    /**
     * Возвращает объект DAO  User.
     * @return DAO User.
     * */
    @Override
    public IUserDAO getUserDAO() {
        return new UserDataBase(connectionPool);
    }

    /**
     * Возвращает DAO Address.
     * @return DAO Address
     * */
    @Override
    public IAddressDAO getAddressDAO() {
        return new AddressDataBase(connectionPool);
    }

    /**
     * Возвращает DAO MusicType.
     * @return DAO MusicType
     * */
    @Override
    public IMusicStyleDAO getMusicStyleDAO() {
        return new MusicStyleDataBase(connectionPool);
    }

    /**
     * Возвращает DAO Role.
     * @return DAO Role
     * */
    @Override
    public IRoleDAO getRoleDAO() {
        return new RoleDataBase(connectionPool);
    }
}
