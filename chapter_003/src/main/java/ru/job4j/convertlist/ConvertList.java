package ru.job4j.convertlist;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.Collections;

/**
 * Class Convert описывает прямое и обратное преобразование Списка в мнокомерный массив.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 20.06.2017
 */
public class ConvertList {

    /**
     * Преобразование двумерного массива в последовательность элементов.
     * @param array - двумерный массив
     * @return список элементов
     * */
    public List<Integer> toList(int[][]array) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                list.add(array[i][j]);
            }
        }
        return list;
    }

    /**
     * Преобразует список элемнтов в двумерный массив.
     * @param list - список элементов
     * @param rows - число строк массива
     * @return двумерный массив
     * */
    public int[][] toArray(List<Integer> list, int rows) {
        int columns = list.size() / rows;
        Iterator<Integer> iterator = list.iterator();
        if ((list.size() % rows) != 0) {
            columns++;
        }

        int[][] array = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if ((i * columns + j) < list.size()) {
                    array[i][j] = iterator.next();
                } else {
                    array[i][j] = 0;
                }
            }
        }

        return array;

    }



//    public static void main(String[] args) {
//
//        List<Integer> list = new ArrayList<Integer>();
//        Collections.addAll(list, 7, 6, 5, 4, 3, 2, 1);
//
//        ConvertList convertor = new ConvertList();
//        int [][] mas = convertor.toArray(list, 2 );
//        for (int i = 0 ; i < mas.length; i++) {
//            for(int j = 0; j < mas[i].length; j++) {
//                System.out.print(mas[i][j] +  " ");
//            }
//            System.out.println();
//        }
//        System.out.println(convertor.toList(mas).toString());
//    }
}
