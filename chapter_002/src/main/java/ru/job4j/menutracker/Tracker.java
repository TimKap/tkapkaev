package ru.job4j.menutracker;
import ru.job4j.models.Item;
import java.util.Arrays;
import java.util.Random;
/**
* Class Tracker обеспечивает работу с заявками.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 09.04.2017
*/
public class Tracker {
	/** Заявки. */
	private Item[] items = new Item[10];

	/** Количество заявок.*/
	private int position;

	/** Генератор случайного числа.*/
	private static final Random RN = new Random();

	/**
	* Генератор идентификационного номера.
	* @return id в формате строки
	*/
	String generateId() {
		return String.valueOf(RN.nextInt());
	}

	/**
	* Добавление заявки.
	* @param item - заявка
	* @return  добавленная заявка
	*/
	public Item add(Item item) {
		item.setId(generateId());
		items[position++] = item;
		return item;
	}

	/**
	* Редактирование заявки.
	* @param item - редактируемая заявка
	*/
	public void update(Item item) {

		for (int i = 0; i < position; i++) {
			if ((items[i].getId().equals(item.getId()))) {
				items[i] = item;
				break;
			}
		}
	}

	/**
	* Удаление заявки.
	* @param item - удаляемая заявка
	*/
	public void delete(Item item) {
		int i;
		for (i = 0; i < position; i++) {
			if (item.getId().equals(items[i].getId())) {
				break;
			}
		}
		if (i < position - 1) {
			System.arraycopy(items, i + 1, items, i, position - i - 1);
			items[position - 1] = null;
			position--;
		} else {
			if (i == (position - 1)) {
				items[position - 1] = null;
				position--;
			}

		}
	}

	/**
	* Получение списка всех заявок.
	* @return список всех заявок
	*/
	public Item[] findAll() {
		Item[] buf = new Item[position];
		for (int i = 0; i < position; i++) {
			buf[i] = items[i];
		}
		return buf;
	}

	/**
	* Получение списка заявок с одинаковым именем.
	* @param key - имя заявки
	* @return список заявок с одинаковым именем.
	*/
	public Item[] findByName(String key) {
		Item[] result = new Item[position];
		int i = 0;
		for (Item item : items) {
			if ((item != null) && key.equals(item.getName())) {
				result[i++] = item;
			}
		}
		return Arrays.copyOf(result, i);
	}

	/**
	* Возвращение заявки по Id.
	* @param id - идентификационный номер
	* @return - заявка
	*/
	public Item findById(String id) {
		Item result = null;
		for (Item item : items) {
			if ((item != null) && id.equals(item.getId())) {
				result = item;
				break;
			}
		}
		return result;
	}
}