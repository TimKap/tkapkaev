package ru.job4j.conditionsynchronization.threadpool;

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
     * @throws InterruptedException - прерывание потока, когда он находился в состоянии wait
     */
    public synchronized E dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
                wait();
        }
        return queue.poll();
    }
}
