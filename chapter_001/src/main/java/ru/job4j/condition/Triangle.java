package ru.job4j.condition;
/**
* Class Triangle геометрическая фигура треуголтьник.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 13.03.2017
*/
public class Triangle {
	/** Вершина треугольника А.*/
	private Point a;
	/** Вершина треугольника B.*/
	private Point b;
	/** Вершина треугольника C.*/
	private Point c;

	/** Конструктор - создает треугольник с вершинами А, B, C.
	* @param a вершина треугольника A
	* @param b вершина треугольника B
	* @param c вершина треугольника C
	*/

	Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	* Вычисление площади треугольника.
	* @return Площадь треугольника
	*/
	public double area() {
		double square;
		square = 0.5D * ((b.getX() - a.getX()) * (c.getY() - a.getY()) - (b.getY() - a.getY()) * (c.getX() - a.getX()));
		return square >= 0D ? square : -square;
	}
}