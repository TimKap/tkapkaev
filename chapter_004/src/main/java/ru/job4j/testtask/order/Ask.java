package ru.job4j.testtask.order;
import ru.job4j.testtask.Price;

/**
 * Class Ask описывает заявку на продажу.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 21.07.2017
 */
public class Ask extends Order {

    /** Конструктор заявки на продажу.
     * @param id - id завяки
     * @param coast - стоимость заявки.
     * @param volume - объем заявки.
     * */
    public Ask(String id, Price coast, int volume) {
        super(id, "SELL", coast, volume);
    }
}
