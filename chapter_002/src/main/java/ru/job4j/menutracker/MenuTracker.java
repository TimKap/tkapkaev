package ru.job4j.menutracker;
import ru.job4j.models.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class MenuTracker описывает меню для Трекера.
 *
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 09.04.2017
 */
public class MenuTracker {
	/**
	 * Трекер, который используется в меню.
	 */
	private final Tracker tracker;
	/**
	 * Система ввода, используемая в меню.
	 */
	private final Input input;
	/**
	 * Пункты меню.
	 */
	private final List<UserAction> actions;
	/**
	 * Выход из меню.
	 */
	public static final int EXIT = 6;

	/**
	 * Конструктор класса MenuTracker.
	 *
	 * @param tracker - используемый трекер задач
	 * @param input   - система ввода информации.
	 */

	public MenuTracker(Tracker tracker, Input input) {
		this.tracker = tracker;
		this.input = input;
		actions = new ArrayList<>();
		fillActions();
	}

	/**
	 * Инициализация пунктов меню меню трекера.
	 */
	public void fillActions() {

        /* Инициализация пунктов меню*/
		actions.addAll(Arrays.asList(new AddItem(), new ShowAllItems(), new EditItem(), new DeleteItem(), new FindItemById(), new FindItemsByName()));
	}

	/**
	 * Вывод на консоль меню.
	 */
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
		boolean invalid = true;
		int i = 0;
		do {
			try {
				i++;
				key = Integer.valueOf(input.ask("Select"));
				if (key > EXIT) {
					throw new OutOfRangeMenuException();
				}
				actions.get(key).execute();
				invalid = false;
			} catch (NumberFormatException e) {
				System.out.println("Please enter validate data again");
			} catch (OutOfRangeMenuException e) {
				System.out.println("Please choose correct action");

			}
		} while (invalid && (i < 3));

	}

	/**
	 * Вывод заявки на консоль.
	 *
	 * @param item - заявка.
	 */
	private void showItem(Item item) {
		if (item != null) {
			System.out.println("-----------------------------");
			System.out.printf("Name: %s     Data: %s\r\n", item.getName(), item.getCreate());
			System.out.println(item.getDescription());
			System.out.printf("ID: %s\r\n", item.getId());
			System.out.println("-----------------------------");
		}
	}

	/**
	 * Class AddItem описывает пункт меню Добавление заявки.
	 */
	private class AddItem extends BaseAction {

		/**
		 * Конструктор класса AddItem.
		 */
		private AddItem() {
			super("Add new item", 0);
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
			boolean invalid = true;
			int i = 0;
			do {
				try {
					i++;
					create = Long.valueOf(input.ask("Print time of creation new Item"));
					invalid = false;
					tracker.add(new Item(name, description, create));
				} catch (NumberFormatException | SQLException e) {
					System.out.println("Please enter correct date");
				}
			} while (invalid && i < 3);

		}
	}

	/**
	 * Class ShowAllItems описывает пункт меню Вывод всех заявок.
	 */
	private class ShowAllItems extends BaseAction {

		/**
		 * Конструктор класса ShowAllItems.
		 */
		private ShowAllItems() {
			super("Show all items", 1);
		}

		/**
		 * Исполнение действий пункта меню.
		 */
		public void execute() {
			List<Item> items;
			try {
				items = tracker.findAll();
				if (items.isEmpty()) {
					System.out.println("Tracker is empty");
				} else {
					for (Item item : items) {
						showItem(item);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Class EditItem описывает пункт меню Редактирование заявки.
	 */
	private class EditItem extends BaseAction {

		/**
		 * Конструктор класса EditItem.
		 */
		private EditItem() {
			super("Edit item", 2);
		}

		/**
		 * Исполнение действий пункта меню.
		 */
		public void execute() {
			String id;
			Item item;
			Item updatedItem;
			id = input.ask("Print id of item which you wont to change");
			try {
				item = tracker.findById(id);
				if (item != null) {
					String name;
					name = input.ask("Print the name of new Item");
					String description;
					description = input.ask("Print description of new Item");
					long create;
					int i = 0;
					boolean invalid = true;
					do {
						try {
							i++;
							create = Long.valueOf(input.ask("Print time of creation new Item"));
							invalid = false;
							updatedItem = new Item(name, description, create);
							updatedItem.setId(item.getId());
							tracker.update(updatedItem);
						} catch (NumberFormatException | SQLException e) {
							System.out.println("Please enter correct date");
						}
					} while (invalid && (i < 3));
				} else {
					System.out.println("Item nod founded");
				}
			} catch (SQLException e) {
				System.out.println("Item nod founded");
			}

		}
	}

	/**
	 * Class DeleteItem описывает пункт меню Удаление заявки.
	 */
	private class DeleteItem extends BaseAction {
		/**
		 * Конструктор класса DeleteItem.
		 */
		private DeleteItem() {
			super("Delete item", 3);
		}

		/**
		 * Исполнение действий пункта меню.
		 */
		public void execute() {
			String id;
			Item item;
 			id = input.ask("Print id of item which you wont to delete");
			try {
				item = tracker.findById(id);
				if (item != null) {
					try {
						tracker.delete(item);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Item nod founded");
				}
			} catch (SQLException e) {
				System.out.println("Item nod founded");
			}

		}
	}

	/**
	 * Class FindItemById описывает пункт меню Поиск заявки по Id.
	 */
	private class FindItemById extends BaseAction {
		/**
		 * Конструктор класса FindItemById.
		 */
		private FindItemById() {
			super("Find item by Id", 4);
		}

		/**
		 * Исполнение действий пункта меню.
		 */
		public void execute() {
			String id;
			Item item;
			id = input.ask("Print id of item which you wont to find ");
			try {
				item = tracker.findById(id);
				if (item != null) {
					showItem(item);
				} else {
					System.out.println("Item nod founded");
				}
			} catch (SQLException e) {
				System.out.println("Item nod founded");
			}

		}
	}

	/**
	 * Class FindItemsByName описывает пункт меню Поиск заявок по имени.
	 */
	private class FindItemsByName extends BaseAction {
		/**
		 * Конструктор класса FindItemsByName.
		 */
		private FindItemsByName() {
			super("Find items by name", 5);
		}

		/**
		 * Исполнение действий пункта меню.
		 */
		public void execute() {
			String name;
			List<Item> items;
			name = input.ask("Print name of item which you wont to find");
			try {
				items = tracker.findByName(name);
				if (items.isEmpty()) {
					System.out.println("Items nod founded");
				} else {
					for (Item item : items) {
						showItem(item);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Завершение работы с MenuTracker.
	 *
	 * */
	public void close() {
		try {
			tracker.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
