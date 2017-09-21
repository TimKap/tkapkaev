package ru.job4j.menutracker;

import ru.job4j.models.Item;
import ru.job4j.models.ItemDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;


/**
 * Class Tracker обеспечивает работу с заявками.
 *
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 09.04.2017
 */
public class Tracker {
    /**
     * Заявки.
     */
    private ItemDatabase items;

    /**
     * Количество заявок.
     */
    private int position;

    /**
     * Генератор случайного числа.
     */
    private static final Random RN = new Random();

    /**
     * Конструктор класса Tracker.
     * @throws Exception при нарушении создания объекта Tracker
     */
    public Tracker() throws Exception {
        items = ItemDatabase.newInstance();
    }

    /**
     * Генератор идентификационного номера.
     *
     * @return id в формате строки
     */
    String generateId() {
        return String.valueOf(RN.nextInt());
    }

    /**
     * Добавление заявки.
     *
     * @param item - заявка
     * @return добавленная заявка
     */
    public Item add(Item item) {
        try {
            items.add(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Редактирование заявки.
     *
     * @param item - редактируемая заявка
     */
    public void update(Item item) {
        try {
            items.update(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Удаление заявки.
     *
     * @param item - удаляемая заявка
     */
    public void delete(Item item) {
        try {
            items.delete(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получение списка всех заявок.
     * @return список всех заявок
     */
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try {
            items.addAll(this.items.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Получение списка заявок с одинаковым именем.
     *
     * @param key - имя заявки
     * @return список заявок с одинаковым именем.
     */
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try {
            items.addAll(this.items.findByName(key));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Возвращение заявки по Id.
     *
     * @param id - идентификационный номер
     * @return - заявка
     */
    public Item findById(String id) {
        Item item = null;
        try {
            item = items.findByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Очищает трекер.
     * @throws SQLException - An exception that provides information on a database access error or other errors
     * */
    public void clean() throws SQLException {
        items.clean();
    }

    /**
     * Завершение работы с трекером.
     * @throws SQLException - An exception that provides information on a database access error or other errors
     * */
    public void close() throws SQLException {
        if (items != null) {
            items.close();
        }
    }
}