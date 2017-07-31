package ru.job4j.testtask;

/**
 * Class Price Описывает цену.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 20.07.2017
 */

public class Price implements Comparable<Price> {
    /** Стоимость заявки. */
    private String value;

    /**
     * Конуструктор класса Price.
     * @param value - стоимость
     * */
    public Price(String value) {
        this.value = value;
    }

    /**
     * Возвращает стоимость.
     * @return стоимость
     * */
    public String getValue() {
        return this.value;
    }

    /**
     * Устанавливает стоимость.
     * @param value - стоимость
     * */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Обределяет соотношение между объектами Price.
     * @return -1 если текущий объект предшествует заданному, 1 если текущий объект следует за заданным, 0 если объекты равны
     * */
    @Override
    public int compareTo(Price o) {
        return Double.compare(Double.valueOf(value), Double.valueOf(o.getValue()));
    }

    /**
     * Равенство объектов.
     * @param o - сравниваемый объект
     * @return true, если объекты равны
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        Price pr = (Price) o;

        return value != null ? value.equals(pr.getValue()) : value == pr.getValue();
    }

    /**
     * Возвращает хеш-код.
     * @return хеш-код
     * */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Преобразование в строку.
     * */
    @Override
    public String toString() {
        return value.toString();
    }
}