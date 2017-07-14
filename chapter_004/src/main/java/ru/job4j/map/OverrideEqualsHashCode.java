package ru.job4j.map;

/**
 * Class OverrideEquals описывает класс с переопределенным методом equals.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 14.07.2017
 */
public class OverrideEqualsHashCode {

    /** Поле с числом. */
    private int number;
    /** Поле с вещественным числом. */
    private float floatNumber;
    /** Поле с переменной ссылочного типа. */
    private String string;
    /**
     * Конструктор класса OverrideEqulas.
     * @param number - целое число
     * @param floatNumber - вещественное число
     * @param string - строка
     * */
    public OverrideEqualsHashCode(int number, float floatNumber, String string) {
        this.number = number;
        this.floatNumber = floatNumber;
        this.string = string;
    }

    /**
     * Проверка равенства объектов класса OverrideEquals.
     * @param o - объект, с которым выполняется сравнение
     * */
    @Override

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (o.getClass() != getClass())) {
            return false;
        }

        OverrideEqualsHashCode object = (OverrideEqualsHashCode) o;

        if (number != object.number) {
            return false;
        }

        if (Float.compare(object.floatNumber, floatNumber) != 0) {
            return false;
        }

        return string != null ? string.equals(object.string) : object.string == null;
    }

    /**
     *Вычисляет значение хеш-функции.
     * @return хеш-код
     * */
    @Override
    public int hashCode() {
        return 0;
    }
}
