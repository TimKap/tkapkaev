package ru.job4j.generic;

import java.util.Iterator;

/**
 * Class SimpleArray описывает структуру список.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 06.07.2017
 * @param <E> - тип элементов массива
 */
public class SimpleArray<E> {

    /** Массив значений.*/
    private Object[] objects;
    /** Размер массива.*/
    private int size = 0;
    /** Индекс итератора. */
    private int index = 0;
    /**
     * Конструктор класса SimpleArray.
     * @param size - обьем данных
     * */
    public SimpleArray(int size) {

        objects = new Object[size];
    }
    /**
     * Добавляет объект в список.
     * @param o - добавляемый объект
     * */
    public void add(E o) {
        objects[size++] = o;
    }
    /**
     * Возвращает из списка объект с указнным индексом.
     * @param index - номер объекта
     * @return объект из списка
     * */
    public E get(int index) {
        return (E) objects[index];
    }
    /**
     * Удаляет объект из списка.
     * @param index -номер удаляемого элемента
     * @return удаленный элемент
     * */
    public E delete(int index) {
        E element = (E) objects[index];
        System.arraycopy(objects, index + 1, objects, index, size - index - 1);
        objects[--size] = null;
        return element;
    }

    /**
     * Удаляет объект из списка.
     * @param o - даляемый элемент
     * @return удаленный элемент
     * */
    public boolean delete(E o) {
        int i = 0;
        for (i = 0; i < size; i++) {
            if (o.equals(get(i))) {
                break;
            }
        }
        if (i < size) {
            delete(i);
            return true;
        } else {
            return false;
        }
    }
    /**
     * Заменяет указанный элемент.
     * @param index - индекс, модифицируемого элемента.
     * @param e - измененный объект
     * @return старое значение
     * */
    public E update(int index, E e) {
        E oldValue = (E) objects[index];
        objects[index] = e;
        return oldValue;
    }
    /**
     * Заменяет указанный элемент.
     * @param src - заменяющий элемент
     * @param dst - заменяемый элемент
     * @return true, если замена была выполнена
     * */
    public boolean update(E dst, E src) {
        for (int i = 0; i < size; i++) {
            E element = (E) objects[i];
            if (((E) objects[i]).equals(dst)) {
                objects[i] = src;
                return true;
            }
        }
        return false;
    }

    /**
     * Возвращает список оэлеметнов.
     * @return спиисок
     * */
    public Object[] getObjects() {
        return objects;
    }

    /**
     * Возвращает итератор.
     * @return итератор
     * */
    Iterator<E> iterator() {
        return new Iterator<E>() {
            /**
             * Проверяет наличие доступных элементов.
             * @return true если есть еще элементы
             * */
            @Override
            public boolean hasNext() {
                return index < size;
            }

            /**
             * Возвращает значение элемента, на который указывает каретка.
             * Переводит каретку на следующий элемент.
             * @return элемент матрицы
             * */
            @Override
            public E next() {
                return (E) objects[index++];
            }
        };
    }

}
