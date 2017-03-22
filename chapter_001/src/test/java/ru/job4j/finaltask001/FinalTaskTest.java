package ru.job4j.finaltask001;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Class FinalTaskTest содержит тесты для класса FinalTask.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 21.03.2017
*/
public class FinalTaskTest {
	/**
	* Тест для поиска подстроки в строке.
	*/
	@Test
	public void whenContainsStrHasSubStrThenTrue() {
		String str = "1235";
		String subStr = "23";
		FinalTask entity = new FinalTask();
		boolean result = entity.contains(str, subStr);
		assertThat(result, is(true));
	}
}
