package ru.job4j.condition;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
* Class TriangleTest содержит тесты для методов класса Triangle.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 13.03.2017
*/
public class TriangleTest {
	/** Тест для расчета площади треугольника.*/
	@Test
	public void whenAreaSetThreePointsThenTriangleArea() {
		/* Создаем равнобедренный прямоугольный треугольник*/
		Triangle geom = new Triangle(new Point(0, 1), new Point(0, 0), new Point(1, 0));
		double result = geom.area();
		double expected = 0.5D * 1D * 1D;
		assertThat(result, closeTo(expected, 0.1));
	}
}