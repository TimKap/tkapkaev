package ru.job4j.generic;

/**
 * Class UserStore описывает хранилище User.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 07.07.2017
 */

public class UserStore extends BaseStore<User> {
    /**
     * Конструктор класса UserStore.
     * @param size - Объем хранилища UserStore
     * */
    public UserStore(int size) {
        super(size);
    }

}
