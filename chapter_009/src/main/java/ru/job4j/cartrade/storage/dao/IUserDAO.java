package ru.job4j.cartrade.storage.dao;

import ru.job4j.cartrade.model.user.User;

/**
 * Interface IUserDAO описывает интерфейс DAO, взаимодействующего с сущностью User.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 23.01.20108
 * */
public interface IUserDAO extends IGenericDAO<User> {
    /**
     * Возвращает пользователя с заданным именем и паролем.
     * @param name - имя пользователя.
     * @param password - пароль пользователя
     * @return пользователь
     * */
    User credential(String name, String password);
}
