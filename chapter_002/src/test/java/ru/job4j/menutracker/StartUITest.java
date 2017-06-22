package ru.job4j.menutracker;

import org.junit.Test;
import ru.job4j.models.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

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
        StubInput userActions = new StubInput(new String[]{"0", "Tim", "Item Test 1", "123", "y"});
        StartUI ui = new StartUI(userActions);
        ui.init();
        Tracker tracker = ui.getTracker();
        String result = tracker.findAll().get(0).getName();
        String expected = "Tim";
        assertThat(result, is(expected));

        result = tracker.findAll().get(0).getDescription();
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
        List<Item> expected =  new ArrayList<Item>();
        expected.addAll(Arrays.asList(items[0], items[2]));

        /*Задание действий пользователя*/
        StubInput userActions = new StubInput(new String[]{"3", tracker.findAll().get(1).getId(), "y"});
        /*Формирование пользовательского интерфейса*/
        StartUI ui = new StartUI(userActions, tracker);
        /* Удаление заявки */
        ui.init();

        List<Item> result = tracker.findAll();
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
        StubInput userActions = new StubInput(new String[]{"2", tracker.findAll().get(0).getId(), "modified Item", "New test", "2", "y"});
        StartUI ui = new StartUI(userActions, tracker);
        String expected = "modified Item";
        String result;

		/* Замена заявки item на expected*/
        ui.init();

        result = tracker.findAll().get(0).getName();
        assertThat(result, is(expected));

        expected = "New test";
        result = tracker.findAll().get(0).getDescription();
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

        StubInput userActions = new StubInput(new String[]{"4", tracker.findAll().get(1).getId(), "y"});
        StartUI ui = new StartUI(userActions, tracker);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String expected = "-----------------------------\r\n"
                +
                String.format("Name: %s     Data: %s\r\n", "Item2", "2")
                +
                String.format("%s\r\n", "test")
                +
                String.format("ID: %s\r\n", tracker.findAll().get(1).getId())
                +
                "-----------------------------\r\n";
        /*Вывод заявки на экран*/
        ui.initWithoutMenu();
        assertThat(out.toString(), is(expected));
    }

    /**
     * Тест получение заявки по Имени.
     */
    @Test
    public void whenFindByNameThenGetAllItemsWithThisName() {
       /* Инициализация трекера*/
        Tracker tracker = new Tracker();
        Item[] items = {new Item("Item1", "test", 1L), new Item("Item2", "test", 2L), new Item("Item1", "test", 3L)};
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);

        StubInput userActions = new StubInput(new String[]{"5", "Item1", "y"});
        StartUI ui = new StartUI(userActions, tracker);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String expected = "-----------------------------\r\n"
                +
                String.format("Name: %s     Data: %s\r\n", "Item1", "1")
                +
                String.format("%s\r\n", "test")
                +
                String.format("ID: %s\r\n", tracker.findAll().get(0).getId())
                +
                "-----------------------------\r\n"
                +
                "-----------------------------\r\n"
                +
                String.format("Name: %s     Data: %s\r\n", "Item1", "3")
                +
                String.format("%s\r\n", "test")
                +
                String.format("ID: %s\r\n", tracker.findAll().get(2).getId())
                +
                "-----------------------------\r\n";
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

        StubInput userActions = new StubInput(new String[]{"1", "y"});
        StartUI ui = new StartUI(userActions, tracker);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String expected = "-----------------------------\r\n"
                +
                String.format("Name: %s     Data: %s\r\n", "Item1", "1")
                +
                String.format("%s\r\n", "test")
                +
                String.format("ID: %s\r\n", tracker.findAll().get(0).getId())
                +
                "-----------------------------\r\n"
                +
                "-----------------------------\r\n"
                +
                String.format("Name: %s     Data: %s\r\n", "Item2", "2")
                +
                String.format("%s\r\n", "test")
                +
                String.format("ID: %s\r\n", tracker.findAll().get(1).getId())
                +
                "-----------------------------\r\n"
                +
                "-----------------------------\r\n"
                +
                String.format("Name: %s     Data: %s\r\n", "Item3", "3")
                +
                String.format("%s\r\n", "test")
                +
                String.format("ID: %s\r\n", tracker.findAll().get(2).getId())
                +
                "-----------------------------\r\n";
        /*Вывод заявки на экран*/
        ui.initWithoutMenu();
        assertThat(out.toString(), is(expected));
    }
    /**
     * Тест для выбора пункта меню: ввод некорректных данных при выборе пункта меню.
     * */
    @Test
    public void whenSelectMeuUserInsertInvalidDataThenTrySelectAgain() {
        Tracker tracker = new Tracker();
        StubInput userActions = new StubInput(new String[] {"gjhg", "10", "0", "Tom", "test", "1", "y"});
        StartUI ui = new StartUI(userActions, tracker);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        String expected = "Please enter validate data again\r\n"
                        + "Please choose correct action\r\n";
        ui.initWithoutMenu();
        String result = out.toString();
        assertThat(result, is(expected));

    }
    /**
     * Тест для добавления новой заявки: Неправильный ввод времени при создании заявки. Переполнение списка заявок.
     * */
    @Test
    public void whenInsertInvalidTimeThenTryInsertNewTimeAndGetMessage() {

        String[] actions = new String[70];
        actions[0] = "0";
        actions[1] = "Tom";
        actions[2] = "test";
        actions[3] = "jadksjdsal";
        actions[4] = "1";
        actions[5] = "n";

        for (int i = 0; i < 10; i++) {
            actions[6 + 5 * i] = "0";
            actions[6 + 5 * i + 1] = "Tom";
            actions[6 + 5 * i + 2] = "test";
            actions[6 + 5 * i + 3] = "1";
            if (i == 9) {
                actions[6 + 5 * i + 4] = "y";
            } else {
                actions[6 + 5 * i + 4] = "n";
            }
        }
        StubInput userActions = new StubInput(actions);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String expected = "Please enter correct date\r\n";

        StartUI ui = new StartUI(userActions);
        ui.initWithoutMenu();
        String result = out.toString();
        assertThat(result, is(expected));
    }
    /**
     * Тест для вывода всех заявок: Вывод пустого списка заявок.
     * */
    @Test
    public void whenShowAllEmptyListOfItemsThenGetMessage() {
        StubInput userActions = new StubInput(new String[] {"1", "y"});
        StartUI ui = new StartUI(userActions);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        String expected = "Tracker is empty\r\n";
        ui.initWithoutMenu();
        String result = out.toString();
        assertThat(result, is(expected));
    }
    /**
     * Тест для редактирования заявки: Неп заявки с введенным ID. Неправильно введенная дата при добавлении заявки.
     */
    @Test
    public void whenEditAndInsertInvalidDataThenGetMessage() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Tom", "test",  1));
        StubInput userActions = new StubInput(new String[] {"2", "100", "n", "2", tracker.findAll().get(0).getId(), "Lee", "test2", "aaaa", "2", "y"});
        StartUI ui = new StartUI(userActions, tracker);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String expected =  "Item nod founded\r\n"
                          +
                           "Please enter correct date\r\n";
        ui.initWithoutMenu();
        String result = out.toString();
        assertThat(result, is(expected));

        expected = "Lee";
        result = tracker.findAll().get(0).getName();

        assertThat(result, is(expected));

    }

    /**
     * Тест для удаления заявки из трекера. В трекере отсутствует заявка с заявленным ID.
     * */
    @Test
    public void whenDeleteItemWithUnexpectedIdThenGetMessage() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Tom", "test",  1));

        StubInput userActions = new StubInput(new String[] {"3", "1", "y"});
        StartUI ui = new StartUI(userActions, tracker);
        String expected = "Item nod founded\r\n";

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        ui.initWithoutMenu();
        String result = out.toString();
        assertThat(result, is(expected));

    }

    /**
     * Тест для поиска заявки по ID. В трекере отсутствует заявка с заявленным ID.
     * */
    @Test
    public void whenFindByIdItemWithUnexpectedIdThenGetMessage() {

        Tracker tracker = new Tracker();
        tracker.add(new Item("Tom", "test",  1));
        StubInput userActions = new StubInput(new String[] {"4", "1", "y"});
        StartUI ui = new StartUI(userActions, tracker);
        String expected = "Item nod founded\r\n";

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        ui.initWithoutMenu();
        String result = out.toString();
        assertThat(result, is(expected));

    }

    /**
     * Тест для поиска заявки по Имени. В трекере отсутствует заявка с заявленным Именем.
     * */
    @Test
    public void whenFindByNameItemWithUnexpectedNameThenGetMessage() {

        Tracker tracker = new Tracker();
        tracker.add(new Item("Tom", "test",  1));
        StubInput userActions = new StubInput(new String[] {"5", "Tm", "y"});
        StartUI ui = new StartUI(userActions, tracker);
        String expected = "Items nod founded\r\n";

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        ui.initWithoutMenu();
        String result = out.toString();
        assertThat(result, is(expected));

    }

}
