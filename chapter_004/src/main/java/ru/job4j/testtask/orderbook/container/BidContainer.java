package ru.job4j.testtask.orderbook.container;

import ru.job4j.testtask.Price;
import ru.job4j.testtask.order.Bid;


import java.util.TreeMap;

/**
 * Class BidContainer описывает контейнер заявок на покупку.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 23.07.2017
 */
public class BidContainer extends OrderBookContainer<Bid> {
    /**
     * Контейнер класса BidContainer.
     * */
    public BidContainer() {
        super(new TreeMap<>((Price o1, Price o2) -> -o1.compareTo(o2)));
    }
}
