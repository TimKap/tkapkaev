package ru.job4j.professions.engineer;
/**
* Class Drawing описывает чертеж.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 30.03.2017
*/
public class Drawing {
	/** название задачи. */
	private String name;

	/**
	* Конструктор класса Drawing.
	* @param name - имя чертежа
	*/
	public Drawing(String name) {
		this.name = name;
	}

	/**
	* Возвращает имя чертежа.
	* @return имя чертежа
	*/
	public String getName() {
		return name;
	}

}