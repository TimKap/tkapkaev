package ru.job4j.models;
/**
* Class Item описывает заявку.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 31.03.2017
*/
public class Item {

	/** Идентификацилонный номер заявки.*/
	private String id;
	/** Имя заявки.*/
	private String name;
	/** Описание заявки.*/
	private String description;
	/** Дата создания заявки.*/
	private long create;
	/** Комментарии к заявке.*/
	private String[] comments;

	/**
	* Конструктор заявки.
	* @param name - название заявки
	* @param description - описание заявки
	* @param create - дата создания заявки
	*/
	public Item(String name, String description, long create) {
		this.name = name;
		this.description = description;
		this.create = create;
	}

	/**
	 * Конструктор заявки.
	 * @param name - название заявки
	 * @param description - описание заявки
	 */
	public Item(String name, String description) {
		this.name = name;
		this.description = description;

	}

	/**
	* Получение id заявки.
	* @return id заявки
	*/
	public String getId() {
		return id;
	}

	/**
	* Получение имени заявки.
	* @return имя заявки
	*/
	public String getName() {
		return name;
	}

	/**
	* Получение описания заявки.
	* @return описание заявки
	*/
	public String getDescription() {
		return description;
	}

	/**
	* Получение даты создания заявки.
	* @return дата создания заявки
	*/
	public long getCreate() {
		return create;
	}

	/**
	* Получение комментариев к заявке.
	* @return комментарии к заявке
	*/
	public String[] getComments() {
		return comments;
	}

	/**
	* Присвоение идентификационного номера заявке.
	* @param id - идентификацтионный номер заявки
	*/
	public void setId(String id) {
		this.id = id;
	}

	/**
	* Добавление комментариев к заявке.
	* @param comments - комментарии
	*/
	public void setComments(String[] comments) {
		this.comments = comments;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Item item = (Item) o;

		return id != null ? id.equals(item.id) : item.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}