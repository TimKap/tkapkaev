package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class FactorialTest содержит тесты для класса Factorial.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 14.03.2017
*/
public class FactorialTest {
	/**
	* Тест вычисления факториала для положительного неотрицательного числа.
	*/
	@Test
	public void whenCalcFiveThenOneHundredAndTwenty() {
		Factorial factorial = new Factorial();
		int result = factorial.calc(5);
		int expected = 120;
		assertThat(result, is(expected));
	}
	/**
	* Тест вычисления факториала для 0.
	*/
	@Test
	public void whenCalcZeroThenOne() {
		Factorial factorial = new Factorial();
		int result = factorial.calc(0);
		int expected = 1;
		assertThat(result, is(expected));
	}

}