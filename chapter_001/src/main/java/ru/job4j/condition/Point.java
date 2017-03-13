package ru.job4j.condition;
/**
* Class Point - материальная точка.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 13.03.2017
*/
public class Point {
	/** Координата x. */
	private int x;
	/** Координата y. */
	private int y;

	/**
	* Конструктор.
	* @param x координата точки x
	* @param y координата точки y
	*/
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	* @return возвращает координату точки x
	*/
	public int getX() {
		return this.x;
	}
    /**
	* @return возвращает координату точки y
	*/
	public int getY() {
		return this.y;
	}
	/**
	* Прроверка принадлежности точки указанной прямой.
	* @param a задает наклон прямой
	* @param b задает смещение прямой
	* @return принадлежность точки с координатами (x,y)заданной прямой
	*/
	public boolean is(int a, int b) {
		return (this.y == (this.x * a + b));
	}
}