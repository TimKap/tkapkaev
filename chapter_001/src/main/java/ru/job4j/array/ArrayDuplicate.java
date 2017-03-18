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
		/* Проход по массиву array.length раз */
		for (int i = 0; i < array.length; i++) {
			/* Слово для которого осуществляется поиск повторений */
			String comparedWord = array[i];
			if (comparedWord == null) {
				continue;
			} else {
				/* Перебор элементов массива с заменой копий слова comparedWord*/
				for (int j = i + 1; j < array.length; j++) {
					if (comparedWord.equals(array[j])) {
						array[j] = null;
					}
				}
			}
		}
		/*Сжатие массива (устранение "пробелов - null элементов" между эелементами массива)*/
		int lastNullPosition = array.length;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				if (lastNullPosition != array.length) {
					array[lastNullPosition] = array[i];
					array[i] = null;
					lastNullPosition++;
				}
			} else {
				/* "Пустой" элемент найден. С этого места начать подвижку элементов массива.*/
				if (lastNullPosition == array.length) {
					lastNullPosition = i;
				}

			}
		}
		return Arrays.copyOf(array, lastNullPosition);

	}
}