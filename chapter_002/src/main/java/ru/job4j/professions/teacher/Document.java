package ru.job4j.professions.teacher;

/**
* Class Student документ.
* @author Timur Kapkaev (timur.kap@yandex.ru)
* @version $Id$
* @since 29.03.2017
*/
public class Document {
	/** уникальный номер документа.*/
	private int id;
	/** оценка документа.*/
	private int mark;

	/**
	* Конструктор класса Document.
    */
	public Document() {
		this.id = (int) Math.round(10000D * Math.random());
	}

	/**
	* Возвращает id документа.
	* @return id
	*/
	public int getId() {
		return id;
	}

	/**
	* Возвращает оценку документа.
	* @return оценка
	*/
	public int getMark() {
		return mark;
	}

	/**
	* Установка оценки документу.
	* @param mark - оценка.
	*/
	public void setMark(int mark) {
		this.mark = mark;
	}

}