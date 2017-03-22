package ru.job4j.finaltask001;
/**
* Class FinalTask содержит заключительное задание к модулю 001.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 21.03.2017
*/
public class FinalTask {
	/**
	* Проверка содержания подстроки в строке.
	* @param origin исходная строка
	* @param sub проверяемая подстрока
	* @return результат проверки содержания подстроки в строке
	*/
	public boolean contains(String origin, String sub) {

		char[] originSymbols = origin.toCharArray();
		char[] subSymbols = sub.toCharArray();
		/* Проверка ввода пустой строки */
		if ((subSymbols.length == 0) || (originSymbols.length == 0)) {
			return false;
		}
		/* Проверка правильности ввода строки */
		if (subSymbols.length > originSymbols.length) {
			return false;
		}
		for (int i = 0; i <= (originSymbols.length - subSymbols.length); i++) {
			for (int j = 0; j < subSymbols.length; j++) {
				if (originSymbols[i + j] != subSymbols[j]) {
					break;
				}
				if (j == subSymbols.length - 1) {
					return true;
				}
			}
		}
		return false;
	}
}