package ru.job4j.generic;

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
     * Возвращает список оэлеметнов.
     * @return спиисок
     * */
    public Object[] getObjects() {
        return objects;
    }

}
