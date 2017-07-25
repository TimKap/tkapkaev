package ru.job4j.testtask;

import org.junit.Test;
import ru.job4j.testtask.order.OrdersBucket;
import ru.job4j.testtask.order.Ask;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;



/**
 * Class OrdersBucketTest содержит тесты для корзины заявок.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 25.07.2017
 */
public class OrdersBucketTest {
    /**
     * Тест для метода addOrder.
     * */
    @Test
    public void whenAddOrderThenGetBucketWithNewOrder() {
        int expectedSize = 2;
        int expectedVolume = 30;

        OrdersBucket<Ask> ordersBucket = new OrdersBucket<>(new Price("100.1"));

        ordersBucket.addOrder(new Ask("1", new Price("100.1"), 10));
        ordersBucket.addOrder(new Ask("2", new Price("100.1"), 20));

        int resultSize = ordersBucket.getSize();
        int resultVolume = ordersBucket.getVolumeBucket();

        assertThat(resultSize, is(expectedSize));
        assertThat(resultVolume, is(expectedVolume));
    }

    /**
     * Тест для метода extractOrder.
     * */
    @Test
    public void whenExtractOrderThenGetOrdersBucketWithoutOrder() {

        int expectedSize = 1;
        int expectedVolume = 20;

        OrdersBucket<Ask> ordersBucket = new OrdersBucket<Ask>(new Price("100.1"));

        ordersBucket.addOrder(new Ask("1", new Price("100.1"), 10));
        ordersBucket.addOrder(new Ask("2", new Price("100.1"), 20));

        ordersBucket.extractOrder("1");
        int resultSize = ordersBucket.getSize();
        int resultVolume = ordersBucket.getVolumeBucket();

        assertThat(resultSize, is(expectedSize));
        assertThat(resultVolume, is(expectedVolume));
    }
}
