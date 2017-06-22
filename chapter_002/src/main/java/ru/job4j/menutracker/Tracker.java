package ru.job4j.menutracker;
import ru.job4j.models.Item;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
* Class Tracker обеспечивает работу с заявками.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 09.04.2017
*/
public class Tracker {
	/** Заявки. */
	private List<Item> items = new ArrayList<Item>();

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
		items.add(item);
		return item;
	}

	/**
	* Редактирование заявки.
	* @param item - редактируемая заявка
	*/
	public void update(Item item) {

		for (int i = 0; i < items.size(); i++) {
			if ((items.get(i).getId().equals(item.getId()))) {
				items.set(i, item);
				break;
			}
		}

	}

	/**
	* Удаление заявки.
	* @param item - удаляемая заявка
	*/
	public void delete(Item item) {
			items.remove(item);
	}

	/**
	* Получение списка всех заявок.
	* @return список всех заявок
	*/
	public List<Item> findAll() {
		List<Item> buf = new ArrayList<>();
		for (Item item:items) {
			buf.add(item);
		}
		return buf;
	}

	/**
	* Получение списка заявок с одинаковым именем.
	* @param key - имя заявки
	* @return список заявок с одинаковым именем.
	*/
	public List<Item> findByName(String key) {
		List<Item> result = new ArrayList<Item>();
		for (Item item : items) {
			if (item.getName().equals(key)) {
				result.add(item);
			}
		}
		return result;
	}

	/**
	* Возвращение заявки по Id.
	* @param id - идентификационный номер
	* @return - заявка
	*/
	public Item findById(String id) {
		Item result = null;
		for (Item item : items) {
			if (id.equals(item.getId())) {
				result = item;
				break;
			}
		}
		return result;
	}
}