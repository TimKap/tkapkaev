package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class SimpleLinkedContainer описывает связный список.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 09.07.2017
 * @param <E> - тип элемента связного списка
 */
public class SimpleLinkedContainer<E> implements Iterable<E> {

    /** Начала связного списка. */
    private Node<E> first;
    /** Конец связного списка. */
    private Node<E> last;
    /** Размер связного списка. */
    private int size = 0;


    /**
     * Добавляет узел в конец связного списка.
     * @param e - значение узла
     * */
    public void add(E e) {
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
     * @param index - индекс элемента
     * @return элемент связного списка
     */
    public E get(int index) {
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

    /**
     * Удаляет последний узел списка.
     * @param last - последний узел списка
     * @return значение последнего узла
     * */
    private E unlinkLast(Node<E> last) {
        Node<E> prev = last.getPrev();
        E value = last.getValue();
        this.last = prev;
        if (prev != null) {
            prev.setNext(null);
        } else {
            first = null;
        }
        size--;
        return value;
    }

    /**
     * Удаляет первый узел из списка.
     * @param first - первый узел списка
     * @return значение первого узла.
     * */
    private E unlinkFirst(Node<E> first) {
        Node<E> next = first.getNext();
        E value = first.getValue();
        this.first = next;
        if (next != null) {
            next.setPrev(null);
        } else {
            last = null;
        }
        size--;
        return value;
    }

    /**
     * Удаляет значение из начала списка.
     * @return значение из начала списка
     * */
    public E removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return unlinkFirst(first);
    }
    /**
     * Удаляет элемент из конца списка.
     * @return значение из конца списка
     * */
    public E removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        return unlinkLast(last);
    }
    /**
     * Возвращает значение первого узла связного списка.
     * @return значение первого узла
     * */
    public E getFirst() {
        return first != null ? first.getValue() : null;
    }

    /**
     * Возвращает значение последнего узла связного списка.
     * @return значение последнего узла
     * */
    public E getLast() {
        return last != null ? last.getValue() : null;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            /** Следующий узел. */
            private Node<E> nextNode = first;

            @Override
            public boolean hasNext() {
                return nextNode != null;
            }

            @Override
            public E next() {
                Node<E> tmp = nextNode;
                nextNode = nextNode.getNext();
                return tmp.getValue();
            }
        };
    }


    /**
     * Class Node описывает узел связанного списка.
     * @author Timur Kapkaev (timur.kap@yandex.ru)
     * @version $Id$
     * @since 09.07.2017
     * @param <E> - тип содержимого узла.
     * */
    private static class Node<E> {
        /** Содержиме узла.*/
        private E value;
        /** Ссылка на предыдущий узел.*/
        private Node prev;
        /** Ссылка на следующий узел. */
        private Node next;

        /** Конструктор класса Node.
         * @param value - содержимое узла
         * @param prev - ссылка на пердыдущий узел
         * @param next - ссылка на следующий узел
         * */
        private Node(E value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        /**
         * Возвращает значение узла.
         * @return значение узла
         * */
        public E getValue() {
            return value;
        }

        /**
         * Устанавливает значение узла.
         * @param value - значение узла
         * */
        public void setValue(E value) {
            this.value = value;
        }

        /**
         * Возвращает ссылку на предыдущий узел.
         * @return ссылку на предыддущий узел
         * */
        public Node getPrev() {
            return prev;
        }

        /**
         * Устанавливает ссылку на предыдущий узел.
         * @param prev - ссылка на предыдущий узел
         * */
        public void setPrev(Node prev) {
            this.prev = prev;
        }

        /**
         * Возвращает ссылку на следующий узел.
         * @return ссылку на следующий узел
         * */
        public Node getNext() {
            return next;
        }

        /**
         * Устанавливает ссылку на следующий узел.
         * @param next - ссылка на следующий узел
         * */
        public void setNext(Node next) {
            this.next = next;
        }
    }
}
