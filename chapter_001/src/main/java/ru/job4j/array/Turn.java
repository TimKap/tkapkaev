package ru.job4j.array;
/**
* Class Turn среверсирование порядка следования элементов массива.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 15.03.2017
*/
public class Turn {
	/**
	* Реверсирование порядка следования элементов массива.
	* @param array массив целочисленных элементов
	* @return реверсированный массив
	*/
	public int[] back(int[] array) {
		int buf;
		for (int i = 0; i < (array.length / 2); i++) {
			buf = array[i];
			array[i] = array[array.length - i - 1];
			array[array.length - i - 1] = buf;
		}

		return array;
	}
}