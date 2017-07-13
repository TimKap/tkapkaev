package ru.job4j.map;

import java.util.Iterator;

/**
 * Class Directory описывает справочник.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 13.07.2017
 * @param <T> - тип ключа
 * @param <V> - тип значения
 */
public class Directory<T, V> implements Iterable<V> {
    /** Справочник. */
    private Node<T, V>[] table;

    /** Максимальный объем справочника. */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /** Конструктор класса Directory.
     * @param capacity - объем справочника
     * */
    public Directory(int capacity) {
        table = new Node[tableSizeFor(capacity)];
    }

    /**
     * Округляет объем справочника до ближайшего (большего) числа, равного степени двойки.
     * @param cap - объем, заданный пользователем
     * @return объем справочника
     * */
    private int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /**
     * Добавляет элемент в справочник.
     * @param key - ключ добавляемого элемента
     * @param value - значение добавляемого элемента
     * @return true, если элемент добавлен
     * */
    public boolean insert(T key, V value) {
        int hash = key.hashCode();
        int n = (table.length - 1);
        if (table[n & hash] == null) {
            table[n & hash] = new Node<T, V>(key, value);
            return true;
        } else {
            return false;
        }
    }
    /**
     * Возвращает значение по заданному ключу.
     * @param key - ключ
     * @return значение
     * */
    public V get(T key) {
        int n = table.length - 1;
        int hash = key.hashCode();
        Node<T, V> node = table[n & hash];
        return node != null ? node.getValue() : null;
    }
    /**
     * Удаление элемента из справочника.
     * @param key - ключ
     * @return true, если элемент был удален
     * */
    public boolean delete(T key) {
        int n = table.length - 1;
        int hash = key.hashCode();
        if (table[n & hash] != null) {
            table[n & hash] = null;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Возвращает таблицу справочника.
     * @return таблица справочника.
     * */
    public Node<T, V>[] getTable() {
        return table;
    }
    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            /** Индекс декущего элемента справочника. */
            private int index = -1;
            /**
             * Проверяет наличие ненулевых элементов в справочнике.
             * @return true, если такие элементы есть в наличии
             * */
            @Override
            public boolean hasNext() {
                return getNextIndex() != -1;
            }

            /**
             * Возвращает следующее значение из справочника.
             * @return значение из справочника
             * */
            @Override
            public V next() {
                index = getNextIndex();
                return table[index].getValue();
            }

            /**
             * Возвращает индекс на следующий элемент справочника.
             * @return индекс ненулевого элемента справочника, -1 если такие элементы отсутствуют
             * */
            private int getNextIndex() {
                int i;
                for (i = index + 1; i < table.length; i++) {
                    if (table[i] != null) {
                        break;
                    }
                }
                return i < table.length ? i : -1;
            }
        };
    }

    /**
     * Class Node описывает узел.
     * @param <K> - тип ключа
     * @param <V> - тип значения
     * */
    static class Node<K, V> {

        /** Ключ узла. */
        private final K key;

        /** Знгачение узла. */
        private V value;

        /**
         * Конструктор для класса Node.
         * @param key - ключ
         * @param value - значение узла
         * */
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Возвращает значение для узла.
         * @return начение узла.
         * */
        V getValue() {
            return value;
        }
    }
}
