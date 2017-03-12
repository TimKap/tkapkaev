package ru.job4j.calculator;

/**
* Class Calculator простой калькулятор.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 12.03.2017
*/
public class Calculator {
	/** Результат арифметической операции.*/
	private double result;

	/**
	* Сложение двух вещественных  чисел.
	* @param first  1-е слагаемое
	* @param second 2-е слагаемое
 	*/
	public void add(double first, double second) {
		this.result = first + second;
	}

    /**
	* Разность двух вещественных  чисел.
	* @param first  уменьшаемое
	* @param second вычитаемое
 	*/
	public void substruct(double first, double second) {
		this.result = first - second;
	}

	/**
	* Деление двух вещественных  чисел.
	* @param first  делимое
	* @param second делитель
 	*/
	public void div(double first, double second) {
		this.result = first / second;
	}

	/**
	* Произведение двух вещественных  чисел.
	* @param first  1-ый множитель
	* @param second 2-ой множитель
 	*/
	public void multiple(double first, double second) {
		this.result = first * second;
	}

	/**
	* Метод возвращает результат арифметической операции.
	* @return результат арифметической операции
	*/
	public double getResult() {
		return this.result;
	}

}