package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;

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
        int i;
        for (i = 0; i < numbers.size(); i++) {
            if (isPrime(numbers.get(i))) {
                break;
            }
        }
        index = i;
    }

    /**
     * Тест числа на простоту.
     * @param n - целое число
     * @return true, если число простое.
     * */
    private static boolean isPrime(int n) {
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
     * Проверяет наличие доступных простых чисел.
     * @return true если есть еще простое число
     * */
    @Override
    public boolean hasNext() {
        return index < numbers.size();
    }

    /**
     * Возвращает простое число.
     * Переводит каретку на следующее простое число.
     * @return элемент матрицы
     * */
    @Override
    public Object next() {
        int tmp = numbers.get(index);
        while (++index < numbers.size()) {
            if (isPrime(numbers.get(index))) {
                break;
            }
        }
        return tmp;
    }
}
