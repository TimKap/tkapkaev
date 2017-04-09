package ru.job4j.menutracker;

/**
 * Interface UserAction - описывает действия в меню.
 */
public interface UserAction {
	/** Вернуть назначенный ключ пункту меню.
	 * @return ключ пункта меню
	 * */
	int key();
    /** Выполнить действие.*/
    void execute();
	/** Информация о пункте меню.
	 * @return информация о пункте меню
	 * */
	String info();
}
