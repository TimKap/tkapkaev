package ru.job4j.set;

import java.util.Iterator;

/**
 * Class SimpleLinkedSet описывает структуру данных "Множество" (связный список).
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 10.07.2017
 * @param <E> - тип данных, хранящихсяв Set
 */
public class SimpleLinkedSet<E> implements Iterable<E> {
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

        if (!isContain(e)) {
            Node<E> newNode = new Node(e, last, null);
            if (last == null) {
                first = newNode;
            } else {
                last.setNext(newNode);
            }
            last = newNode;
            size++;
        }
    }

    /**
     * ППроверка наличия элемента в множестве.
     * @param element - проверяемый элемент.
     * @return true, если множество содержит элемент
     * */
    public boolean isContain(E element) {
        Node<E> node = first;
        while (node != null) {
            if (element.equals(node.getValue())) {
                return true;
            }
            node = node.getNext();
        }
        return false;
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
