package ru.job4j.professions.doctor;
/**
* Class Patient описывает пацента.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 30.03.2017
*/
public class Patient {
	/** Имя пациента. */
	private String name;

	/** Заболевание пацента.*/
	private Ilness ilness;

	/**
	* Конструктор класса Patient.
	* @param name - имя пациента
	*/
	public Patient(String name) {
		this.name = name;
	}

	/**
	* Возвращает имя пациента.
	* @return имя пациента
	*/
	public String getName() {
		return name;
	}

	/**
	* Возвращает заболевание пациента.
	* @return заболевания пациента
	*/
	public Ilness getIlness() {
		return ilness;
	}

	/**
	* Устанавливает заболевание пациента.
	* @param ilness - заболевание
	*/
	public void setIlness(Ilness ilness) {
		this.ilness = ilness;
	}

}