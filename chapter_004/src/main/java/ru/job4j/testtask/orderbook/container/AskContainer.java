package ru.job4j.testtask.orderbook.container;

import ru.job4j.testtask.order.Ask;

import java.util.TreeMap;

/**
 * Class AskContainer описывает контейнер заявок на продажу.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 21.07.2017
 */
public class AskContainer extends OrderBookContainer<Ask> {
    /**
     * Конструктор класса AskContainer.
     * */
    public AskContainer() {
        super(new TreeMap<>());
    }

}
