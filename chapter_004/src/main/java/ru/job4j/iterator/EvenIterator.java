package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Class EvenGenerator возвращает итератор четных чисел.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 30.06.2017
 */
public class EvenIterator implements Iterator {
    /**Четное число.*/
    private int evenNumber;
    /**
     * Конструктор класса EvenIterator.
     * @param evenNumber - начальное значение четного числа
     * */
    public EvenIterator(int evenNumber) {
        this.evenNumber = evenNumber;
    }

    /**
     * Проверяет наличие доступных четных чисел.
     * @return true если есть еще четное число
     * */
    @Override
    public boolean hasNext() {
        return evenNumber  != Integer.MIN_VALUE + 1;
    }

    /**
     * Возвращает четное число.
     * Переводит каретку на следующее четно число.
     * @return элемент матрицы
     * */
    @Override
    public Object next() {
        int tmp = evenNumber;
        evenNumber += 2;
        return tmp;

    }
}
