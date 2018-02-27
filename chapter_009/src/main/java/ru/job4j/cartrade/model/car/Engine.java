package ru.job4j.cartrade.model.car;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * Class Engine описывает двигатель автомобиля.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 16.01/2018
 * */
@Embeddable
public class Engine {

    /** model.*/
    @Column(name = "engine")
    private String model;

    /**
     * Конструктор класса Engine.
     * @param model - модель двигателя.
     * */
    public Engine(String model) {
        this.model = model;
    }

    /**
     * Возвращает модель двигателя.
     * @return модель двигателя
     * */
    public String getModel() {
        return model;
    }
    /**
     * Задает модель двигателя.
     * @param model - модель двигателя.
     * */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Конструктор класса Engine.
     * */
    public Engine() {

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
        Engine engine = (Engine) o;
        return model != null ? model.equals(engine.model) : engine.model == null;
    }

    /**
     * Хэш-функция.
     * @return хэш-код
     * */
    @Override
    public int hashCode() {
        return model != null ? model.hashCode() : 0;
    }
}
