package ru.job4j.list;

/**
 * Class LinkedListCyclicity описывает задачу с замыканиями связного списка.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 10.07.2017
 * @param <E> - тип содержимого узла.
 */
public class LinkedListCyclicity<E> {

    /**Ссылка на первый узел списка.*/
    private Node<E> first;
    /** С ылка на последний узел списка. */
    private Node<E> last;
    /**
     * Добавляет узел в список.
     * @param newNode - новый узел
     * */
    public void addNode(Node<E> newNode) {
        if (last != null) {
            last.setNext(newNode);
            last = newNode;
        } else {
            last = newNode;
            first = last;
        }
    }

    /**
     * Проверка наличия цикличности в списке.
     * @return true, если список образует циклическую структуру
     * */
    public boolean hasCycle() {
        /* Внешний счетчик пройденных узлов. */
        int counter = 1;
        /* Крайний узел в пройденном пути узлов. */
        Node<E> node = first;

        while (node != null) {
            /* Внутренний счетчик пройденных узлов. */
            int innerCounter = 1;

            /* Поиск среди пройденныхх узлов крайнего узла. */
            Node<E> innerNode = first;
            while (innerNode != node) {
                innerNode = innerNode.getNext();
                innerCounter++;
            }

            if (counter != innerCounter) {
                /*Обнаружение среди пройденных узлов крайнего узла.*/
                return true;
            }
            /* Переход к следующему узлу списка. */
            node = node.getNext();
            counter++;
        }
        return false;
    }

}

/**
 * Class Node описывает узел связанного списка.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 10.07.2017
 * @param <E> - тип содержимого узла.
 * */
    class Node<E> {
    /** Ссылка на следующий узел списка.*/
    private Node<E> next;
    /** Значение узла. */
    private E value;

    /**
     * Конструктор класса Node.
     * @param value - начение узла
     * */
     Node(E value) {
        this.value = value;
    }

    /**
     * Возвращает ссылку на следующий узел.
     * @return ссылка на узел.
     * */
    public Node<E> getNext() {
        return next;
    }

    /**
     * Задает ссылку на следующий узел.
     * @param next - ссылка наследующий узел
     * */
    public void setNext(Node<E> next) {
        this.next = next;
    }
}
