package ru.job4j.testtask.model.storage.dao.interfaces;


import ru.job4j.testtask.model.entities.Address;
import ru.job4j.testtask.model.entities.MusicStyle;
import ru.job4j.testtask.model.entities.Role;
import ru.job4j.testtask.model.entities.User;
import ru.job4j.testtask.model.storage.dao.exception.OperationException;

import java.util.Collection;

/**
 * Interface IUserDAO описывает огбращение с элементами User в хранилище.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 13.12.2017
 * */
public interface IUserDAO extends IFundamentalDAO<User> {
    /**
     * Проверка регистрации пользователя.
     * @param login - логин
     * @param password  - пароль
     * @return true, елси пользователь зарегистрирован.
     * @throws OperationException - ошибка выполнения операции
     * */
    User isCredential(String login, String password) throws OperationException;
    /**
     * Возвращает пользователей по адресу.
     * @param address - адрес пользователя
     * @return пользователи с заданным адресом
     * @throws OperationException - ошибка выполнения операции
     * */
    Collection<User> getByAddress(Address address) throws OperationException;

    /**
     * Возвращает пользователей по роли.
     * @param role - роль
     * @return пользователи с искомой ролью
     * @throws OperationException - ошибка выполнения операции
     * */
    Collection<User> getByRole(Role role) throws OperationException;
    /**
     * Возвращает пользователей с заданными музыкальными предпочтениями.
     * @param musicPreferences - музыкальны предпочтения пользователей
     * @return пользователи с заданными музыкальными предпочтениями
     * @throws OperationException - ошибка выполнения операции
     * */
    Collection<User> getByMusicPreferences(Collection<MusicStyle> musicPreferences) throws OperationException;


}
