package ru.job4j.start.actions;

import ru.job4j.start.Input;
import ru.job4j.start.Tracker;
import ru.job4j.models.Item;

/**
 * Class ShowAllItem описывает пункт меню вывода всех заявок трекера.
 */
public class ShowAllItems implements Action  {
    /** Трекер, который используется в меню.*/
    private final Tracker tracker;
    /** Система ввода, используемая в меню.*/
    private final Input input;

    /**
     * Конструктор для пункта меню вывода всех заявок.
     * @param tracker - используемый трекер.
     * @param input - система ввода данных.
     *  */
    public ShowAllItems(Tracker tracker, Input input) {
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
     * Вывод всех заявок на экран.
     */
    public void execute() {
        for (Item item : tracker.findAll()) {
            if (item != null) {
                showItem(item);
            }
        }
    }


}
