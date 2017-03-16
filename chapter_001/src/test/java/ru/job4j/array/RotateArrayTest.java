package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Class RotateArrayTest содержит тест для класса RotateArray.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 16.03.2017
*/
public class RotateArrayTest {
	/**
	* Тест для поворота массива на 90 градусов по ходу часовой стрелки.
	*/
	@Test
	public void wheRotateArrayThenGetRoatatedArrayCW90() {
		RotateArray anyElements = new RotateArray();
		int[][] expected = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
		int[][] result = anyElements.rotate(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
		assertThat(result, is(expected));
	}

}