 package ru.job4j.array;
import java.util.Arrays;
/**
* Class ArrayDuplicate уборка повторяющихся слов из массива.
* @author Timur Kapkaev (timur.kap@ayndex.ru)
* @version $Id$
* @since 17.03.2017
*/
public class ArrayDuplicate {
	/**
	* Удаление повторяющихся элементов из массива.
	* @param array массив со строками
	* @return массив без повторяющихся элементов
	*/
	public String[] remove(String[] array) {

		/* предполагаемое число неповторяющихся элементов массива*/
		int k = array.length;
		/*Внешний цикл - задает элемент с которым будут сравниваться оставшиеся элементы массива*/
		for (int i = 0; i < k - 1; i++) {
			int j = i + 1;
			/* Цикл, обеспечивающий сравнение выбранного элемента с оставшимися элементами массива*/
			while (j < k) {
				String buf;
				if (array[i].equals(array[j])) {
					/*Поместить повторяющийся элемент в конец массива и уменьшить число предполагаемых неповторяющихся элементов массива*/
					buf = array[j];
					array[j] = array[k - 1];
					array[k - 1] = buf;
					k--;
				} else {
					j++;
					}
			}

		}
		return Arrays.copyOf(array, k);
	}
}