package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class EvenGenerator возвращает итератор четных чисел.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 30.06.2017
 */
public class EvenIterator implements Iterator {
    /**
     * Числовая последовательность.
     */
    private List<Integer> numbers;
    /**
     * Индекс, указывающий на четное число.
     */
    private int index;

    /**
     * Конструктор класса EvenIterator.
     *
     * @param number - числовая последовательность
     */
    public EvenIterator(List<Integer> number) {
        this.numbers = number;
        index = -1;
    }

    /**
     * Возвращает позицию следующего четного числа.
     * @param currentIndex - текущая позици четного числа
     * @return позиция четного числа: -1 в случае отсутствия четного числа
     */
    private int getNextIndexForEvenNumber(int currentIndex) {
        for (int i = ++currentIndex; i < numbers.size(); i++) {
            if ((numbers.get(i) % 2) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Проверяет наличие доступных четных чисел.
     * @return true если есть еще четное число
     */
    @Override
    public boolean hasNext() {
        return getNextIndexForEvenNumber(index) != -1;
    }

    /**
     * Возвращает четное число.
     * Переводит каретку на следующее четно число.
     * @return элемент матрицы
     * @throws NoSuchElementException при отсутствии четных элементов
     */
    @Override
    public Object next() {
        int nextIndex = getNextIndexForEvenNumber(index);

        if (nextIndex != -1) {
            index = nextIndex;
        }

        int evenNumber;
        try {
            evenNumber = numbers.get(nextIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
        return evenNumber;
    }
}
