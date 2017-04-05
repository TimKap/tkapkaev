package ru.job4j.start.actions;

import ru.job4j.start.Input;
import ru.job4j.start.Tracker;
import ru.job4j.models.Item;

/**
 * Class EditItem описывает пункт меню - редактирование заявки.
 */
public class EditItem implements Action {
    /** Трекер, который используется в меню.*/
    private final Tracker tracker;
    /** Система ввода, используемая в меню.*/
    private final Input input;

    /**
     * Конструктор для пункта меню редактирования заявки.
     * @param tracker - используемый трекер.
     * @param input - система ввода данных.
     *  */
    public EditItem(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.input = input;
    }

    /**
     * Редактирование заявки.
     */
    public void execute() {
        String id;
        Item item;
        Item updatedItem;
        id = input.ask("Print id of item which you wont to change");
        item = tracker.findById(id);
        if (item != null) {
            String name;
            name = input.ask("Print the name of new Item");
            String description;
            description = input.ask("Print description of new Item");
            long create;
            create = Long.parseLong(input.ask("Print time of creation new Item"));
            updatedItem = new Item(name, description, create);
            updatedItem.setId(item.getId());
            tracker.update(updatedItem);
        }
    }
}

