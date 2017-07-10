package ru.job4j.list;


import java.util.Iterator;

/**
 * Class описывает структуру данных "Очередь".
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 10.07.2017
 * @param <E> - тип данных, которые хранятся в очереди
 */
public class Queue<E> implements Iterable<E> {
    /** Связный список. */
    private SimpleLinkedContainer<E> list = new SimpleLinkedContainer<>();

    /**
     * Дабавление элемента в очередь.
     * @param element - добавляемый в очередь элемент.
     * */
    public void enqueue(E element) {
        list.add(element);
    }

    /**
     * Извлечение элемента из очереди.
     * @return извлекаемый элемент
     * */
    public E dequeue() {
        return list.removeFirst();
    }

    /**
     * Возвращает итератор для очереди.
     * @return итератор
     * */
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
