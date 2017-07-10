package ru.job4j.set;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Class SimpleSet описывает структуру данных "Множество".
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 10.07.2017
 * @param <E> - тип данных, хранящихсяв Set
 */
public class SimpleSet<E> implements Iterable<E> {
    /** Хранилище данных.*/
    private Object[] container;
    /** Размер хранилища.*/
    private int size = 0;
    /** Пустой контейнер.*/
    private static final Object[] EMPTY_CONTAINER = {};

    /**
     * Конструктор класса SimpleContainer.
     * */
    public SimpleSet() {
        container = EMPTY_CONTAINER;
    }

    /**
     * Проверка наличия элемнта в множестве.
     * @param element - проверяемый элемент
     * @return true, если множество уже содержит элемент
     * */
    private boolean isContain(E element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(container[i])) {
                return true;
            }
        }
        return false;
    }
    /**
     * Добавляет элемент в контейнер.
     * @param value - добавляемый элемент.
     * */
    public void add(E value) {
        if (!isContain(value)) {
            ensureCapacity();
            container[size++] = value;
        }

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
