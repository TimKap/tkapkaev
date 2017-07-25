package ru.job4j.testtask.orderbook;


import ru.job4j.testtask.order.Order;
import ru.job4j.testtask.orderbook.container.AskContainer;
import ru.job4j.testtask.orderbook.container.BidContainer;
import ru.job4j.testtask.orderbook.container.OrderBookContainer;


/**
 * Class OrderBook описывает биржевой стакан.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 21.07.2017
 */
public class OrderBook {

    /** Название биржевого стакана.*/
    private final String name;

    /** Заявки на покупку. */
    private final BidContainer bids = new BidContainer();

    /** Заявки на продажу. */
    private final AskContainer asks = new AskContainer();


    /**
     * Возращает контейнер к которому относится заявка.
     * @param order - заявка
     * @return контейнер с заявками.
     * */
    private OrderBookContainer selectContainer(Order order) {
        if (order.getType().equals("SELL")) {
            return asks;
        } else {
            return bids;
        }
    }

    /**
     * Конструктор OrderBook.
     * @param name - имя
     * */
    public OrderBook(String name) {
        this.name = name;
    }
    /**
     * Возвращает имя биржевого стакана.
     * @return имя стакана
     * */
    public String getName() {
        return name;
    }

    /**
     * Добавялет заявку в биржевой стакан.
     * @param order - заявка.
     * @return true, если завка добавлена
     * */
    public boolean add(Order order) {
        if (order != null) {
            OrderBookContainer container = selectContainer(order);
            container.put(order);
            return true;
        }
        return false;
    }
    /**
     * Удалить заявку и збиржевого стакана.
     * @param id - заявки
     * @return true, если заявка была удалена
     * */
    public boolean remove(String id) {
        return bids.remove(id) || asks.remove(id);
    }

    /**
     * Формирует отображение биржевого стакана.
     * @return отображение биржевого стакана
     * */
    public String[] representation() {
        String[] asksRecords = asks.getRecords();
        String[] bidsRecords = bids.getRecords();

        int size = Math.max(asksRecords.length, bidsRecords.length);
        String[] records = new String[size];

        for (int i = 0; i < size; i++) {
            String ask, bid;
            ask = i < asksRecords.length ? asksRecords[i] : "-----------";
            bid = i < bidsRecords.length ? bidsRecords[i] : "-----------";
            records[i] = String.format("%s - %s\n", bid, ask);
        }
        return records;
    }
}
