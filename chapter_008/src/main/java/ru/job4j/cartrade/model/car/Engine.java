package ru.job4j.cartrade.model.car;

/**
 * Class Engine описывает двигатель автомобиля.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 16.01/2018
 * */
public class Engine {

    /** model.*/
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
}
