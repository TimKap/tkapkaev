package ru.job4j.doptask;
/**
* Class DopTask обеспечивает создание упорядоченного по возрастанию массива из двух упорядоченных по возрастанию.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 22.03.2017
*/
public class DopTask {
	/**
	* Создание упорядоченного по овзрастанию массива из двух упорядоченных по овзрастанию.
	* @param ar1 - 1ый упорядоченный по возрастанию массив
	* @param ar2 - 2ой упорядоченный по овзрастанию массив
	* @return упорядоченный по возрастанию массив(объединение 1-ого и 2-ого)
	*/
	public int[] concat(int[] ar1, int[] ar2) {
		int[] union = new int[ar1.length + ar2.length];
		int i = 0, j = 0;
		for (int k = 0; k < union.length; k++) {


			if (i < ar1.length && j < ar2.length) {
				/*Попарно сравнить элементы из двух массивов. В объединенный массив записать меньший элемент.
				  Перейти к следующему элементу в массиве из которого была выолнена запись
				*/
				if (ar1[i] > ar2[j]) {
					union[k] = ar2[j];
					j++;
				} else {
					if (ar1[i] == ar2[j]) {
						union[k] = ar1[i];
						union[k + 1] = union[k];
						k++;
						i++;
						j++;
					} else {
						union[k] = ar1[i];
						i++;
					}
				}
			} else {
				/* Один из массивов закончился.*/
				if (i == ar1.length) {
					union[k] = ar2[j];
					j++;
				} else {
					if (j == ar2.length) {
						union[k] = ar1[i];
						i++;
					}
				}
			}
		}
		return union;
	}
}