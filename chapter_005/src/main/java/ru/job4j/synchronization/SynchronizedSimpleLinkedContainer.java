package ru.job4j.synchronization;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class SynchronizedSimpleLinkedContainer описывает потокобезопасный связанный список.
 *
 * @author Timur Kpakev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 21.08.2017
 * @param <E> - тип хранящегося объекта в контейнере
 */
@ThreadSafe
public class SynchronizedSimpleLinkedContainer<E> implements Iterable<E> {
    /**
     * Начала связного списка.
     */
    @GuardedBy(value = "this")
    private Node<E> first;
    /**
     * Конец связного списка.
     */
    @GuardedBy(value = "this")
    private Node<E> last;
    /**
     * Размер связного списка.
     */
    @GuardedBy(value = "this")
    private int size = 0;


    /**
     * Добавляет узел в конец связного списка.
     *
     * @param e - значение узла
     */
    public synchronized void add(E e) {
        Node<E> newNode = new Node(e, last, null);

        if (last == null) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
        size++;

    }

    /**
     * Возвращает элемент связного списка.
     *
     * @param index - индекс элемента
     * @return элемент связного списка
     */
    public synchronized E get(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IllegalArgumentException();
        }

        Node<E> node;
        if (index < (size >> 1)) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.getPrev();
            }
        }
        return node.getValue();
    }


    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            /** Следующий узел. */
            private Node<E> nextNode = first;

            /** счетчик для отслеживания изменения колллекции.*/
            private final int modCounter = size;

            @Override
            public boolean hasNext() {
                boolean result = nextNode != null;
                if (modCounter != size) {
                    throw new ConcurrentModificationException();
                }
                return result;
            }

            @Override
            public E next() {
                Node<E> tmp = nextNode;
                nextNode = nextNode.getNext();
                if (modCounter != size) {
                    throw new ConcurrentModificationException();
                }
                return tmp.getValue();
            }
        };
    }


    /**
     * Class Node описывает узел связанного списка.
     *
     * @param <E> - тип содержимого узла.
     * @author Timur Kapkaev (timur.kap@yandex.ru)
     * @version $Id$
     * @since 09.07.2017
     */
    private static class Node<E> {
        /**
         * Содержиме узла.
         */
        private E value;
        /**
         * Ссылка на предыдущий узел.
         */
        private Node prev;
        /**
         * Ссылка на следующий узел.
         */
        private Node next;

        /**
         * Конструктор класса Node.
         *
         * @param value - содержимое узла
         * @param prev  - ссылка на пердыдущий узел
         * @param next  - ссылка на следующий узел
         */
        private Node(E value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        /**
         * Возвращает значение узла.
         *
         * @return значение узла
         */
        public E getValue() {
            return value;
        }

        /**
         * Устанавливает значение узла.
         *
         * @param value - значение узла
         */
        public void setValue(E value) {
            this.value = value;
        }

        /**
         * Возвращает ссылку на предыдущий узел.
         *
         * @return ссылку на предыддущий узел
         */
        public Node getPrev() {
            return prev;
        }

        /**
         * Устанавливает ссылку на предыдущий узел.
         *
         * @param prev - ссылка на предыдущий узел
         */
        public void setPrev(Node prev) {
            this.prev = prev;
        }

        /**
         * Возвращает ссылку на следующий узел.
         *
         * @return ссылку на следующий узел
         */
        public Node getNext() {
            return next;
        }

        /**
         * Устанавливает ссылку на следующий узел.
         *
         * @param next - ссылка на следующий узел
         */
        public void setNext(Node next) {
            this.next = next;
        }
    }
}
