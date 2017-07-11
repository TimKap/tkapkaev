package ru.job4j.map;
import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Calendar;
/**
 * Class NotOverrideHashAndEquals выводит отображение с пользователями (методы equals and hashCode не переопределены).
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 11.07.2017
 */
public class NotOverrideHashAndEquals {
    /**
     * Выводит отображение с пользователями.
     * */
    @Test
    public void printMap() {
        HashMap<User, Object> map = new HashMap<>();
        GregorianCalendar date = new GregorianCalendar(1975, Calendar.DECEMBER, 31);
        map.put(new User("Tom", date), "first");
        map.put(new User("Tom", date), "second");
        System.out.println(map);
    }

}
