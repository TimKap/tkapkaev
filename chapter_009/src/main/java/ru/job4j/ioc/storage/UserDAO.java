package ru.job4j.ioc.storage;

import ru.job4j.ioc.model.User;
/**
 * Class UserDAO описывает объект доступа к данным пользователей.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 08.02.2018
 * */
public class UserDAO {
    /** хранилище пользователей. */
    private final IStorage<User> storage;
    /**
     * Конструктор UserDAO.
     * @param storage - хранилище пользователей.
     * */
    public UserDAO(IStorage<User> storage) {
        this.storage = storage;
    }

    /**
     * Сохраняет пользователя.
     * @param user - пользователь
     * @return сохраненный пользователь (null, если не удалось выполнить сохранение)
     * */
    public User persist(User user) {
        return storage.persist(user);
    }
    /**
     * Возвращает пользователя из хранилища.
     * @param id - id возвращаемого пользователя
     * @return пользователь (null, если пользователя не существует)
     * */
    public User get(long id) {
        return storage.get(id);
    }
    /**
     * Удаляет пользователя из хранилища.
     * @param user - удаляемый пользователь
     * @return удаленный элемент (null, при неудачном удалении)
     * */
    public User remove(User user) {
        return storage.remove(user);
    }
    /**
     * Обновляет пользователя.
     * @param user - обновленный пользователь
     * @return обновленный пользователь (null, при неудачном обновлении)
     * */
    public User update(User user) {
        return storage.update(user);
    }
}
