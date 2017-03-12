package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Класс с тестами для простого арифметического калькулятора.
* @author Timur Kpakaev (timur.kap@yandex.ru)
* @version $Id$
* @since 12.03.2017
*/
public class CalculatorTest {
	/**
	* Тест операции сложения, выполняемой калькулятором.
	*/
	@Test
	public void whenAddOnePlussOneThenTwo() {
		Calculator calc = new Calculator();
		calc.add(1D, 1D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}
	/**
	* Тест операции вычитания, выполняемой калькулятором.
	*/
	@Test
	public void whenSubstructOneMinusTwoThenMinusOne() {
		Calculator calc = new Calculator();
		calc.substruct(1D, 2D);
		double result = calc.getResult();
		double expected = -1D;
		assertThat(result, is(expected));

	}
	/**
	* Тест операции деления, выполняемой калькулятром.
	*/
	@Test
	public void whenDivOneDivisinByZeroThenInfinity() {
		Calculator calc = new Calculator();
		calc.div(1D, 0D);
		double result = calc.getResult();
		double expected = Double.POSITIVE_INFINITY;
		assertThat(result, is(expected));
	}
		/**
	* Тест операции деления, выполняемой калькулятром.
	*/
	@Test
	public void whenMultipleOneMultiplyTwoThenTwo() {
		Calculator calc = new Calculator();
		calc.multiple(1D, 2D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}
}