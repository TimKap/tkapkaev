package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Class UnionIterator возвращает итератор целых чисел.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 03.07.2017
 */
public class UnionIterator {

    /**
     * Метод формирует из нескольких итератеров целых чисел один общий итератор целых чисел.
     * @param it - итератор, которой возвращает итераторы на целые числа.
     * @return - итератор целых чисел
     * */
    public static Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<Integer>() {
            /* Блок начальной инициализации */
            {
                if (it.hasNext()) {
                    sIterator = it.next();
                }
            }

            /** Итератор целых чисел.*/
            private Iterator<Integer> sIterator;
            /**
             * Проверяет наличие целых чисел.
             * @return true, если возможно вернуть целое число
             * */
            @Override
            public boolean hasNext() {
                return it.hasNext() || sIterator.hasNext();
            }

            /**
             * Возвращает целое число.
             * */
            @Override
            public Integer next() {
                Integer number = sIterator.next();
                if (!sIterator.hasNext() && it.hasNext()) {
                    sIterator = it.next();
                }
                return number;
            }
        };
    }
}
