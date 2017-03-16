package ru.job4j.array;
/**
* Class RotateArray класс поворота матрицы по ходу часовой стрелки на 90 градусов.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 16.03.2017
*/
public class RotateArray {
	/**
	* Поворот матрицы по ходу часовой стрелки на 90 градусов.
	* @param array масси целочисленных элементов
	* @return массив повернутый по ходу часовой стрелки на 90 градусов
	*/
	public int[][] rotate(int[][] array) {
		int length = array.length;
		int buf;

		/* Обход массива по строкам */
		for (int i = 0; i < length / 2; i++) {
			/* Обход массива по столбцам */
			for (int j = i; j < length - i - 1; j++) {
				/*Перестановка 4-х элементов массива (поворот на 90 градусов 4-х элементов)*/
				buf = array[i][j];
				array[i][j] = array[length - j - 1][i];
				array[length - j - 1][i] = array[length - i - 1][length - j - 1];
				array[length - i - 1][length - j - 1] = array[j][length - i - 1];
			    array[j][length - i - 1] = buf;
			}
		}
		return array;

	}

}