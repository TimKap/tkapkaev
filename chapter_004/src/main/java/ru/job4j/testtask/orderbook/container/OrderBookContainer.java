package ru.job4j.testtask.orderbook.container;

import ru.job4j.testtask.Price;
import ru.job4j.testtask.order.Order;
import ru.job4j.testtask.order.OrdersBucket;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Class OrderBookContainer описывает контейнер заявок биржевого стакана.
 * @author Timur Kapkaev (timur.kjap@yandex.ru)
 * @version $Id$
 * @since 23.07.2017
 * @param <E> - тип заявки в контейнере
 */
public abstract class OrderBookContainer<E extends Order> {

    /** Контейнер заявок. */
    private Map<Price, OrdersBucket<E>> container;

    /**
     * Конструктор для OrderBookContainer.
     * @param container - реализация контейнера
     * */
    public OrderBookContainer(TreeMap<Price, OrdersBucket<E>> container) {
        this.container = container;
    }

    /**
     * Добавляет заявку в контейнер.
     * @param order - заявка
     * */
    public void put(E order) {
        OrdersBucket<E> bucket = container.get(order.getPrice());
        if (bucket == null) {
            bucket = new OrdersBucket<>(order.getPrice());
            bucket.addOrder(order);
            container.put(bucket.getPrice(), bucket);
        } else {
            bucket.addOrder(order);
        }
    }
    /**
     * Удаляет заявку из контейнера.
     * @param id - ID заявки
     * @return true, если заявка была удалена
     * */
    public boolean remove(String id) {
        OrdersBucket<E> searchingBucket = null;
        /* Поиск корзины с нужной заявкой*/
        for (OrdersBucket<E> bucket : container.values()) {
            /*Извлечение заявки из корзины*/
            if (bucket.extractOrder(id) != null) {
                searchingBucket = bucket;
                break;
            }
        }

        if ((searchingBucket != null) && (searchingBucket.getSize() == 0)) {
            container.remove(searchingBucket.getPrice());
        }
        return searchingBucket != null;
    }

    /**
     * Возвращает массив записей вида объем@цена.
     * @return массив строк
     * */
    public String[] getRecords() {
        String[]  records = new String[container.size()];

        Set<Map.Entry<Price, OrdersBucket<E>>> set = container.entrySet();
        int i = 0;
        for (Map.Entry<Price, OrdersBucket<E>> element : set) {
            records[i++] = String.format("%s@%s", element.getValue().getVolumeBucket(), element.getKey().toString());
        }
        return records;
    }
}
