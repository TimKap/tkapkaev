package ru.job4j.convertlist;
import java.util.HashMap;
import java.util.List;

/**
 * Class UserConvert описывает работу со списками пользователей.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version &$Id$
 * @since 22.06.2017
 */
public class UserConvert {
    /**
     * Формирует из списка пользователей таблицу пользователей с ключом по Id.
     * @param list - список пользователей
     * @return таблица с пользователями
     * */
    HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> map = new HashMap<Integer, User>();
        for (User users:list) {
            map.put(users.getId(), users);
        }
        return map;
    }
}
