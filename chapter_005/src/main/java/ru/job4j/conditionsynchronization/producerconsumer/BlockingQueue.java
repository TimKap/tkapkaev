package ru.job4j.conditionsynchronization.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class BlockingQueue описывает блокирующую очередь.
 *
 * @param <E> - тип элемента в очереди
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 24.08.2017
 */
public class BlockingQueue<E> {
    /**
     * очередь элементов.
     */
    private Queue<E> queue = new LinkedList<>();

    /**
     * примитив синхронизации.
     */
    private Object lock = new Object();


    /**
     * Добавить элемент в очередь.
     *
     * @param value - добавляемое значение
     */
    public synchronized void enqueue(E value) {
        queue.add(value);
        notify();
    }

    /**
     * Извлекает эдемент из очереди.
     *
     * @return элемент очереди
     */
    public synchronized E dequeue() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.poll();
    }
}
