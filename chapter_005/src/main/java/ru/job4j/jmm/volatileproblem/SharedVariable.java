package ru.job4j.jmm.volatileproblem;

/**
 * Class SharedVariable.
 */
public class SharedVariable {
    /** Счетчик. */
    private int counter = 0;

    /**
     * Увеличение счетчика на заданное значение.
     * @param value - значение на которое увеличивается счетчик
     * */
    public void add(int value) {
        counter += value;
    }

    /**
     * Возвращает значение счетчика.
     * @return значение счетчика
     * */
    public int getCounter() {
        return counter;
    }
}
