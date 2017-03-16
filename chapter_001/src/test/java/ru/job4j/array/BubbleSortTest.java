package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Class BubbleSortTest содержит тесты для класс BubbleSort.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 15.03.2017
*/
public class BubbleSortTest {
	/**
	*Тест для сортировки массива по возрастанию методом "пузырька".
	*/
	@Test
	public void whenSortArrayThenAscendSortedArray() {
		BubbleSort anyElements = new BubbleSort();
		int[] expected = {1, 2, 3, 5, 7};
		int[] result = anyElements.sort(new int[] {5, 1, 2, 7, 3});

		assertThat(result, is(expected));
	}

}