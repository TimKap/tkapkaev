package ru.job4j.professions.engineer;
/**
* Class Device описывает устройство.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 30.03.2017
*/
public class Device {
	/** Название задачи. */
	private String name;
	/** Состояние устройства. */
	private int status;

	/**
	* Конструктор класса Device.
	* @param name - имя устройства
	*/
	public Device(String name) {
		this.name = name;
	}

	/**
	* Возвращает название устройства.
	* @return название устройства
	*/
	public String getName() {
		return name;
	}

	/**
	* Возвращает состояние устройства.
	* @return состояние устройства
	*/
	public int getStatus() {
		return status;
	}
	/**
	* Задает состояние устройства.
	* @param status - состояние устройства
	*/
	public void setStatus(int status) {
		this.status = status;
	}

}