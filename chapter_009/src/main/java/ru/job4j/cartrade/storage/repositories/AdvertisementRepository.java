package ru.job4j.cartrade.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.cartrade.model.advertisement.Advertisement;

import java.util.List;

/**
 * Interface AdvertisementRepository описывает репозиторий объявлений.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 04.03.2018
 * */
@Transactional
public interface AdvertisementRepository extends CrudRepository<Advertisement, Long> {
    /**
     * Вовзращает список всех объявлений.
     * @return список объявлений.
     * */
    @Override
    List<Advertisement> findAll();
    /**
     * Вовзращает объявление по имени продавца.
     * @param name - имя продавца.
     * @return список объявлений
     * */
    List<Advertisement> findBySellerName(String name);
    /**
     * Возвращает объявления марке автомобиля.
     * @param model - марка автомобиля
     * @return список объявлений
     * */

    List<Advertisement> findByProductModel(String model);
    /**
     * Возвращает объявления по статусу.
     * @param sold - состояние объявления
     * @return список объявлений
     * */
    List<Advertisement> findBySold(boolean sold);
}
