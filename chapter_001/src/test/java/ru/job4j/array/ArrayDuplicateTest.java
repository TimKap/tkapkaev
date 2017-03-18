package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Class ArrayDuplicateTest содержит тесты для класса ArrayDuplicate.
* @author  Timur Kapakev (timur.kap@yandex.ru)
* @version $Id$
* @since 18.03.2017
*/
public class ArrayDuplicateTest {
	/**
	* Тест для удаления повторяющихся элементов массива.
	*/
	@Test
	public void whenRemoveDublicateElementsFromMassiveThenReturnArrayWithoutDublicatedElements() {
		ArrayDuplicate anyElements = new ArrayDuplicate();
		String[] result = anyElements.remove(new String[] {"Привет", "Мир", "Привет", "Супер", "Мир"});
		String[] expected = new String[] {"Привет", "Мир", "Супер"};
		assertThat(result, is(expected));
	}
}