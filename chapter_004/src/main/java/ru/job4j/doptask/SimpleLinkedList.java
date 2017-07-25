package ru.job4j.doptask;

/**
 * Class SimpleLinkedList описывает простой связанный список.
 * @author Timur Kapkaev (timur.kap@ynadex.ru)
 * @version $Id$
 * @since 25.07.2017
 * @param <E> - тип элементов связанного списка
 */
public class SimpleLinkedList<E> {

    /** Начало связанного списка. */
    private Node<E> first;
    /** Конец связанного списка. */
    private Node<E> last;

    /** Размер связанного списка. */
    private int size;

    /**
     * Добавляет значение в список.
     * @param e - добавляемое знеачение
     * */
    public void add(E e) {
        Node<E> node = new Node<>(e, null);
        if (first == null) {
            first = node;
        } else {
            last.setNextNode(node);
        }
        last = node;
        size++;
    }
    /**
     * Возвращает i - ый элемент списка.
     * @param index - индекс искомого узла
     * @return значение i-ого узла
     * */
    private E get(int index) {
        Node<E> node = first;
        if (index < size) {
            for (int i = 0; i < index; i++) {
                node = node.getNextNode();
            }
            return node.getValue();
        } else {
            return null;
        }
    }

    /**
     * Возвращает k-ый элемент с конца.
     * @param k - номер элемента с конца.
     * @return значение k - ого элемента с конца
     * */
    public E getK(int k) {
        int index = size - k;
        if (index < 0) {
            return null;
        } else {
            return get(index);
        }
    }

    /**
     * Class Node описывает узел связанного списка.
     * @author Timur Kapkaev (timur.kap@yandex.ru)
     * @version $Id$
     * @since 25.07.2017
     * @param <E> - тип значения узла
     * */
     class Node<E> {
        /** Ссылка на следующий узел. */
        private Node<E> nextNode;
        /** Значение узла. */
        private final E value;

        /**
         * Конструктор класса Node.
         * @param value - значение узла
         * @param nextNode - ссылка на следующий узел
         * */
        Node(E value, Node<E> nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }
        /**
         * Возвращает следующий узел.
         * @return следующий узел
         * */
        Node<E> getNextNode() {
            return nextNode;
        }
        /**
         * Задает следующий узел.
         * @param nextNode - ссылка на следующий узел
         * */
        void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }

        /**
         * Возвращает значение узла.
         * @return значение узла
         * */
        E getValue() {
            return value;
        }
    }
}
