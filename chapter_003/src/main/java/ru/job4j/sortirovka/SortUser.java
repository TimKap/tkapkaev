package ru.job4j.sortirovka;


import java.util.Comparator;
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

    /***
     * Сортировка списка по длине имени пользлователя.
     * @param list - входной список
     * @return отсортированный список
     */
    public List<User> sortNameLength(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getName().length(), o2.getName().length());
            }
        });

        return list;
    }
    /***
     * Сортировка списка по длине имени и возрасту пользлователя.
     * @param list - входной список
     * @return отсортированный список
     */
    public List<User> sortByAllFields(List<User> list) {
        Comparator<User> comparator = (o1, o2) -> {
            int result;
            result = Integer.compare(o1.getName().length(), o2.getName().length());
            if (result == 0) {
                result = Integer.compare(o1.getAge(), o2.getAge());
            }
            return result;
        };
        list.sort(comparator);

        return list;
    }

}
