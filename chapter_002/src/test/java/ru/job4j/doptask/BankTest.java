package ru.job4j.doptask;

import org.junit.Test;

import java.time.LocalTime;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Class BankTest содержит тесты для дополнительной задачи.
 */
public class BankTest {
    /**
     * Тест для нахождения отрезков времени, на которых было зафиксировано максимльное число посетителей.
     * */
    @Test
    public void whenBankVisitSOmePeopleThenGetIntervalTimeWhenPeoiplesPeak() {
        Visitor[] visitors = new Visitor[] {new Visitor(LocalTime.of(10, 12), LocalTime.of(10, 30)), new Visitor(LocalTime.of(10, 0), LocalTime.of(10, 30)),
                new Visitor(LocalTime.of(11, 0), LocalTime.of(12, 0)), new Visitor(LocalTime.of(11, 10), LocalTime.of(11, 40)), new Visitor(LocalTime.of(11, 30), LocalTime.of(11, 50)),
                new Visitor(LocalTime.of(12, 10), LocalTime.of(12, 40)), new Visitor(LocalTime.of(12, 30), LocalTime.of(12, 50))};

        /*
        *    A1-----------B1
        * A2--------------B2
        *                              A3-------------------------------B3
        *                                                |    |
        *                                        A4-----------B4
        *                                                |    |
        *                                                A5--------B5
        *                                                |    |
        *                                                |    |                   A6---------B6
        *                                                |    |
        *                                                |    |                           A7--------B7
        *                                                |    |
        *                                               A5 max B4
        *---------------------------------------------------------------------------------------------------------------------->t,время
        * */
        Bank bank = new Bank(visitors);
        Interval[] intervals = bank.peakIntervals();
        LocalTime expectedLeftBoundary = visitors[4].getVisitTime().getLefBoundary();
        LocalTime expectedRightBoundary = visitors[3].getVisitTime().getRightBoundary();
        LocalTime resultLeftBoundary = intervals[0].getLefBoundary();
        LocalTime resultRightBoundary = intervals[0].getRightBoundary();
        assertThat(resultLeftBoundary, is(expectedLeftBoundary));
        assertThat(resultRightBoundary, is(expectedRightBoundary));
    }
}
