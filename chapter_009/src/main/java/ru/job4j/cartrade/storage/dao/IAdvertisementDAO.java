package ru.job4j.cartrade.storage.dao;

import ru.job4j.cartrade.model.advertisement.Advertisement;

import java.util.List;

/**
 * Interface IAdvertisementDAO описывает интерфейс DAO, взаимодействующего с сущностью Advertisement.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 23.01.20108
 * */
public interface IAdvertisementDAO extends IGenericDAO<Advertisement> {
    /**
     * Вовзращает объявление по имени продавца.
     * @param name - имя продавца.
     * @return список объявлений
     * */
    List<Advertisement> getBySellerName(String name);
    /**
     * Возвращает объявления марке автомобиля.
     * @param model - марка автомобиля
     * @return список объявлений
     * */
    List<Advertisement> getByModel(String model);
    /**
     * Возвращает объявления по статусу.
     * @param status - состояние объявления
     * @return список объявлений
     * */
    List<Advertisement> getByStatus(boolean status);
}
