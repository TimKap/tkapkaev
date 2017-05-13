package ru.job4j.doptask;
import java.time.LocalTime;
/**
 * Class Visitor описывает посетителя.
 * @author Tomur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 12.05.2017
 */
public class Visitor {
    /** Время посещения клиентом банка.*/
    private Interval visitTime;
    /** Конструктор класса Visitor.
     * @param entryTime - время захода клиента
     * @param exitTime - время ухода клиента
     * */
    public Visitor(LocalTime entryTime, LocalTime exitTime) {
        visitTime = new Interval(entryTime, exitTime);
    }

    /**
     * Возвращает время нахождения клиента в банке.
     * @return время нахождения клиента в банке
     * */
    public Interval getVisitTime() {
        return visitTime;
    }
}
