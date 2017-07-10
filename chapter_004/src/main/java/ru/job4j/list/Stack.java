package ru.job4j.list;

import java.util.Iterator;

/**
 * Class Stack реализует структуру данных стек.
 * @author Timur Kapkaev (timur,kap@yandex.ru)
 * @version $Id$
 * @since 10.07.2017
 * @param <E> - дип данных, хранящихся в стеке
 */
public class Stack<E> implements Iterable<E> {
    /** Связный список. */
    private SimpleLinkedContainer<E> list = new SimpleLinkedContainer<>();
    /**
     * Добавляет элемент в стек.
     * @param element - вставляемый элемент
     * */
    public void push(E element) {
        list.add(element);
    }

    /**
     * Извлекает элемент из стека.
     * @return извлекаемый элемент
     * */
    public E pop() {
        return list.removeLast();
    }

    /**
     * Возвращает итератор для очереди.
     * @return итератор
     * */
    public Iterator<E> iterator() {
        return list.iterator();
    }

}
