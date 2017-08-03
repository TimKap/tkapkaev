package ru.job4j.testtask;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import ru.job4j.testtask.order.Ask;
import ru.job4j.testtask.orderbook.container.AskContainer;

/**
 * Class OrderBookContainerTest содержит тесты для контейнера биржевого стакана.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 25.07.2017
 */
public class OrderBookContainerTest {
    /**
     * Тест для метода Put.
     * */
    @Test
    public void whenPutOrderThenGetContainerWithNewOrder() {
        String[] expected = new String[1];
        expected[0] = String.format("%s@%s", "100", "10.1");

        AskContainer askContainer = new AskContainer();
        askContainer.put(new Ask("1", new Price("10.1"), 20));
        askContainer.put(new Ask("2", new Price("10.1"), 80));

        String[] result = askContainer.getRecords();

        assertThat(result, is(expected));
    }

    /**
     * Тест для метода remove.
     * */
    @Test
    public void whenRemoveOrderThenGetContainerWithoutOrder() {
        String[] expected = new String[1];
        expected[0] = String.format("%s@%s", "20", "10.1");

        AskContainer askContainer = new AskContainer();
        askContainer.put(new Ask("1", new Price("10.1"), 20));
        askContainer.put(new Ask("2", new Price("10.1"), 80));

        askContainer.remove("2");
        String[] result = askContainer.getRecords();
        assertThat(result, is(expected));
    }

//    /**
//     * Тест время вставки.
//     * */
//    Map<Price, OrdersBucket<Ask>> container = new TreeMap<>();
//    @Test
//    public void TimeAddTest() {
//        long time2 = 0;
//        long time3 = 0;
//        long time4 = 0;
//
//        long start = System.currentTimeMillis();
//
//        for (int j = 0; j < 10; j++) {
//            for (int i = 0; i < 50000; i++ ) {
//                Ask order = new Ask(((Integer)(i * j)).toString(), new Price(((Integer)j).toString()), 20);
//
//                long start2 = System.currentTimeMillis();
//
//                OrdersBucket<Ask> bucket = container.get(order.getPrice());
//
//                long end2 = System.currentTimeMillis();
//                time2 += (end2 - start2);
//                if (bucket == null) {
//                    long start4 = System.currentTimeMillis();
//
//                    bucket = new OrdersBucket<>(order.getPrice());
//                    bucket.addOrder(order);
//                    container.put(bucket.getPrice(), bucket);
//
//                    long end4 = System.currentTimeMillis();
//                    time4 += (end4 - start4);
//                } else {
//                    long start3 = System.currentTimeMillis();
//
//                    bucket.addOrder(order);
//
//                    long end3 = System.currentTimeMillis();
//                    time3 += (end3 - start3);
//                }
//            }
//        }
//        long end = System.currentTimeMillis();
//        long time = end - start;
//        System.out.println(time);
//        System.out.println(time2);
//        System.out.println(time3);
//        System.out.println(time4);
//    }



}
