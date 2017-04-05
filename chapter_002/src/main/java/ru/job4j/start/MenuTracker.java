package ru.job4j.start;
import ru.job4j.start.actions.Action;
import ru.job4j.start.actions.AddItem;
import ru.job4j.start.actions.DeleteItem;
import ru.job4j.start.actions.EditItem;
import ru.job4j.start.actions.FindItemById;
import ru.job4j.start.actions.FindItemsByName;
import ru.job4j.start.actions.ShowAllItems;

/**
 * Class MenuTracker описывает меню для Трекера.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 04.04.2017
 */
public class MenuTracker {
    /** Трекер, который используется в меню.*/
    private final Tracker tracker;
    /** Система ввода, используемая в меню.*/
    private final Input input;
    /** Пункты меню.*/
    private final Action[] actions;
    /** Выход из меню.*/
    public static final int EXIT = 6;

    /**
     * Конструктор класса MenuTracker.
     * @param tracker - трекер, который используется в меню
     * @param input - система ввода, используемая в меню
     * */
    public MenuTracker(Tracker tracker, Input input) {

        this.tracker = tracker;
        this.input = input;
        /* Инициализация пунктов меню*/
        actions = new Action[7];
        actions[0] = new AddItem(tracker, input);
        actions[1] = new ShowAllItems(tracker, input);
        actions[2] = new EditItem(tracker, input);
        actions[3] = new DeleteItem(tracker, input);
        actions[4] = new FindItemById(tracker, input);
        actions[5] = new FindItemsByName(tracker, input);
    }

    /**
     * Вывод на консоль меню.
     * */
    public void showMenu() {
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    /**
     * Выбор пункта меню.
     * @return пункт меню
     */
    public int select() {
        int i;

        i = Integer.valueOf(input.ask("Select"));
        if (i < EXIT) {
            actions[i].execute();
        } else {
            i = EXIT;
        }
        return i;
    }
}
