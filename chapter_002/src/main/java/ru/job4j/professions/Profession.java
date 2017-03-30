package ru.job4j.professions;
/**
* Class Profession описывает профессию.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 29.03.2017
*/
public abstract class Profession {

	/** Имя сотрудника.*/
	private String name;
	/** Возраст сотрудника.*/
	private int age;
	/** Образование сотрудника.*/
	private String graduation;
	/** Зарплата сотрудника.*/
	private float salary;
	/** Специализация сотрудника.*/
	private String specialization;

	/**
	* Конструктор класса Profession.
	* @param name - имя работника
	* @param age - возраст раблтника
	* @param graduation - полученное работником образование
	* @param specialization - специализация работника
	*/
	public Profession(String name, int age, String graduation, String specialization) {
		this.name = name;
		this.age = age;
		this.graduation = graduation;
		this.specialization = specialization;
	}

	/**
	* Возвращает имя работника.
	* @return имя
	*/
	public String getName() {
		return name;
	}

	/**
	* Возвращает воззраст работника.
	* @return возраст
	*/
	public int getAge() {
		return age;
	}

	/**
	* Возвращает образование работника.
	* @return образование
	*/
	public String getGraduation() {
		return graduation;
	}

	/**
	* Возвращает зарплату работника.
	* @return зарплата
	*/
	public float getSalary() {
		return salary;
	}

	/**
	* Возвращает зспециализацию работника.
	* @return специализация
	*/
	public String getSpecialization() {
		return specialization;
	}

	/**
	* Установка возраста сотрудника.
	* @param age - возраст сотрудника.
	*/
	public void setAge(int age) {
		this.age = age;
	}

	/**
	* Установка зарплаты сотрудника.
	* @param salary - зарплата сотрудника.
	*/
	public void setSalary(float salary) {
		this.salary = salary;
	}

}