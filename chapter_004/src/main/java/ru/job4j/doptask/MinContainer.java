package ru.job4j.doptask;

import java.util.ArrayList;

/**
 * Class MinContainer описывает контейнер с индексами минимальных элементов.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 25.07.2017
 */
public class MinContainer {
    /** Значение минимального элемента. */
    private int min;

    /** Контейнер индексов минимальных элементов. */
    private ArrayList<Integer> minIndexes = new ArrayList<>();

    /** Конструктор класса MinContainer.
     * @param min - начальное значение минимального элемента
     * @param index - индекс минимального элемента
     * */
    public MinContainer(int min, int index) {
        this.min = min;
        this.minIndexes.add(index);
    }
    /**
     * Возвращает минимальное значение контейнера.
     * @return минимальное значение контейнера
     * */
    public int getMin() {
        return min;
    }

    /**
     * Устанавливает минимальное значение контейнера.
     * @param min - минимальное значение контейнера
     * */
    void setMin(int min) {
        this.min = min;
    }

    /**
     * Возвращает контенер с индексами минимальных элементов.
     * @return контейнер с индексавми минимальных элементов.
     * */
    public ArrayList<Integer> getMinIndexes() {
        return minIndexes;
    }

    /**
     * Добавляет индекс минимального элемента.
     * @param  index - индекс минимального элемента
     * */
     void addIndex(int index) {
        minIndexes.add(index);
    }
    /**
     * Освобождает контейнер с индексами минимальных элементов.
     * */
    void clearContainer() {
        minIndexes.clear();
    }
}
