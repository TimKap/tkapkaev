package ru.job4j.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class SearchBinaryTree описывает бинрное дерево поиска.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @since 17.07.2017
 * @param <E> тип ключа бинарного дерева поиска
 */
public class SearchBinaryTree<E extends Comparable<E>> implements Iterable {

    /**Корень дерева. */
    private Node<E> root;

    /**
     * Вставка нового ключа.
     * @param key - значение ключа
     * @retrun true, если вставка была выполнена успешно
     * */
    public void add(E key) {
        Node<E> preNode = null;
        Node<E> node = root;
        while (node != null) {
            preNode = node;
            if (key.compareTo(node.getKey()) <= 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        if (preNode == null) {
            root = new Node(key);
        } else {
            if (key.compareTo(preNode.getKey()) <= 0) {
                preNode.setLeft(new Node(key));
            } else {
                preNode.setRight(new Node(key));
            }
        }

     }

    /**
     * Формирует следующий уровень узлов.
     * @param preNodesLevel - предыдущий уровень узлов
     * @return следующий уровень узлов
     * */
    private List<Node<E>> formNextNodesLevel(List<Node<E>> preNodesLevel) {
        List<Node<E>> nextNodesLevel = new LinkedList<>();
        for (Node<E> node : preNodesLevel) {
            if ((node.getChildren().size() != 0)) {
                nextNodesLevel.addAll(node.getChildren());
            }
        }
        return nextNodesLevel;
    }

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
                E key = iterator.next().getKey();
                if (!iterator.hasNext()) {
                    nodesLevel = formNextNodesLevel(nodesLevel);
                    iterator = nodesLevel.iterator();
                }
                return key;
            }
        };
    }

    /**
     * Возвращает корень дерева.
     * @return корень дерева
     * */
    Node<E> getRoot() {
        return root;
    }

    /**
     * Класс Node описывает узел дерева.
     * @author Timur Kapkaev (timur.kap@yandex.ru)
     * @version $Id$
     * @since 17.07.2017
     * @param <E> - тип ключа узла
     * */
    class Node<E> {
        /** Значение ключа узла. */
        private E key;
        /** Левый дочерний узел. */
        private Node<E> left;
        /** Правый дочерний узел. */
        private Node<E> right;

        /**
         * Конструктор класса Node.
         * @param key - значение ключа
         * */
        Node(E key) {
            this.key = key;
        }

        /**
         * Возвращает левый узел.
         * @return левый узел
         * */
        Node<E> getLeft() {
            return left;
        }
        /**
         * Возвращает правый узел.
         * @return правый узел
         * */
        Node<E> getRight() {
            return right;
        }


        /**
         * Устанавливает левый дочерний узел.
         * @param node - узел
         * */
        void setLeft(Node<E> node) {
            left = node;
        }
        /**
         * Устанавливает правый дочерний узел.
         * @param node - узел
         * */
        void setRight(Node<E> node) {
            right = node;
        }

        /**
         * Возвращает ключ узла.
         * @return ключ
         * */
        E getKey() {
            return key;
        }
        /**
         * Возвращает список дочерних узлов.
         * @return дочерние узлы
         * */
        List<Node<E>> getChildren() {
            List<Node<E>> nodes = new LinkedList<>();
            if (left != null) {
                nodes.add(left);
            }
            if (right != null) {
                nodes.add(right);
            }
            return nodes;
        }

    }
}
