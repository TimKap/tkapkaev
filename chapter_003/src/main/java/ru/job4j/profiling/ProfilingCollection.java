package ru.job4j.profiling;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.Random;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class ProfilingCollection реализует профилирование методов коллекций.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 31/05/2017
 */
public class ProfilingCollection {

    /** Длина случайной строки.*/
    private static final int STR_LEN = 15;

    /** Набор симворлов из которых формируется строка. */
    private static final String ALFABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    /** Число элементов коллекции.*/
    private static final int  VOLUME = 2000000;

    /**
     * Формирование случайной строки.
     * @return случайная строка
     * */
    private String getRandomString() {
        StringBuilder randomString = new StringBuilder();
        Random rn = new Random();
        for (int i = 0; i < ALFABET.length(); i++) {
            char symbol = ALFABET.charAt(rn.nextInt(ALFABET.length()));
            randomString.append(symbol);
        }
        return randomString.toString();
    }

    /**
     * Профилирование метода вставки элемнта в коллекцию.
     * @param collection - коллекция в которую добавляется элемент
     * @param line - вводимая строка
     * @param amount - количество вводимых строк
     * @return время (нс)
     * */
    public long add(Collection<String> collection, String line, int amount) {
        long start = System.nanoTime();
        for (int i = 0; i < amount; i++) {
            collection.add(line + i);
        }
        long end = System.nanoTime();
        return end - start;
    }

    /**
     * Профилирование метода удаления элемнта в коллекцию.
     * @param collection - коллекция в которую добавляется элемент
     * @param amount - количество вводимых строк
     * @return время(нс)
     * */
    public long delete(Collection<String> collection, int amount) {
        long start = System.nanoTime();
        Iterator<String> iterator = collection.iterator();

        for (int i = 0; i < amount; i++) {
            iterator.next();
            iterator.remove();
        }

        long end = System.nanoTime();
        return end - start;
    }

    /**
     * Точка входа.
     * @param args - строки, полученные из консольного ввода.)
     */
    public static void main(String[] args) {
        ProfilingCollection profiling = new ProfilingCollection();

        ArrayList<String> arrayList = new ArrayList<String>();
        LinkedList<String> linkedList = new LinkedList<String>();
        TreeSet<String> treeSet =  new TreeSet<String>();

        Map<Integer, Long> mapAdd = new TreeMap<Integer, Long>();
        Map<Integer, Long> mapDelete = new TreeMap<Integer, Long>();
        int[] dataNum = {100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000};


        /*ArrayList*/
        for (int j = 0; j < dataNum.length; j++) {
            long accumulator = 0;
            accumulator = profiling.add(arrayList, "Stroka", dataNum[j]);
            mapAdd.put(dataNum[j], accumulator / dataNum[j]);
            accumulator = profiling.delete(arrayList, dataNum[j]);
            mapDelete.put(dataNum[j], accumulator / dataNum[j]);
            arrayList = new ArrayList<String>();
        }
        System.out.println("Время на добавление элемента в конец ArrayList");
        System.out.println(mapAdd.toString());
        System.out.println("Время на удаление первого элемента из ArrayList");
        System.out.println(mapDelete.toString());

        /*LinkedList*/
        for (int j = 0; j < dataNum.length; j++) {
            long accumulator = 0;
            accumulator = profiling.add(linkedList, "Stroka", dataNum[j]);
            mapAdd.put(dataNum[j], accumulator / dataNum[j]);
            accumulator = profiling.delete(linkedList, dataNum[j]);
            mapDelete.put(dataNum[j], accumulator / dataNum[j]);
            linkedList = new LinkedList<String>();
        }
        System.out.println("Время на добавление элемента в конец LinkedList");
        System.out.println(mapAdd.toString());
        System.out.println("Время на удаление первого элемента из LinkedList");
        System.out.println(mapDelete.toString());


        /*TreeSet*/
        for (int j = 0; j < dataNum.length; j++) {
            long accumulator = 0;
            accumulator = profiling.add(treeSet, "Stroka", dataNum[j]);
            mapAdd.put(dataNum[j], accumulator / dataNum[j]);
            accumulator = profiling.delete(treeSet, dataNum[j]);
            mapDelete.put(dataNum[j], accumulator / dataNum[j]);
            treeSet = new TreeSet<String>();
        }
        System.out.println("Время на добавление элемента в TreeSet");
        System.out.println(mapAdd.toString());
        System.out.println("Время на удаление первого элемента из TreeSet");
        System.out.println(mapDelete.toString());







    }
}

