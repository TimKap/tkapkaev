package ru.job4j.testtask.order;
import ru.job4j.testtask.Price;

/**
 * Class Bid описывает заявку на покупку.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 21.07.2017
 */
public class Bid extends Order {

    /** Конструктор заявки на покупку.
     * @param id - id завяки
     * @param coast - стоимость заявки.
     * @param volume - объем заявки.
     * */
    public Bid(String id, Price coast, int volume) {
        super(id, "BUY", coast, volume);
    }
}