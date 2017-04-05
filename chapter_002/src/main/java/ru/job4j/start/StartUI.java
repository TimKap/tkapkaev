package ru.job4j.start;

/**
 * Class StartUI обеспечивает точку входа в программу.
 * @author Timur Kapkaev (timur,kap@yandex.ru)
 * @since 02.04.2017
 */
public class StartUI {
    /** Определяет способ ввода данных в приложение.*/
    private final Input input;
    /** ИТрекер.*/
    private final Tracker tracker;
    /** Меню трекера.*/
    private final  MenuTracker menu;

    /**
     * Конструктор класса StartUI.
     * @param input - способ ввода данных в приложение
     * */
    public StartUI(Input input) {

        this.input = input;
        tracker = new Tracker();
        menu = new MenuTracker(tracker, input);
    }


    /**
     * Точка входа в приложение.
     * @param args - аргументы переданные в приложение из консоли.
     * */
    public static void main(String[] args) {
       StartUI ui = new StartUI(new ConsoleInput());
       do {
           ui.menu.showMenu();
       } while (ui.menu.select() != MenuTracker.EXIT);
    }
}
