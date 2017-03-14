package ru.job4j.loop;
/**
* Class Factorial вычисление факториала.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 14.03.2017
*/
public class Factorial {
	/**
	* Вычисление факториала n!.
	* @param n положительное целое число
	* @return факториал числа n!.
	*/
	public int calc(int n) {
		int factorial = 1;
		for (int i = n; i > 1; i--) {
			factorial *= i;
		}
		return factorial;
	}
}