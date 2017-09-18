package ru.job4j.menutracker;

import ru.job4j.models.Item;
import ru.job4j.models.ItemDatabase;

import java.sql.SQLException;
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
     * @throws SQLException - An exception that provides information on a database access error or other errors
     */
    public Item add(Item item) throws SQLException {
        item.setId(generateId());
        items.add(item);
        return item;
    }

    /**
     * Редактирование заявки.
     *
     * @param item - редактируемая заявка
     * @throws SQLException - An exception that provides information on a database access error or other errors
     */
    public void update(Item item) throws SQLException {
        items.update(item);
    }

    /**
     * Удаление заявки.
     *
     * @param item - удаляемая заявка
     * @throws SQLException - An exception that provides information on a database access error or other errors
     */
    public void delete(Item item) throws SQLException {
        items.delete(item);
    }

    /**
     * Получение списка всех заявок.
     * @return список всех заявок
     * @throws SQLException - An exception that provides information on a database access error or other errors
     */
    public List<Item> findAll() throws SQLException {
        return items.findAll();
    }

    /**
     * Получение списка заявок с одинаковым именем.
     *
     * @param key - имя заявки
     * @return список заявок с одинаковым именем.
     * @throws SQLException - An exception that provides information on a database access error or other errors
     */
    public List<Item> findByName(String key) throws SQLException {
        return items.findByName(key);
    }

    /**
     * Возвращение заявки по Id.
     *
     * @param id - идентификационный номер
     * @return - заявка
     * @throws SQLException - An exception that provides information on a database access error or other errors
     */
    public Item findById(String id) throws SQLException {
        return items.findByID(id);
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