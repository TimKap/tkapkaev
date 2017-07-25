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
}
