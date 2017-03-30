package ru.job4j.professions.doctor;
import  ru.job4j.professions.Profession;
import java.util.Arrays;
/**
* Class Doctor описывает профессию доктора.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 30.03.2017
*/
public class Doctor extends Profession {
	/** Рейтинг доктора.*/
	private float popularity;
	/** Закрепленные за доктором пациенты. */
	private Patient[] patients;
	/** Число пациентов, закрепленных за доктором.*/
	private int patientsNumber;

	/**
	* Конструктор класса Doctor.
    * @param name - имя доктора
	* @param age - возраст доктора
	* @param graduation - полученное доктором образование
	* @param specialization - специализация доктора
	*/
	public Doctor(String name, int age, String graduation, String specialization) {
		super(name, age, graduation, specialization);
		patients = new Patient[5];
	}

	/**
	* Возвращает рейтинг доктора.
	* @return рейтинг доктора
	*/
	public float getPopularity() {
		return popularity;
	}

	/**
	* Возвращает пациентов, закрепленных за доктором.
	* @return пациенты, закрепленные за доктором
	*/
	public Patient[] getPatients() {
		return patients;
	}

	/**
	* Возвращает количество пациентов, закрепленных за доктором.
	* @return количество пациентов, закрпленных за доктором
	*/
	public int getPatientsNumber() {
		return patientsNumber;
	}

	/**
	* Установка прейтинга доктора.
	* @param popularity - рейтинг доктора
	*/
	public void setPopularity(float popularity) {
		this.popularity = popularity;
	}

	/**
	* Закрепление пациента за доктором.
	* @param patient - пациент
	*/
	public void addPatient(Patient patient) {
		if (patientsNumber == patients.length) {
			patients = Arrays.copyOf(patients, patientsNumber + 5);
		}
		patients[patientsNumber] = patient;
		patientsNumber++;
	}
	/**
	* Удаление пациента.
	* @param patient - пациент
	*/
	public void deletePatient(Patient patient) {
		int i, k;
		Patient buf;
		/*Поиск элемента, который необходимо удалить*/
		for (i = 0; i < patientsNumber; i++) {
			if (patient.getName().equals(patients[i].getName())) {
				break;
			}
		}
		/* Затираем удаляемый элемент, сдвигая элементы массива*/
		for (k = i; k < patientsNumber - 1; k++) {
			buf = patients[k];
			patients[k] = patients[k + 1];
			patients[k + 1] = buf;
		}
		patientsNumber--;
		/* Обрезаем крайний элемент*/
		patients = Arrays.copyOf(patients, patientsNumber);
	}
	/**
	* Перенаправление пациента к другому доктору.
	* @param patient - пациент
	* @param doctor - другой доктор
	*/
	public void redirectPatient(Patient patient, Doctor doctor) {
		doctor.addPatient(patient);
		deletePatient(patient);
	}

	/**
	* Проведение доктором диагностики пациента.
	* @param patient - пациент
	* @return заболевание
	*/
	public Ilness diagnostic(Patient patient) {
		patient.setIlness(new Ilness("Заболевание из области" + this.getSpecialization()));
		return patient.getIlness();
	}

	/**
	* проведение доктором операции.
	* @param patient - пациент
	*/
	public void operation(Patient patient) {
		patient.setIlness(null);
	}

}