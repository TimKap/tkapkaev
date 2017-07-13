package ru.job4j.set;

import java.util.Iterator;

/**
 * Class HashTable описывает структуру данных хеш-таблицу.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 13.07.2017
 * @param <K> - тип ключа хеш-таблицы
 * @param <V> - тип значения хеш-таблицы
 */
public class HashTable<K, V> {
    /** Хеш-таблица. */
    private Node<K, V>[] table;
    /** Начальная енмкость таблицы. */
    private static final int INITIAL_CAPACITY = 64;

    /**
     * Конструктор класса HashTable.
     * */
    public HashTable() {
        table = (Node<K, V>[]) new Node[INITIAL_CAPACITY];
    }

    /**
     * Добавление элемента в хеш-таблицу.
     * @param key - ключ элемента
     * @param value - значение элемента
     * @return добавленное значение
     * */
    public V put(K key, V value) {
        int hash = key.hashCode();
        Node<K, V> newNode = new Node<>(hash, key, value, null);



        if (table[(table.length - 1) & hash] == null) {
            table[(table.length - 1) & hash] = newNode;
        } else {
            Node<K, V> node = table[(table.length - 1) & hash];
            boolean isContinue = true;

            while (isContinue) {
                if (node.getKey().equals(key)) {
                    node.setValue(value);
                    isContinue = false;
                } else {
                    if (node.getNextNode() == null) {
                        node.setNextNode(newNode);
                        isContinue = false;
                    }
                }
                node = node.nextNode;
            }
        }
        return value;
    }
    /**
     * Возвращает итератор для ключей.
     * @return итератор ключей.
     * */
    public Iterator<K> keyIterator() {
        return new Iterator<K>() {
            /** Узел. */
            private Node<K, V> node = null;
            /** Счетчик ячеек массива. */
            private int index = -1;

            /**
             * Возвращает индекс на следующую непустую ячейку массива.
             * @retrun индекс непустой ячекйки. -1, если ячеек нет.
             * */
            private int getIndex() {
                int i;
                for (i = index + 1; i < table.length; i++) {
                    if (table[i] != null) {
                        break;
                    }
                }
                return i < table.length ? i : -1;
            }

            @Override
            public boolean hasNext() {
                return !((node == null) && (getIndex() == -1));
            }

            @Override
            public K next() {
                if (node == null) {
                    index = getIndex();
                    node = table[index];
                }
                Node<K, V> temp = node;
                node = node.getNextNode();
                return temp.getKey();
            }
        };
    }



    /**
     * Class Node описывает узел хеш-таблицы.
     * @author Timur Kapkaev (timur.kap@yandex.ru)
     * @version $ID$
     * @since 13.07.2017
     * @param <K> - тип ключа узла
     * @param <V> - тип значения узла
     * */
    static class Node<K, V> {
        /** Ключ узла. */
        private final K key;

        /** Знгачение узла. */
        private V value;

        /** Хеш-код ключа. */
        private final int hash;

        /** Ссылка на следующий узел.*/
        private Node<K, V> nextNode;

        /**
         * Конструктор для класса Node.
         * @param hash хеш-код ключа
         * @param key - ключ
         * @param value - значение узла
         * @param nextNode - ссылка на следующий узел.
         * */
        Node(int hash, K key, V value, Node<K, V> nextNode) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.nextNode = nextNode;
        }

        /**
         * Возвращает ключ для узла.
         * @return ключ узла
         * */
        K getKey() {
            return key;
        }
        /**
         * Возвращает значение для узла.
         * @return начение узла.
         * */
        V getValue() {
            return value;
        }
        /**
         * Задает новое значение.
         * @param value - новое значение для узла.
         * @return старое значение узла.
         * */
        V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
        /**
         * Задает новую ссылку на следующий узел.
         * @param nextNode - ссылка на следующий узел.
         * */
        void setNextNode(Node<K, V> nextNode) {
            this.nextNode = nextNode;
        }

        /**
         * Возвращает значение на следующий узел.
         * @return ссылка на следующий узел
         * */
        Node<K, V> getNextNode() {
            return this.nextNode;
        }
    }
}
