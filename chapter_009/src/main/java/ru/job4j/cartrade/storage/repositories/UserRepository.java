package ru.job4j.cartrade.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.cartrade.model.user.User;

/**
 * Interface UserRepository описывает репозиторий пользователей.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 04.03.2018
 * */
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Возвращает пользователя с заданным именем и паролем.
     * @param name - имя пользователя.
     * @param password - пароль пользователя
     * @return пользователь
     * */
    User findByNameAndPassword(String name, String password);
}
