package ru.job4j.generic;

/**
 * Class RoleStore описывает хранилище Role.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 07.07.2017
 */
public class RoleStore extends BaseStore<User> {
    /**
     * Конструктор класса RoleStore.
     * @param size - Объем хранилища RoleStore
     * */
    public RoleStore(int size) {
        super(size);
    }
}
