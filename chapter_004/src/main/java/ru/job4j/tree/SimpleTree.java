package ru.job4j.tree;

/**
 * Interface SimpleTree содержит методы структуры данных дерево.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 14.07.2017
 * @param <E> - тип элементов дерева
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    /**
     * Добавляет значение в дерево.
     * @param parent - значение родительского узла
     * @param child - значение дочернего узла (добавляемый узел)
     * @return true, если элемент был добавлен
     * */
    boolean add(E parent, E child);

}
