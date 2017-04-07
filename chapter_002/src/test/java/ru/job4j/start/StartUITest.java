package ru.job4j.start;

import org.junit.Test;
import ru.job4j.models.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Class StartUITest описывает тесты для пользовательского интерфейса трекера.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 06.04.2017
 */
public class StartUITest {

    /**
     * Тест добавление заявки в трекер.
     * */
    @Test
    public void whenAddItemThenTrackerAddItToList() {
        StubInput userActions = new StubInput(new String[]{"0", "Tim", "Item Test 1", "123", "6"});
        StartUI ui = new StartUI(userActions);
        ui.init();
        Tracker tracker = ui.getTracker();
        String result = tracker.findAll()[0].getName();
        String expected = "Tim";
        assertThat(result, is(expected));

        result = tracker.findAll()[0].getDescription();
        expected = "Item Test 1";
        assertThat(result, is(expected));
    }

    /**
     * Тест по удалению заявки.
     */
    @Test
    public void whenDeleteItemThenGetReducedTracker() {
       /* Инициализация трекера*/
        Tracker tracker = new Tracker();
        Item[] items = {new Item("Item1", "test", 1L), new Item("Item2", "test", 2L), new Item("Item3", "test", 3L)};
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);
        /*Ожидаемое значение после удаления заявки*/
        Item[] expected = {items[0], items[2]};

        /*Задание действий пользователя*/
        StubInput userActions = new StubInput(new String[]{"3", tracker.findAll()[1].getId(), "6"});
        /*Формирование пользовательского интерфейса*/
        StartUI ui = new StartUI(userActions, tracker);
        /* Удаление заявки */
        ui.init();

        Item[] result = tracker.findAll();
        assertThat(result, is(expected));
    }

    /**
     * Тест для редактирования заявки.
     */
    @Test
    public void whenUpdateItemThenGetExpected() {
        /*Инициализация трекера*/
        Tracker tracker = new Tracker();
        Item item = new Item("Item1", "test", 1L);
        tracker.add(item);

        /*Задание действий пользователя*/
        StubInput userActions = new StubInput(new String[]{"2", tracker.findAll()[0].getId(), "modified Item", "New test", "2", "6"});
        StartUI ui = new StartUI(userActions, tracker);
        String expected = "modified Item";
        String result;

		/* Замена заявки item на expected*/
        ui.init();

        result = tracker.findAll()[0].getName();
        assertThat(result, is(expected));

        expected = "New test";
        result = tracker.findAll()[0].getDescription();
        assertThat(result, is(expected));
    }

    /**
     * Тест получение заявки по Id.
     */
    @Test
    public void whenFindByIdThenGetItemWithThisId() {
       /* Инициализация трекера*/
        Tracker tracker = new Tracker();
        Item[] items = {new Item("Item1", "test", 1L), new Item("Item2", "test", 2L), new Item("Item3", "test", 3L)};
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);

        StubInput userActions = new StubInput(new String[]{"4", tracker.findAll()[1].getId(), "6"});
        StartUI ui = new StartUI(userActions, tracker);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String expected = "-----------------------------\r\n"
                +
                "Name: " + "Item2" + "     Data: " + "2" + "\r\n"
                +
                "test" + "\r\n"
                +
                "ID: " + tracker.findAll()[1].getId() + "\r\n"
                +
                "-----------------------------" + "\r\n";
        /*Вывод заявки на экран*/
        ui.initWithoutMenu();
        assertThat(out.toString(), is(expected));
    }

    /**
     * Тест получение заявки по Id.
     */
    @Test
    public void whenFindByNameThenGetAllItemsWithThisName() {
       /* Инициализация трекера*/
        Tracker tracker = new Tracker();
        Item[] items = {new Item("Item1", "test", 1L), new Item("Item2", "test", 2L), new Item("Item1", "test", 3L)};
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);

        StubInput userActions = new StubInput(new String[]{"5", "Item1", "6"});
        StartUI ui = new StartUI(userActions, tracker);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String expected = "-----------------------------\r\n"
                +
                "Name: " + "Item1" + "     Data: " + "1" + "\r\n"
                +
                "test" + "\r\n"
                +
                "ID: " + tracker.findAll()[0].getId() + "\r\n"
                +
                "-----------------------------" + "\r\n"
                +
                "-----------------------------\r\n"
                +
                "Name: " + "Item1" + "     Data: " + "3" + "\r\n"
                +
                "test" + "\r\n"
                +
                "ID: " + tracker.findAll()[2].getId() + "\r\n"
                +
                "-----------------------------" + "\r\n";
        /*Вывод заявки на экран*/
        ui.initWithoutMenu();
        assertThat(out.toString(), is(expected));
    }

    /**
     * Тест получение всех заявок.
     */
    @Test
    public void whenFindAllThenGetAllNotNullItems() {
       /* Инициализация трекера*/
        Tracker tracker = new Tracker();
        Item[] items = {new Item("Item1", "test", 1L), new Item("Item2", "test", 2L), new Item("Item3", "test", 3L)};
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);

        StubInput userActions = new StubInput(new String[]{"1", "6"});
        StartUI ui = new StartUI(userActions, tracker);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String expected = "-----------------------------\r\n"
                +
                "Name: " + "Item1" + "     Data: " + "1" + "\r\n"
                +
                "test" + "\r\n"
                +
                "ID: " + tracker.findAll()[0].getId() + "\r\n"
                +
                "-----------------------------" + "\r\n"
                +
                "-----------------------------\r\n"
                +
                "Name: " + "Item2" + "     Data: " + "2" + "\r\n"
                +
                "test" + "\r\n"
                +
                "ID: " + tracker.findAll()[1].getId() + "\r\n"
                +
                "-----------------------------" + "\r\n"
                +
                "-----------------------------\r\n"
                +
                "Name: " + "Item3" + "     Data: " + "3" + "\r\n"
                +
                "test" + "\r\n"
                +
                "ID: " + tracker.findAll()[2].getId() + "\r\n"
                +
                "-----------------------------" + "\r\n";
        /*Вывод заявки на экран*/
        ui.initWithoutMenu();
        assertThat(out.toString(), is(expected));
    }


}
