package ru.job4j.doptask;

/**
 * Class Bank описывает работу банка.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 12.05.217
 */
public class Bank {
    /** Посетители банка.*/
    private Visitor[] visitors;

    /**
     * Конструктор для класса Bank.
     * @param visitors - посетители банка
     * */
    public Bank(Visitor[] visitors) {
        this.visitors = visitors;
    }

    /**
     * Возвращает интервалы времени с пиковым числом посетителей.
     * @return  интервалы времени с пиковым числом посетителей
     * */
    public Interval[] peakIntervals() {
        Interval[] intervals = new Interval[visitors.length];
        int i = 0;
        for (Visitor visitor:visitors) {
            intervals[i++] = visitor.getVisitTime();
        }
        return Interval.maxInterferences(intervals);
    }

    /**
     * Выводит интервалы с пиковым числом посетителей.
     */
    public void printPeakInterval() {
        Interval[] intervals = peakIntervals();
        for (Interval interval : intervals) {
            interval.printInteval();
        }
    }


}
