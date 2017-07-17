package ru.job4j.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;



/**
 * Class Tree описывает структуру данных дерево.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 14.07.2017
 * @param <E> тип данных, который хранится в дереве
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    /** Корень дерева. */
    private Node<E> root;

    /**
     * Конструктор класса Tree.
     * @param rootValue - значение корня дерева;
     * */

    public Tree(E rootValue) {
        root = new Node<>(rootValue);
    }
    /**
     * Добавляет значение в дерево.
     * @param parentValue - значение родительского узла
     * @param childValue - значение дочернего узла (добавляемый узел)
     * @return true, если элемент был добавлен
     * */
    @Override
    public boolean add(E parentValue, E childValue) {

        /* */
        if (parentValue.compareTo(childValue) == 0) {
            return false;
        }

        if (searchNode(childValue) != null) {
            return false;
        }
        Node<E> node = searchNode(parentValue);

        /* Вставка дочернего узла. */
        if (node != null) {
            node.addChildrenNode(childValue);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Поиск узла с запрашиваемым значением в дереве.
     * @param value - значние узла
     * @return узел с искомым значением, null, если узел не найден.
     * */
    public Node<E> searchNode(E value) {
        List<Node<E>> nodesLevel = new LinkedList<>();

        /* Формирование 0-ого уровня */
        nodesLevel.add(root);

        /* Искомый узел */
        Node<E> searchingNode = null;

        /*Поиск узла с переходм на следующий уровень */
        while (nodesLevel.size() != 0) {
            /* Поиск узла c указанным значением на уровне */
            searchingNode = searchNodeOnTheLevel(value, nodesLevel);

            if (searchingNode != null) {
                break;
            }
            /* Переход на следующий уровень узлов */
            nodesLevel = formNextNodesLevel(nodesLevel);
        }
        return searchingNode;
    }

    /**
     *  Поиск узла с запрашиваемым значением на уровне.
     *  @param value - значение искомого узла
     * @param nodes - список дочерних узлов
     * @return узел, если поиск дал результат и null, если узел не обнаружен
     * */
    private Node<E> searchNodeOnTheLevel(E value, List<Node<E>> nodes) {
        for (Node<E> node : nodes) {
            if (node.getValue().compareTo(value) == 0) {
                return node;
            }
        }
        return null;
    }

    /**
     * Формирует следующий уровень узлов.
     * @param preNodesLevel - предыдущий уровень узлов
     * @return следующий уровень узлов
     * */
    private List<Node<E>> formNextNodesLevel(List<Node<E>> preNodesLevel) {
        List<Node<E>> nextNodesLevel = new LinkedList<>();
        for (Node<E> node : preNodesLevel) {
            if ((node.getChildren() != null) && (node.getChildren().size() != 0)) {
                nextNodesLevel.addAll(node.getChildren());
            }
        }
        return nextNodesLevel;
    }

    /**
     * Возвращает корень дерева.
     * @return корень дерева
     * */
    public Node<E> getRoot() {
        return root;
    }

    /**
     * Возвращает итератор для структуры данных дерево.
     * @return итератор
     * */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /** Текущий уровень дерева с узлами. */
           private List<Node<E>> nodesLevel = new LinkedList<>();
            /** Итератор узлов для текущего уровня. */
           private Iterator<Node<E>> iterator;
            /*Инициализация 0-ого уровня*/
            {
                nodesLevel.add(root);
                iterator = nodesLevel.iterator();
            }

            @Override
            public boolean hasNext() {
                return nodesLevel.size() != 0;
            }

            @Override
            public E next() {

                E value = iterator.next().getValue();
                if (!iterator.hasNext()) {
                    nodesLevel = formNextNodesLevel(nodesLevel);
                    iterator = nodesLevel.iterator();
                }
                return value;
            }
        };
    }

    /**
     * Class Node описывает дерево.
     * @author Timur Kapkaev (timur.kap@yandex.ru)
     * @version $Id$
     * @since 16.06.2017
     * @param <E> тип значения узла
     * */
    class Node<E> {
        /** Ссылки на другие связанные узлы.*/
        private List<Node<E>> children = new LinkedList<>();

        /** Значение узла. */
        private E value;

        /**
         * Конструктор класса Node.
         * @param value - значение узла.
         * */
        Node(E value) {
            this.value = value;
        }
        /**
         * Возвращает значение узла.
         * @return значение узла
         * */
        E getValue() {
            return value;
        }
        /**
         * Возвращает связанные узлы.
         * @return связанные узлы
         * */
        List<Node<E>> getChildren() {
            return children;
        }

        /**
         * Добавляет узлу дочерний узел.
         * @param value - значение дочернего узла
         * */
        void addChildrenNode(E value) {
            children.add(new Node<>(value));
        }
    }
}


