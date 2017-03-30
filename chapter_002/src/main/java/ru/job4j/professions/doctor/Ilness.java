package ru.job4j.professions.doctor;
/**
* Class Ilness описывает заболевание.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 30.03.2017
*/
public class Ilness {
	/** Имя заболевания. */
	private String name;

	/**
	* Конструктор класса Ilness.
	* @param name - имя заболевания
	*/
	public Ilness(String name) {
		this.name = name;
	}

	/**
	* Возвращает имя пзаболевания.
	* @return имя заболевания
	*/
	public String getName() {
		return name;
	}
}