package ru.job4j.jmm;

/**
 * Class SharedVariable описывает разделяемую переменную.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 08.08.2017
 */
public class SharedVariable {
    /** счетчик. */
    private int counter;

    /**
     * Возвращает значение счетчика.
     * @return значение счетчика
     * */
    public int getCounter() {
        return counter;
    }

    /**
     * Задает значение счетчика.
     * @param counter - новое значение счетчика.
     * */
    public void setCounter(int counter) {
        this.counter = counter;
    }
    /**
     * Добавляет знаяение.
     * @param value - значение
     * */
    public void add(int value) {
        this.counter += value;
    }
}
