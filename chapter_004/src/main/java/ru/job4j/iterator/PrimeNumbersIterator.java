package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class NaturalNumberIterator описывает итератор для натуральных чисел.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @since 03.07.2017
 */
public class PrimeNumbersIterator implements Iterator {

    /** Список чисел.*/
    private List<Integer> numbers;
    /** Индекс простых чисел.*/
    private int index;

    /**
     * Конструктор класса NaturalNumbersIterator.
     * @param numbers - последовательность чисел.
     * */
    public PrimeNumbersIterator(List<Integer> numbers) {
        this.numbers = numbers;
        index = -1;
    }

    /**
     * Тест числа на простоту.
     * @param n - целое число
     * @return true, если число простое.
     * */
    private boolean isPrime(int n) {
        int  lim = (int) Math.sqrt(n);
        boolean isPrime = true;
        for (int i = 2; i <= lim; i++) {
            if ((n % i) == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    /**
     * Возвращает позицию следующего простого числа.
     * @param currentIndex - текущая позици простого числа
     * @return позиция простого числа: -1 в случае отсутствия четного числа
     */
    private int getNextIndexForPrimeNumber(int currentIndex) {
        for (int i = ++currentIndex; i < numbers.size(); i++) {
            if (isPrime(numbers.get(i))) {
                return i;
            }
        }
        return -1;
    }
    /**
     * Проверяет наличие доступных простых чисел.
     * @return true если есть еще простое число
     * */
    @Override
    public boolean hasNext() {
        return getNextIndexForPrimeNumber(index) != -1;
    }

    /**
     * Возвращает простое число.
     * Переводит каретку на следующее простое число.
     * @return элемент матрицы
     * */
    @Override
    public Object next() {
        int nextIndex = getNextIndexForPrimeNumber(index);

        if (nextIndex != -1) {
            index = nextIndex;
        }

        int primeNumber;
        try {
            primeNumber = numbers.get(nextIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }

        return primeNumber;
    }
}
