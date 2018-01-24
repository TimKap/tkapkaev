package ru.job4j.cartrade.model.advertisement;

import ru.job4j.cartrade.model.car.Car;
import ru.job4j.cartrade.model.user.User;

import java.util.ArrayList;
import java.util.List;


/**
 * Class advertisement описывает объявление о продаже.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 16.01.2018
 * */
public class Advertisement {
    /** id. */
    private long id;
    /** seller. */
    private User seller;
    /** product.*/
    private Car product;
    /** sold status. */
    private boolean sold;
    /** comments. */
    private List<String> comments = new ArrayList<>();

    /**
     * Конструктор класса advertisement.
     * */
    public Advertisement() {
    }

    /**
     * Возвращает id объявления.
     * @return id объявления
     * */
    public long getId() {
        return id;
    }

    /**
     * Завдает id объявления.
     * @param id - id объявления.
     * */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Возвращает продавца.
     * @return продавца
     * */
    public User getSeller() {
        return seller;
    }

    /**
     * Задает продавца.
     * @param seller - продавец
     * */
    public void setSeller(User seller) {
        this.seller = seller;
    }

    /**
     * Возвращает продаваемый товар.
     * @return продаваемый товар
     * */
    public Car getProduct() {
        return product;
    }

    /**
     * Задает продаваемый товар.
     * @param product - продааваемый товар
     * */
    public void setProduct(Car product) {
        this.product = product;
    }

    /**
     * Возвращает статус продажи.
     * @return статус продажи
     * */
    public boolean getSold() {
        return sold;
    }

    /**
     * Задает статус продажи.
     * @param sold - статус продажи
     * */
    public void setSold(boolean sold) {
        this.sold = sold;
    }

    /**
     * Возвращает комментарии к  объявлению.
     * @return id объявления
     * */
    public List<String> getComments() {
        return comments;
    }

    /**
     * Задает комментарии к объявлению.
     * @param comments - комментарии к объявлению
     * */
    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
