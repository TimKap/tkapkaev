package ru.job4j.set;
import java.util.Iterator;

/**
 * Class описывает структуру данных "Множество" (хеш-таблица).
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 11.07.2017
 * @param <E> - тип данных, хранящихся в таблице
 */
public class HashTableSet<E> implements Iterable<E> {
    /** Хеш-таблица. */
    private final HashTable<E, Object> hashTable = new HashTable<E, Object>();

    /** "Пустое" значение. */
    private static final Object DUMMY_VALUE = new Object();

    /**
     * Добавление элемента в множество.
     * @param value - добавляемое значение
     * */
    public void add(E value) {
        hashTable.put(value, DUMMY_VALUE);
    }

    /**
     * Возвращает итератор.
     * @return итератор
     * */
    @Override
    public Iterator<E> iterator() {
        return hashTable.keyIterator();
    }

}
