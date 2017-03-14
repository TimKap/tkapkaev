package ru.job4j.loop;
/**
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 14.03.2017
*/
public class Paint {
	/**
	* Формирование псевдографического изображения пирамиды.
	* @param h высота пирамиды
	* @return рисунок пирамиды высотой h
	*/
	public String piramid(int h) {

		/* Размер основания пирамиды */
		int fund = 2 * h - 1;
		/* Рисунок пирамиды */
		StringBuilder piram = new StringBuilder();

		for (int i = 0; i < h; i++) {
			/*Формирование строки рисунка*/
			for (int j = 0; j < fund; j++) {
				/* Количество символов '^' в строке */
				int symbolNumber = 2 * i + 1;

				/* Позиция первго символа '^' в строке */
				int startSymbPos = (fund - symbolNumber) / 2;

				/* Позиция крайнего символа '^' в строке*/
				int endSymbPos = (fund + symbolNumber) / 2 - 1;

				/* Заполнение строки символами*/
				if ((startSymbPos <= j) && (j <= endSymbPos)) {
					piram.append('^');
				} else {
					piram.append(' ');
					}
			}
		    /* Перенос на новую строку и установка каретки в начало строки*/
			piram.append("\n\r");
		}
		return piram.toString();
	}
}