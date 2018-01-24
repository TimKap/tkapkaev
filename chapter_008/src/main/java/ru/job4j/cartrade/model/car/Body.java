package ru.job4j.cartrade.model.car;

/**
 * Class Body описывает кузов автомобиля.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 16/01/2018
 * */
public class Body {
    /** type. */
    private String type;
    /** color. */
    private String color;

    /**
     * Конструктор класса Body.
     * */
    public Body() {

    }

    /**
     * Конструктор класса Body.
     * @param type - тип кузова
     * @param color - wdtn repjdf/
     * */
    public Body(String type, String color) {
        this.type = type;
        this.color = color;
    }

    /**
     * Возвращает тип кузова.
     * @return тип кузова
     * */
    public String getType() {
        return type;
    }
    /**
     * Задает тип кузова.
     * @param type - тип кузова
     * */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * Возвращает цвет кузова.
     * @return цвет кузова.
     * */
    public String getColor() {
        return color;
    }
    /**
     * Задает цвет кузова.
     * @param color - цвет кузова
     * */
    public void setColor(String color) {
        this.color = color;
    }
}
