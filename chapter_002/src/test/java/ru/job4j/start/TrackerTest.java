package ru.job4j.start;
import ru.job4j.models.Item;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Class TrackerTest содержит тесты к классу Tracker.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 01.04.2017
*/
public class TrackerTest {
	/**
	* Тест для добавления заявки в список заявок.
	*/
	@Test
	public void whenAddItemThenTrackerAddItToList() {
		Tracker tracker = new Tracker();
		Item item = new Item("Item1", "test", 1L);
		tracker.add(item);
		Item expected = item;
		Item result = tracker.findAll()[0];
		assertThat(result, is(expected));
	}

	/**
	* Тест для редактирования заявки.
	*/
	@Test
	public void whenUpdateItemThenGetExpected() {
		Tracker tracker = new Tracker();
		Item item = new Item("Item1", "test", 1L);
		tracker.add(item);

		Item expected = new Item("midifiedItem", "test", 2L);
		expected.setId(item.getId());

		/* Замена заявки item на expected*/
		tracker.update(expected);
		Item result = tracker.findAll()[0];

		assertThat(result, is(expected));
	}

    /**
	* Тест для удаления заявки.
	*/
	@Test
	public void whenDeleteItemThenGetReducedTracker() {
		Tracker tracker = new Tracker();
		Item[] items = {new Item("Item1", "test", 1L), new Item("Item2", "test", 2L), new Item("Item3", "test", 3L)};
		tracker.add(items[0]);
		tracker.add(items[1]);
		tracker.add(items[2]);

		Item[] expected = {items[0], items[2]};

		/* Удаление заявки */
		tracker.delete(items[1]);

		Item[] result = tracker.findAll();

		assertThat(result, is(expected));
	}

	/**
	* Тест получение списка всех заявок.
	*/
	@Test
	public void whenFindAllThenGetAllNotNullItems() {
		Tracker tracker = new Tracker();
		Item[] items = {new Item("Item1", "test", 1L), new Item("Item2", "test", 2L), new Item("Item3", "test", 3L)};
		tracker.add(items[0]);
		tracker.add(items[1]);
		tracker.add(items[2]);

		Item[] expected = {items[0], items[1], items[2]};


		Item[] result = tracker.findAll();

		assertThat(result, is(expected));
	}

	/**
	* Тест получение списка заявок по имени.
	*/
	@Test
	public void whenFindByNameThenGetAllItemsWithThisName() {
		Tracker tracker = new Tracker();
		Item[] items = {new Item("Item1", "test", 1L), new Item("Item2", "test", 2L), new Item("Item1", "test", 3L)};
		tracker.add(items[0]);
		tracker.add(items[1]);
		tracker.add(items[2]);

		Item[] expected = {items[0], items[2]};


		Item[] result = tracker.findByName("Item1");

		assertThat(result, is(expected));
	}

	/**
	* Тест получение заявки по Id.
	*/
	@Test
	public void whenFindByIdThenGetItemWithThisId() {
		Tracker tracker = new Tracker();
		Item[] items = {new Item("Item1", "test", 1L), new Item("Item2", "test", 2L), new Item("Item1", "test", 3L)};
		tracker.add(items[0]);
		tracker.add(items[1]);
		tracker.add(items[2]);

		Item expected = items[1];

		Item result = tracker.findById(items[1].getId());

		assertThat(result, is(expected));
	}

}