package ru.job4j.start.actions;

import ru.job4j.models.Item;
import ru.job4j.start.Input;
import ru.job4j.start.Tracker;

/**
 * Class DeleteItem описывает пункт меню удаления заявки.
 */
public class DeleteItem implements Action {
    /** Трекер, который используется в меню.*/
    private final Tracker tracker;
    /** Система ввода, используемая в меню.*/
    private final Input input;

    /**
     * Конструктор для пункта меню удаления заявки.
     * @param tracker - используемый трекер.
     * @param input - система ввода данных.
     *  */
    public DeleteItem(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.input = input;
    }

    /**
     * Удаление заявки.
     */
    public void execute() {
        String id;
        Item item;
        id = input.ask("Print id of item which you wont to delete");
        item = tracker.findById(id);
        if (item != null) {
            tracker.delete(item);
        }
    }
}
