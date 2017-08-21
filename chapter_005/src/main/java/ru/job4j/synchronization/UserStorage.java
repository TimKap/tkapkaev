package ru.job4j.synchronization;

import java.util.HashMap;
import java.util.Map;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class UserStorage описывает хранилище пользователей.
 *
 * @author Timur Kapkaev ()timur.kap@yandex.ru
 * @version $Id$
 * @since 21.08.2017
 */
@ThreadSafe
public class UserStorage {

    /**
     * Контейнер пользователей.
     */
    @GuardedBy(value = "this")
    private Map<Integer, User> container = new HashMap<>();

    /**
     * Добавление пользователя.
     *
     * @param user - пользователь
     */
    public synchronized void add(User user) {
        container.put(user.getId(), user);
    }

    /**
     * Удаление пользователя.
     *
     * @param id - id пользователя
     * @return удаленный пользователь
     */
    public synchronized User delete(int id) {
        return container.remove(id);
    }

    /**
     * Обновление пользователя.
     *
     * @param modifiedUser - модифицированный пользователь
     * @return успешность обновления пользователя
     */
    public boolean update(User modifiedUser) {
        int id = modifiedUser.getId();
        synchronized (this) {
            User user = container.get(modifiedUser.getId());
            if (user == null) {
                return false;
            }
            container.put(id, modifiedUser);
            return true;
        }
    }

    /**
     * Перевод со счета одного пользователя на счет другого пользователя.
     *
     * @param fromId - id пользователя со счета которого снимаются средства
     * @param toId   - id пользователя на счет которого переводятся средства
     * @param amount - размер переводимых средств
     * @return успешность перевода
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User sourceUser = container.get(fromId);
        User targetUser = container.get(toId);
        if (sourceUser == null || targetUser == null) {
            return false;
        }
        if (sourceUser.getAmount() < amount) {
            return false;
        }

        sourceUser.minus(amount);
        targetUser.plus(amount);
        return true;
    }


}
