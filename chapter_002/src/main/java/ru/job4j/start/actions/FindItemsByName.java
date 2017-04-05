package ru.job4j.start.actions;
import ru.job4j.start.Tracker;
import ru.job4j.start.Input;
import ru.job4j.models.Item;

/**
 * Class FindItemsByName - описывает пункт меню поиска всех заявок с одинаковым именем.
 */
public class FindItemsByName implements Action {
    /** Трекер, который используется в меню.*/
    private final Tracker tracker;
    /** Система ввода, используемая в меню.*/
    private final Input input;

    /**
     * Конструктор для пункта меню поиска заявок по тмени.
     * @param tracker - используемый трекер.
     * @param input - система ввода данных.
     *  */
    public FindItemsByName(Tracker tracker, Input input) {
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
     * Поиск заявок по имени.
     */
    public void execute() {
        String name;
        Item[] items;
        name = input.ask("Print name of item which you wont to find");
        items = tracker.findByName(name);

        for (Item item : items) {
            showItem(item);
        }
    }
}
