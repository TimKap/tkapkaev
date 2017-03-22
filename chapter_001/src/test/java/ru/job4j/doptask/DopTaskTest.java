package ru.job4j.doptask;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Class DopTaskTest содержит тесты для класса DopTask.
* @author  Timur Kapakev (timur.kap@yandex.ru)
* @version $Id$
* @since 22.03.2017
*/
public class DopTaskTest {
	/**
	* Тест для объединения отсортированных массивов.
	*/
	@Test
	public void whenConcatedTwoArraysThenGetAscendSortedArray() {
		DopTask entity = new DopTask();
		int[] result = entity.concat(new int[] {2, 4}, new int[] {1, 3, 5});
		int[] expected = {1, 2, 3, 4, 5};
		assertThat(result, is(expected));
	}
}