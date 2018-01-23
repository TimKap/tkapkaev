package ru.job4j.todo.storage;

import org.hibernate.Session;
import ru.job4j.todo.model.Item;

import java.util.List;

/**
 * Class ItemDAO описывает работу с хранилищем Item.
 * @author Timur Kpakaev(timur.kap@yandex.ru)
 * @version $ID$
 * @since 20.01.2018
 * */
public class ItemDAO {

    /** сессия. */
    private final Session session;

    /**
     * Конструктор ItemDAO.
     * @param session - сессия hibernate
     * */
    public ItemDAO(Session session) {
        this.session = session;
    }
    /**
     * Сохраняет item в хранилище.
     * @param item - item
     * @return сохраненный объект item
     * */
    public Item persist(Item item) {
        session.save(item);
        return item;
    }
    /**
     * Возвращает item по значению id.
     * @param id - item's id
     * @return item
     * */
    public Item get(int id) {
        List<Item> items = session.createQuery(String.format("FROM Item I WHERE I.id = %d", id)).list();
        return items.get(0);
    }
    /**
     * Обновляет item.
     * @param modifiedItem - обновленный item.
     * @return обновленный item
     * */
    public Item update(Item modifiedItem) {
        session.update(modifiedItem);
        return modifiedItem;
    }
    /**
     * Возвращает все item.
     * @return список всех item.
     * */
    public List<Item> getAll() {
        List<Item> items = session.createQuery("from Item").list();
        return items;
    }


}
