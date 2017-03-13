package ru.job4j.max;

/**
*Class Max. Поиск максимального значения.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 13.03.2017
*/

public class Max {
	/**
	* Возвращает максимальное значение из двух возможных.
	* @param first 1-е значение
	* @param second 2-е значение
	* @return максимальное значение
	*/
	public int max(int first, int second) {
		return (first >= second) ? first : second;
	}
}