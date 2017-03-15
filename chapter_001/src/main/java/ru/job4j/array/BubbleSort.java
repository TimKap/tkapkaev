package ru.job4j.array;
/**
* Class BubbleSort сортировка методом "пузырька".
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 15.03.2017
*/
public class BubbleSort {
	/**
	* Сортировка по возрастанию массива целых чисел методом "пузырька".
	* @param array массив целых чисел
	* @return отсортиорванный по возрастанию массив.
	*/
	public int[] sort(int[] array) {
		boolean fl = true;
		int buf;
		while (fl) {
			fl = false;
			for (int i = 0; i < array.length - 1; i++) {
				if (array[i] > array[i + 1]) {
					buf = array[i];
					array[i] = array[i + 1];
					array[i + 1] = buf;
					fl = true;
				}
			}

		}
		return array;
	}

}