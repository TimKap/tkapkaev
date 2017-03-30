package ru.job4j.professions.teacher;
import  ru.job4j.professions.Profession;
/**
* Class Teacher описывает профессию учителя.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 29.03.2017
*/
public class Teacher extends Profession {
	/** Нагрузка на преподавателя.*/
	private int workLoad;
	/**
	* Конструктор класса Teacher.
	* @param name - имя преподавателя
	* @param age - возраст преподавателя
	* @param graduation - полученное преподавателем образование
	* @param specialization - специализация преподавателя
	*/
	public Teacher(String name, int age, String graduation, String specialization) {
		super(name, age, graduation, specialization);
	}

	/**
	* Возвращает инагрузку на преподавателя.
	* @return нагрузка на преподавателя
	*/
	public int getWorkLoad() {
		return workLoad;
	}

	/**
	* Установка нагрузки на преподавателя.
	* @param workLoad - нагрузка на преподавателя
	*/
	public void setWorkLoad(int workLoad) {
		this.workLoad = workLoad;
	}

    /**
	* Обучение ученика.
	* @param student - ученик
	*/
	public void teach(Student student) {
		student.setSkill(getSpecialization());
	}

	/**
	* Проверка контрольной работы.
    * @param doc - документ с контррольной работой.
	* @return оценка за работу
	*/
	public int checkWork(Document doc) {
		doc.setMark(1 + (int) (Math.random() * 5D));
		return doc.getMark();
	}

}