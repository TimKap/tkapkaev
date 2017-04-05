package ru.job4j.start.actions;

import ru.job4j.models.Item;
import ru.job4j.start.Input;
import ru.job4j.start.Tracker;

/**
 * Class FindItemById описывает пункт меню - поиск заявки по Id.
 */
public class FindItemById implements Action {
    /** Трекер, который используется в меню.*/
    private final Tracker tracker;
    /** Система ввода, используемая в меню.*/
    private final Input input;

    /**
     * Конструктор для пункта меню поиска заявки по  номеру Id.
     * @param tracker - используемый трекер.
     * @param input - система ввода данных.
     *  */
    public FindItemById(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.input = input;
    }

    /**
     * Вывод заявки на консоль.
     * @param item - заявка.
     * */
    private void showItem(Item item) {
        System.out.println("-----------------------------");
        System.out.println("Name: " + item.getName() + "     Data: " + item.getCreate());
        System.out.println(item.getDescription());
        System.out.println("ID: " + item.getId());
        System.out.println("-----------------------------");
    }

    /**
     * Поиск заявки по номеру Id.
     */
    public void execute() {
        String id;
        Item item;
        id = input.ask("Print id of item which you wont to find");
        item = tracker.findById(id);
        if (item != null) {
            showItem(item);
        }
    }
}
