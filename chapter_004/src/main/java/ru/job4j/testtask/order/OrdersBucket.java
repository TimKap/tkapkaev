package ru.job4j.testtask.order;

import ru.job4j.testtask.Price;

import java.util.HashMap;
import java.util.Map;

/**
 * Class OrdersBucket содержит набор заявок.
 * Корзина позволяет группировать заявки по определенному признаку.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 20.07.2017
 * @param <E> - тип завяки
 */
public class OrdersBucket<E extends Order> {

    /** Корзина заказов. */
    private Map<String, E> bucket = new HashMap<>();

    /** Стоимость корнзины. */
    private final Price bucketPrice;

    /** Объем заявок в корзине. */
    private int volumeBucket = 0;

    /**
     * Конструктор корзины заявок.
     * @param bucketPrice - стоимость корзины
     * */
    public OrdersBucket(Price bucketPrice) {
        this.bucketPrice = bucketPrice;

    }
    /** Положить в корзину заявку.
     * @param order - Заявка
     * */
    public void addOrder(E order) {
        if (order != null) {
            bucket.put(order.getId(), order);
            volumeBucket += order.getVolume();
        }
    }

    /** Извлечь из корзины заявку.
     * @param id заявки
     * @return заявка
     * */
    public E extractOrder(String id) {
        E order = bucket.remove(id);
        if (order != null) {
            volumeBucket -= order.getVolume();
        }
        return order;
    }
    /**
     * Вовзращает размер корзины.
     * @return объем корзины
     * */
    public int getSize() {
        return bucket.size();
    }

    /**
     * Возвращает объем заявок.
     * @return объем заявок в корзине
     * */
    public int getVolumeBucket() {
        return volumeBucket;
    }


    /**
     * Возвращает стоимость корзины.
     * @return стоимость корзины
     * */
    public Price getPrice() {
        return bucketPrice;
    }
}
