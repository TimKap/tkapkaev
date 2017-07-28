package ru.job4j.doptask;

import java.util.ArrayList;
import java.util.List;

/**
 * Class SearchMinimum обеспечивает поиск минимальных элементов.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 25.07.2017
 */
public class SearchMinimum {


    /**
     * Возвращает индек левого узла.
     * @param parent - индекс родительского узла
     * @return индекс левого узла
     * */
    private int leftIndex(int parent) {
        return 2 * parent + 1;
    }

    /**
     * Возвращает индекс правого узла.
     * @param parent - индекс родительского узла
     * @return индекс правого узла
     * */
    private int rightIndex(int parent) {
        return 2 * (parent + 1);
    }

    /**
     * Восстанавливает свойство неубывающей пирамиды
     * с одним неправильно расположенным узлом.
     * @param heap - пирамида
     * @param index - индекс неправильно расположенного узла
     * */
    private void recoverPropertyNonDecreasingHeap(List<Integer> heap, int index) {
        /* Индекс родительского узла*/
        int parentIndex;
        /* Индекс узла с минимальным значением */
        int minIndex = index;

        do {
            parentIndex = minIndex;

            int leftIndex = leftIndex(parentIndex);
            int rightIndex = rightIndex(parentIndex);

            if ((leftIndex < heap.size()) && (heap.get(leftIndex) < heap.get(parentIndex))) {
                minIndex = leftIndex;
            }
            if ((rightIndex < heap.size()) && (heap.get(rightIndex) < heap.get(minIndex))) {
                minIndex = rightIndex;
            }
            int buf = heap.get(parentIndex);
            heap.set(parentIndex, heap.get(minIndex));
            heap.set(minIndex, buf);
        } while (minIndex != parentIndex);

    }
    /**
     * Преобразовывает список элементов в неубывающую пирамиду.
     * @param list - список целых чисел (значений узлов)
     * @return структура данных пирамида
     * */
    private List<Integer> buildNonDecreasingHeap(List<Integer> list) {

        List<Integer> heap = new ArrayList<>();
        heap.addAll(list);
        for (int i = (heap.size() / 2) - 1; i >= 0; i--) {
            recoverPropertyNonDecreasingHeap(heap, i);
        }
        return heap;
    }


    /**
    * Поиск первых m минимальных элементов.
    * @param list - контейнер с входными данными
    * @param m - число первых минимальных элементов
    * @return контейнер минимальных элементов
    * */
   public int[] search(List<Integer> list, int m) {

       List<Integer> heap = buildNonDecreasingHeap(list);

       int[] buf = new int[m];

       for (int i = 0; i < m; i++) {
           buf[i] = heap.get(0);
           int last = heap.get(heap.size() - 1);
           heap.set(0, last);
           heap.remove(heap.size() - 1);
           recoverPropertyNonDecreasingHeap(heap, 0);
       }

       return buf;
   }
}
