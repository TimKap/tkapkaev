package ru.job4j.testtask.model.storage.dao.implementations;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.util.Properties;

/**
 * Возвращает пул соединнеий с тестируемой базой данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 22.12.2017
 * */
public class TestDataBasePoolConnection {
    /**
     * Возаращает пул соединений с базой данных.
     * @return пул соединений
     * @throws IOException - ошибка конфигурационного файла.
     * */
    public BasicDataSource connectionPool() throws IOException {
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
}
