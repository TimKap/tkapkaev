package ru.job4j.todo.model;

import java.sql.Timestamp;

/**
 * Class Item описывает item.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 09.01.2017
 * */
public class Item {
    /** id. */
    private int id;
    /** описание. */
    private String description;
    /** дата создания. */
    private Timestamp created;
    /** состояние задаич.*/
    private boolean done;

    /**
     * возвращает id .
     * @return id
     * */
    public int getId() {
        return id;
    }

    /**
     * Задает id.
     * @param id - id
     * */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Возвращает описание.
     * @return описание
     * */
    public String getDescription() {
        return description;
    }

    /**
     * Задает описание.
     * @param description - описание
     * */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Возвращает время сосздания.
     * @return время создания
     * */
    public Timestamp getCreated() {
        return created;
    }

    /**
     * Задает дата создания.
     * @param created - дата создания
     * */
    public void setCreated(Timestamp created) {
        this.created = created;
    }

    /**
     * Возвращает состояние выполнения задания.
     * @return состояние выполнения задания
     * */
    public boolean getDone() {
        return done;
    }

    /**
     * Задает состояние выполнения задания.
     * @param done - состояние выполнения задания
     * */
    public void setDone(boolean done) {
        this.done = done;
    }
}
