package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Class MatrixIterotor представляет итератор двумерного массива.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 29.06.2017
 */
public class MatrixIterator implements Iterator {
    /** Двумерный массив.*/
    private int[][] mas;
    /** Размер матрицы.*/
    private int length;
    /** Индекс строк матрицы.*/
    private int i;
    /** Индекс столбцов матрицы. */
    private int j;
    /** Счетчик числа элементов. */
    private int index;


    /** Конструктор класса MAtrixIterartor.
     * @param mas - матрица для которой реализуется итератор
     * */
    public MatrixIterator(int[][] mas) {
        this.mas = mas;
        length = length(mas);
    }
    /**
     * Проверяет наличие доступных элементов элемент коллекции.
     * @return true если есть еще элементы
     * */
    @Override
    public boolean hasNext() {
        return index < length;
    }

    /**
     * Возвращает значение элемента, на который указывает каретка.
     * Переводит каретку на следующий элемент.
     * @return элемент матрицы
     * */
    @Override
    public Object next() {
        if (j  == mas[i].length) {
            i++;
            j = 0;
        }
        index++;
        return mas[i][j++];
    }

    /**
     * Вычисляет число элементов матрицы.
     * @param mas -матрица
     * @return число элементов в матрице
     * */
    private int length(int[][] mas) {
        int sum = 0;
        for (int[] elements:mas) {
            sum += elements.length;
        }
        return sum;
    }


}
