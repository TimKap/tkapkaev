package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Класс содержимт тесты для класса Max.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 13.03.2017
*/
public class MaxTest {
	/**
	* Тест поиска максимального значения из двух возможных.
	*/
	@Test
	public void whenMaxOneAndMinusOneThenOne() {
		Max operation = new Max();
		int result = operation.max(1, -1);
		int expected = 1;
		assertThat(result, is(expected));
	}
}