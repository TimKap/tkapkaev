package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class CounterTest набор тестов для класса Counter.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 14.03.2017
*/
public class CounterTest {
	/**
	* Тест суммирования четныхъ чисел в пределах заданного диапазона.
	*/
	@Test
	public void whenCounterOneToTenThenThirty() {
		Counter cnt = new Counter();
		int result = cnt.add(1, 10);
		int expected = 30;
		assertThat(result, is(expected));
	}
}