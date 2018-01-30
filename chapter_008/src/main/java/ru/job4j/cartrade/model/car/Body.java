package ru.job4j.cartrade.model.car;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * Class Body описывает кузов автомобиля.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 16/01/2018
 * */
@Embeddable
public class Body {

    /** type. */
    @Expose
    @Column(name = "type")
    private String type;

    /** color. */
    @Expose
    @Column(name = "color")
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

    /**
     * Равенство объектов.
     * @param o - сравниваемый объект
     * @return true, объекты равны
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Body body = (Body) o;
        if (color != null ? !color.equals(body.color) : body.color != null) {
            return false;
        }
        return type != null ? type.equals(body.type) : body.type == null;
    }

    /**
     * Хэш-функция.
     * @return хэш-код
     * */
    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

}
