package ru.job4j.loop;
/**
* Class Counter - класс счетчикв.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 14.03.2017
*/
public class Counter {
	/**
	* Вычисление суммы четных чиел в пределах заданного диапазона.
	* @param start начало диапазона
	* @param finish конец диапазона
	* @return сумма четных чисел
	*/
	public int add(int start, int finish) {
		int accumulator = 0;
		if ((start % 2) != 0) {
			start++;
		}
		if ((finish % 2) != 0) {
			finish--;
		}

		for (int i = start; i <= finish; i += 2) {
			accumulator += i;
		}
		return accumulator;

	}
}
