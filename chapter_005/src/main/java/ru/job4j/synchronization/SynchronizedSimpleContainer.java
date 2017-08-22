package ru.job4j.synchronization;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;


/**
 * Class SimpleContainer описывает контейнер данных.
 *
 * @param <E> - тип элемента контейнера.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 07.07.2017
 */
@ThreadSafe
public class SynchronizedSimpleContainer<E> implements Iterable<E> {


    /**
     * Хранилище данных.
     */
    @GuardedBy(value = "this")
    private Object[] container;
    /**
     * Размер хранилища.
     */
    @GuardedBy(value = "this")
    private int size = 0;
    /**
     * Пустой контейнер.
     */
    private static final Object[] EMPTY_CONTAINER = {};

    /**
     * Конструктор класса SimpleContainer.
     */
    public SynchronizedSimpleContainer() {
        container = EMPTY_CONTAINER;
    }

    /**
     * Конструктор класса SimpleContainer.
     *
     * @param initialCapacity - начальная емкость контейнера
     */
    public SynchronizedSimpleContainer(int initialCapacity) {
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
     *
     * @param value - добавляемый элемент.
     */
    public synchronized void add(E value) {
        ensureCapacity();
        container[size++] = value;

    }

    /**
     * Возвращает элемент контейнера.
     *
     * @param index - позиция элемента в контейнере.
     * @return элемент контейнера
     */
    public synchronized E get(int index) {
        if ((index >= size) || (index < 0)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E) container[index];
    }

    /**
     * Обеспечение необходимой емкости контейнера.
     */
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

            /** счетчик для отслеживания изменения колллекции.*/
            private final int modCounter = size;


            /**
             * Проверяет наличие доступных элементов.
             * @return true, если есть еще доступные элементы.
             * */
            @Override
            public boolean hasNext() {
                boolean result = index < size;
                if (modCounter != size) {
                    throw new ConcurrentModificationException();
                }
                return result;
            }

            /**
             * Возвращает следующий доступный элемент.
             * @return следующий доступный элемент.
             * */
            @Override
            public E next() {
                E result = (E) container[index++];
                if (modCounter != size) {
                    throw new ConcurrentModificationException();
                }
                return result;
            }
        };
    }

    /**
     * Возвращает содержимое контейнера.
     *
     * @return контейнер.
     */
    public synchronized Object[] getContainer() {
        return container;
    }
}

