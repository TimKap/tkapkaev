package ru.job4j.menutracker;
import ru.job4j.models.Item;

/**
 * Class MenuTracker описывает меню для Трекера.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 09.04.2017
 */
public class MenuTracker {
    /** Трекер, который используется в меню.*/
    private final Tracker tracker;
    /** Система ввода, используемая в меню.*/
    private final Input input;
    /** Пункты меню.*/
    private final UserAction[] actions;
    /** Выход из меню.*/
    public static final int EXIT = 6;

	/**
	* Конструктор класса MenuTracker.
	* @param tracker - используемый трекер задач
	* @param input - система ввода информации.
	*/

	public MenuTracker(Tracker tracker, Input input) {
		this.tracker = tracker;
		this.input = input;
		actions = new UserAction[6];
		fillActions();
	}

    /**
     * Инициализация пунктов меню меню трекера.
     * */
    public void fillActions() {

        /* Инициализация пунктов меню*/
        actions[0] = new AddItem();
        actions[1] = new ShowAllItems();
        actions[2] = new EditItem();
        actions[3] = new DeleteItem();
        actions[4] = new FindItemById();
        actions[5] = new FindItemsByName();
    }

    /**
     * Вывод на консоль меню.
     * */
    public void show() {
    	for (UserAction action : actions) {
			System.out.println(action.info());
		}

    }

    /**
     * Выбор пункта меню.
     */
    public void select() {
		int key;
		key = Integer.valueOf(input.ask("Select"));
		if (key < EXIT) {
			actions[key].execute();
		}
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
	 * Class AddItem описывает пункт меню Добавление заявки.
	 * */
	private class AddItem implements UserAction {
    	/**
		* Ключ пункта меню Добавление заявки.
		* @return  ключ меню
		*/
		public int key() {
			return 0;
		}
		/**
		* Исполнение действий пункта меню.
		*/
		public void execute() {
			String name;
			name = input.ask("Print the name of new Item");
			String description;
			description = input.ask("Print description of new Item");
			long create;
			create = Long.valueOf(input.ask("Print time of creation new Item"));
			tracker.add(new Item(name, description, create));
		}

		/**
		* Информация о пункте меню.
		* @return информация о пункте мею в формате строки
		*/
		public String info() {
			return String.format("%s. %s", key(), "Add new item");
		}
	}
	/**
	 * Class ShowAllItems описывает пункт меню Вывод всех заявок.
	 * */
	private class ShowAllItems implements UserAction {
		/**
		* Ключ пункта меню Вывести все заявки.
		* @return ключ меню
		*/
		public int key() {
			return 1;
		}

		/**
		* Исполнение действий пункта меню.
		*/
		public void execute() {
			for (Item item : tracker.findAll()) {
				if (item != null) {
					showItem(item);
				}
			}
		}

		/**
		* Информация о пункте меню.
		* @return информация о пункте мею в формате строки
		*/
		public String info() {
			return String.format("%s. %s", key(), "Show all items");
		}
	}
	/**
	 * Class EditItem описывает пункт меню Редактирование заявки.
	 * */
	private class EditItem implements UserAction {
		/**
		* Ключ пункта меню Редактирование заявки.
		* @return ключ меню
		*/
		public int key() {
			return 2;
		}

		/**
		* Исполнение действий пункта меню.
		*/
		public void execute() {
			String id;
			Item item;
			Item updatedItem;
			id = input.ask("Print id of item which you wont to change");
			item = tracker.findById(id);
			if (item != null) {
				String name;
				name = input.ask("Print the name of new Item");
				String description;
				description = input.ask("Print description of new Item");
				long create;
				create = Long.valueOf(input.ask("Print time of creation new Item"));
				updatedItem = new Item(name, description, create);
				updatedItem.setId(item.getId());
				tracker.update(updatedItem);
			}
		}

		/**
		* Информация о пункте меню.
		* @return информация о пункте мею в формате строки
		*/
		public String info() {
			return String.format("%s. %s", key(), "Edit item");
		}

	}
	/**
	 * Class DeleteItem описывает пункт меню Удаление заявки.
	 * */
	private class DeleteItem implements UserAction {
		/**
		* Ключ пункта меню Удаление заявки.
		* @return ключ меню
		*/
		public int key() {
			return 3;
		}

		/**
		* Исполнение действий пункта меню.
		*/
		public void execute() {
			String id;
			Item item;
			id = input.ask("Print id of item which you wont to delete");
			item = tracker.findById(id);
			if (item != null) {
				tracker.delete(item);
			}
		}

		/**
		* Информация о пункте меню.
		* @return информация о пункте мею в формате строки
		*/
		public String info() {
			return String.format("%s. %s", key(), "Delete item item");
		}
	}
	/**
	 * Class FindItemById описывает пункт меню Поиск заявки по Id.
	 * */
	private class FindItemById implements UserAction {
		/**
		* Ключ пункта меню Поиска заявки по Id.
		* @return ключ меню
		*/
		public int key() {
			return 4;
		}

		/**
		* Исполнение действий пункта меню.
		*/
		public void execute() {
			String id;
			Item item;
			id = input.ask("Print id of item which you wont to find ");
			item = tracker.findById(id);
			if (item != null) {
				showItem(item);
			}
		}

		/**
		* Информация о пункте меню.
		* @return информация о пункте мею в формате строки
		*/
		public String info() {
			return String.format("%s. %s", key(), "Find item by Id");
		}
	}
	/**
	 * Class FindItemsByName описывает пункт меню Поиск заявок по имени.
	 * */
	private class FindItemsByName implements UserAction {
		/**
		* Ключ пункта меню Поиска заявок по имени.
		* @return ключ меню
		*/
		public int key() {
			return 5;
		}

		/**
		* Исполнение действий пункта меню.
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

		/**
		* Информация о пункте меню.
		* @return информация о пункте мею в формате строки
		*/
		public String info() {
			return String.format("%s. %s", key(), "Find items by name");
		}
	}
}
