package ru.job4j.testtask;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class OrderBookManagerTest содержит тесты OrderBookManager.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 24.07
 */
public class OrderBookManagerTest {
    /**
     * Тест для processingFile.
     * */
    @Test
    public void whenProcessingFileThenGetFilledOrderBooks() {
        String expected = String.format("%s - %s\n", "64@99.80", "39@100.50");

        OrderBookManager bookManager = new OrderBookManager();
        bookManager.processingFile("C:/projects/tkapkaev/chapter_004/src/test/java/ru/job4j/testtask/ordersTest.xml");

        String[] result = bookManager.getOrderBooks().get(0).representation();

        assertThat(result[0], is(expected));

    }
}
