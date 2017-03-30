package ru.job4j.professions.teacher;

/**
* Class Student описывает ученика.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 29.03.2017
*/
public class Student {
	/** Имя ученика.*/
	private String name;
	/** Навык ученика.*/
	private String skill;

	/**
	* Конструктор класса Student.
	* @param name - имя ученика.
	*/
	public Student(String name) {
		this.name = name;
	}

	/**
	* Возвращает имя студента.
	* @return имя
	*/
	public String getName() {
		return name;
	}

	/**
	* Возвращает навык ученика.
	* @return специализация
	*/
	public String getSkill() {
		return skill;
	}

	/**
	* Установка навыка ученику.
	* @param skill - навык ученика.
	*/
	public void setSkill(String skill) {
		this.skill = skill;
	}
}