package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Class TurnTest содержит тест для класса Turn.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 15.03.2017
*/
public class TurnTest {
	/**
	* Тест для реверсирования элементов массива с нечетным количеством элементов.
	*/
	@Test
	public void whenBackArrayOddThenGetReturnReversedArray() {
		Turn anyObjects = new Turn();
		int[] expected = {5, 4, 3, 2, 1};
		int[] result = anyObjects.back(new int[] {1, 2, 3, 4, 5});
		assertThat(result, is(expected));
	}
	/**
	* Тест для реверсирования элементов массива с четным количеством элементов.
	*/
	@Test
	public void whenBackArrayEvenThenGetReturnReversedArray() {
		Turn anyObjects = new Turn();
		int[] expected = {2, 6, 1, 4};
		int[] result = anyObjects.back(new int[] {4, 1, 6, 2});
		assertThat(result, is(expected));
	}
}