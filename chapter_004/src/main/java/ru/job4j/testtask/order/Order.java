package ru.job4j.testtask.order;

import ru.job4j.testtask.Price;

/**
 * Class Order описывает заявку.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 20.07.2017
 */

public abstract class Order {

    /** ID заявки. */
    private final String id;

    /** Тип заявки. */
    private final String type;

    /** Стоимость заявки. */
    private Price price;

    /** Объем заявки.*/
    private int volume;


    /** Конструктор заявки.
     * @param id - id завяки
     * @param type - тип заявки
     * @param coast - стоимость заявки.
     * @param volume - объем заявки
     * */
    public Order(String id, String type, Price coast, int volume) {
        this.id = id;
        this.price = coast;
        this.volume = volume;
        this.type = type;
    }

    /**
     * Возвращает стоимост ьзаявки.
     * @return стоимость
     * */
    public Price getPrice() {
        return price;
    }
    /**
     * Возвращает ID заявки.
     * @return ID заявки
     * */
    public String getId() {
        return id;
    }

    /**
     * Возвращает объем заявки.
     * @return объем заявки
     * */
    public int getVolume() {
        return volume;
    }

    /**
     * Возвращает тип заявки.
     * @return тип заявки
     * */
    public String getType() {
        return type;
    }


}


