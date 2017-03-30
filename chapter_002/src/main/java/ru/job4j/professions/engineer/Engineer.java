package ru.job4j.professions.engineer;
import  ru.job4j.professions.Profession;
import java.util.Arrays;
/**
* Class Engineer описывает профессию инженера.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 29.03.2017
*/
public class Engineer extends Profession {
	/** набор навыков инженера.*/
	private String[] skills = new String[0];
	/**
	* Конструктор класса Engineer.
	* @param name - имя инженера
	* @param age - возраст инженера
	* @param graduation - полученное инженером образование
	* @param specialization - специализация инженера
	*/
	public Engineer(String name, int age, String graduation, String specialization) {
		super(name, age, graduation, specialization);
	}

	/**
	* Возвращает навыки инженера.
	* @return навыки инженера
	*/
	public String[] getSkills() {
		return skills;
	}

	/**
	* Добавление навыка инженеру.
    * @param skill - навык инженера
	*/
	public void addSkill(String skill) {
		String[] buf;
		buf = Arrays.copyOf(skills, skills.length + 1);
		buf[skills.length] = skill;
		skills = buf;
	}

	/**
	* Удаление навыка у инженера.
    * @param skill - навык инженера
	*/
	public void deleteSkill(String skill) {
		String buf;
		int i, k;
		/*Поиск элемента, который необходимо удалить*/
		for (i = 0; i < skills.length; i++) {
			if (skill.equals(skills[i])) {
				break;
			}
		}
		/* Затираем удаляемый элемент, сдвигая элементы массива*/
		for (k = i; k < (skills.length - 1); k++) {
			buf = skills[k];
			skills[k] = skills[k + 1];
			skills[k + 1] = buf;
		}
		/* Обрезаем крайний элемент*/
		skills = Arrays.copyOf(skills, k);

	}
	/**
	* Создание чертежа по задаче.
	* @param task - задача для инженера
	* @return чертеж
	*/
	public Drawing drawSketch(Task task) {
		return new Drawing("Схема" + task.getName());
	}

	/**
	* @param device - устройство
	*/
	public void fixup(Device device) {
		device.setStatus(5);
	}
}