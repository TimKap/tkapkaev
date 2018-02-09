package ru.job4j.ioc.model;
/**
 * Class PersistentClass описывает сохраняемый класс.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 08.02.2018
 * */
public abstract class PersistentClass {
    /** id модели.*/
    private long id = 0;

    /***/
    /**
     * Задает значение id.
     * @param id - id
     * */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * Возвращает значение id.
     * @return id
     * */
    public long getId() {
        return id;
    }
}
