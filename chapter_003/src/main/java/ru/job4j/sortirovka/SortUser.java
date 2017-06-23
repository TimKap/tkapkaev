package ru.job4j.sortirovka;


import java.util.Set;
import java.util.TreeSet;
import java.util.List;

/**
 * Class SortUser выполняет сортировку пользователей.
 *@author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 23.06.2017
 */
public class SortUser {
    /**
     * Перобразует список в упорядоченное по возрасту множество пользователей.
     * @param list - список пользователей
     * @return упорядоченное по возрастанию множество
     * */
    public Set<User> sort(List<User> list) {
        TreeSet<User> tree = new TreeSet<User>();
        tree.addAll(list);
        return tree;
    }
}
