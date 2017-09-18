package ru.job4j.menutracker;

/**
 * Class StartUI обеспечивает точку входа в программу.
 * @author Timur Kapkaev (timur,kap@yandex.ru)
 * @since 09.04.2017
 */
public class StartUI {
    /** Определяет способ ввода данных в приложение.*/
    private final Input input;
    /** ИТрекер.*/
    private  final Tracker tracker;
    /** Меню трекера.*/
    private final  MenuTracker menu;

    /**
     * Конструктор класса StartUI.
     * @param input - способ ввода данных в приложение
     * @throws Exception при нарушении создания объекта Tracker
     * */
    public StartUI(Input input) throws Exception {

        this.input = input;
        tracker = new Tracker();
        menu = new MenuTracker(tracker, this.input);
    }

    /**
     * Конструктор класса StartUI.
     * @param input - способ ввода данных в приложение
     * @param tracker - установка трекера
     * */
    public StartUI(Input input, Tracker tracker) {

        this.input = input;
        this.tracker = tracker;
        menu = new MenuTracker(this.tracker, this.input);
    }

    /**
     * Возвращает трекер из пользовательского интерфейса.
     * @return трекер
     * */
    public Tracker getTracker() {
        return tracker;
    }

    /**
     * Метод для теста работы пользовательского интерфейса пользователя.
     * */
    public void init() {
        do {
            menu.show();
            menu.select();
        } while (!"y".equals(input.ask("Exit? y")));

    }

    /**
     * Метод для теста работы пользовательского интерфейса пользователя без меню.
     * */
    public void initWithoutMenu() {
        do {
            menu.select();
        } while (!"y".equals(input.ask("Exit? y")));

    }

    /**
     * Точка входа в приложение.
     * @param args - аргументы переданные в приложение из консоли.
     * */
    public static void main(String[] args) {
        StartUI ui = null;
        try {
            ui = new StartUI(new ConsoleInput());
            ui.init();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ui != null) {
                ui.menu.close();
            }
        }
    }
}
