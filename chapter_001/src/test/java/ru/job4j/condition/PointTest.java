package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class PointTest содержит тесты для методов класса Point.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 13.03.2017
*/
public class PointTest {
	/**
	* Тест для определения принадлежности точки прямой.
	*/
	@Test
	public void whenX1Y2ThenPointIsPlacedOnLineA1B1() {
		Point point = new Point(1, 2);
		boolean result = point.is(1, 1);
		boolean expected = true;
		assertThat(result, is(expected));
	}
}