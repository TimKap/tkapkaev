package ru.job4j.cartrade.storage.dao.hibernatedao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.job4j.cartrade.model.advertisement.Advertisement;
import ru.job4j.cartrade.storage.dao.IAdvertisementDAO;

import java.util.List;

/**
 * Class AdvertisementDAO описывает hibernate реализацию IAdvertisementDAO.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 24.01.2018
 * */
public class AdvertisementDAO implements IAdvertisementDAO {
    /** hibernate сессия. */
    private final Session session;
    /**
     * Конструктор AdvertisementDAO.
     * @param session - hibernate сессия.
     * */
    public AdvertisementDAO(Session session) {
        this.session = session;
    }

    /**
     * Возвращает объявление из хранилища по id.
     * @param id - id объявления
     * @return объявление (null, если объявление не найдено)
     * */
    @Override
    public Advertisement get(long id) {
        List<Advertisement> advertisements = session.createQuery(String.format("FROM Advertisement a WHERE a.id = %d", id)).list();
        return advertisements.size() != 0 ? advertisements.get(0) : null;
    }

    /**
     * Возвращает все объявления из хранилища.
     * @return список всех объявлений, находящихся в хранилище
     * */
    @Override
    public List<Advertisement> getAll() {
        List<Advertisement> advertisements = session.createQuery("FROM Advertisement").list();
        return advertisements;
    }

    /**
     * Добавляет объявление в хранилище.
     * @param advertisement - добаляемое обяъвление.
     * @return добавленное объявление
     * */
    @Override
    public Advertisement persist(Advertisement advertisement) {
        session.save(advertisement);
        return advertisement;
    }


    /**
     * Обновляет объявление, находящуюся в хранилище.
     * @param modifiedAdvertisement - обновленное объявление
     * @return обновленное объявление
     * */
    @Override
    public Advertisement update(Advertisement modifiedAdvertisement) {
        session.update(modifiedAdvertisement);
        return modifiedAdvertisement;
    }

    /**
     * Удаляет из хранилища заданное объявленеи.
     * @param advertisement - удаляемое объявление.
     * @return удаленный автомобиль
     * */
    @Override
    public Advertisement remove(Advertisement advertisement) {
        session.remove(advertisement);
        return advertisement;
    }

    /**
     * Вовзращает объявление по имени продавца.
     * @param name - имя продавца.
     * @return список объявлений
     * */
    @Override
    public List<Advertisement> getBySellerName(String name) {
        Query query = session.createQuery("FROM Advertisement a WHERE a.seller.name = :name");
        query.setParameter("name", name);
        return query.list();
    }

    /**
     * Возвращает объявления марке автомобиля.
     * @param model - марка автомобиля
     * @return список объявлений
     * */
    @Override
    public List<Advertisement> getByModel(String model) {
        Query query = session.createQuery("FROM Advertisement a WHERE a.product.model = :model");
        query.setParameter("model", model);
        return query.list();
    }

    /**
     * Возвращает объявления по статусу.
     * @param status - состояние объявления
     * @return список объявлений
     * */
    @Override
    public List<Advertisement> getByStatus(boolean status) {
        Query query = session.createQuery("FROM Advertisement a WHERE a.sold = :status");
        query.setParameter("status", status);
        return query.list();
    }
}
