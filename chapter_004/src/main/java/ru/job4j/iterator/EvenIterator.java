package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * Class EvenGenerator возвращает итератор четных чисел.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 30.06.2017
 */
public class EvenIterator implements Iterator {
    /**Числовая последовательность.*/
    private List<Integer> evenNumber;
    /** Индекс, указывающий на четное число.*/
    private int index = 0;
    /**
     * Конструктор класса EvenIterator.
     * @param evenNumber - начальное значение четного числа
     * */
    public EvenIterator(List<Integer> evenNumber) {
        this.evenNumber = evenNumber;
        int i;
        for (i = 0; i < evenNumber.size(); i++) {
            if ((evenNumber.get(i) % 2) == 0) {
                break;
            }
        }
        index = i;
    }

    /**
     * Проверяет наличие доступных четных чисел.
     * @return true если есть еще четное число
     * */
    @Override
    public boolean hasNext() {
        return index < evenNumber.size();
    }

    /**
     * Возвращает четное число.
     * Переводит каретку на следующее четно число.
     * @return элемент матрицы
     * */
    @Override
    public Object next() {
        int tmp = evenNumber.get(index);
        while (++index < evenNumber.size()) {
            if  ((evenNumber.get(index) % 2) == 0) {
                break;
            }
        }
        return tmp;
    }
}
