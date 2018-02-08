package ru.job4j.cartrade.controller.main;

import ru.job4j.cartrade.model.advertisement.Advertisement;
import ru.job4j.cartrade.storage.dao.IAdvertisementDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
/**
 * Class DispatchPattern описывает паттерн Dispatch.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 08.02.2018
 * */
public class DispatchPattern {
    /** advertisement DAO. */
    private final IAdvertisementDAO advertisementDAO;
    /** набор выполняемых задач. */
    private final Map<String, Function<String, List<Advertisement>>> tasks = new HashMap();
    /**
     * Конструктор DispatchPattern.
     * @param advertisementDAO - advertisement DAO.
     * */
    public DispatchPattern(IAdvertisementDAO advertisementDAO) {
        this.advertisementDAO = advertisementDAO;
    }
    /**
     * Возвращает задачу, которая извлекает все объявления.
     * @return задача, извлекающая все объявления
     * */
    private Function<String, List<Advertisement>> getAll() {
        return (value) -> advertisementDAO.getAll();
    }

    /**
     * Возвращает задачу, которая извлекает все объявления с заданной моделью автомобиля.
     * @return задача, извлекающая все объявления с заданной моделью автомобиля
     * */
    private Function<String, List<Advertisement>> getByModel() {
        return (value) -> advertisementDAO.getByModel(value);
    }

    /**
     * Возвращает задачу, которая извлекает все объявления с заданным продавцом.
     * @return задача, извлекающая все объявления с заданным продавцом
     * */
    private Function<String, List<Advertisement>> getBySellerName() {
        return (value) -> advertisementDAO.getBySellerName(value);
    }

    /**
     * Возвращает задачу, которая извлекает все объявления с непроданным автомобилем.
     * @return задача, извлекающая все объявления с непроданным автомобилем
     * */
    private Function<String, List<Advertisement>> getByStatus() {
        return (value) -> advertisementDAO.getByStatus(false);
    }

    /**
     * Инициализация Dispatch.
     * */
    public void init() {
        tasks.put("all", getAll());
        tasks.put("model", getByModel());
        tasks.put("seller", getBySellerName());
        tasks.put("unsold", getByStatus());
    }
    /**
     * Извлекает объявления.
     * @param type - тип параметра
     * @param value - значение параметра.
     * @return список объявлений.
     * */
    public List<Advertisement> extract(String type, String value) {
        return tasks.get(type).apply(value);
    }
}

