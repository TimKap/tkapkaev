package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Class SimpleContainer описывает контейнер данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 07.07.2017
 * @param <E> - тип элемента контейнера.
 */
public class SimpleContainer<E> implements Iterable<E> {
    /** Хранилище данных.*/
    private Object[] container;
    /** Размер хранилища.*/
    private int size = 0;
    /** Пустой контейнер.*/
    private static final Object[] EMPTY_CONTAINER = {};

    /**
     * Конструктор класса SimpleContainer.
     * */
    public SimpleContainer() {
        container = EMPTY_CONTAINER;
    }
    /**
     * Конструктор класса SimpleContainer.
     * @param initialCapacity - начальная емкость контейнера
     * */
    public SimpleContainer(int initialCapacity) {
        if (initialCapacity > 0) {
            container = new Object[initialCapacity];
        } else {
            if (initialCapacity == 0) {
                container = EMPTY_CONTAINER;
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * Добавляет элемент в контейнер.
     * @param value - добавляемый элемент.
     * */
    public void add(E value) {
        ensureCapacity();
        container[size++] = value;

    }
    /**
     * Возвращает элемент контейнера.
     * @param index - позиция элемента в контейнере.
     * @return элемент контейнера
     * */
    public E get(int index) {
        if ((index >= size) || (index < 0)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E) container[index];
    }
    /**
     * Обеспечение необходимой емкости контейнера.
     * */
    private void ensureCapacity() {

        if (size == container.length) {
            int newLength = (int) Math.round(1.5 * container.length) + 1;
            container = Arrays.copyOf(container, newLength);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /** индекс итератора. */
            private int index = 0;
            /**
             * Проверяет наличие доступных элементов.
             * @return true, если есть еще доступные элементы.
             * */
            @Override
            public boolean hasNext() {
                return index < size;
            }
            /**
             * Возвращает следующий доступный элемент.
             * @return следующий доступный элемент.
             * */
            @Override
            public E next() {
                return (E) container[index++];
            }
        };
    }

    /**
     * Возвращает содержимое контейнера.
     * @return контейнер.
     * */
    public Object[] getContainer() {
        return  container;
    }
}
