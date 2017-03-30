package ru.job4j.professions.engineer;
/**
* Class Task описывает задачу для инженера.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 30.03.2017
*/
public class Task {
	/** название задачи. */
	private String name;

	/**
	* Конструктор класса Task.
	* @param name - задача
	*/
	public Task(String name) {
		this.name = name;
	}

	/**
	* Возвращает название задачи.
	* @return название задачи
	*/
	public String getName() {
		return name;
	}

}