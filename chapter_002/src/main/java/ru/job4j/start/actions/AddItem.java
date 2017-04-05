package ru.job4j.start.actions;

import ru.job4j.start.Input;
import ru.job4j.start.Tracker;
import ru.job4j.models.Item;

/**
 * Class AddItem описывает пункт меню добавления заявки.
 */
public class AddItem implements Action {

    /** Трекер, который используется в меню.*/
    private final Tracker tracker;
    /** Система ввода, используемая в меню.*/
    private final Input input;
    /**
     * Конструктор для пункта меню добавление заявки.
     * @param tracker - используемый трекер.
     * @param input - система ввода данных.
     *  */
    public AddItem(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.input = input;
    }

    /**
     * Реализаци пункта меню.
     * */
    public void execute() {
        String name;
        name = input.ask("Print the name of new Item");
        String description;
        description = input.ask("Print description of new Item");
        long create;
        create = Long.parseLong(input.ask("Print time of creation new Item"));
        tracker.add(new Item(name, description, create));
    }
}
