package ru.job4j.testtask.model.entities;

/**
 * Class MusicStyle описывает стиль музыки.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 13.12.2017
 * */
public class MusicStyle {

    /** ID. */
    private int id;
    /** музыкальный стиль.*/
    private  String style;

    /**
     * Конструктор класса MusicStyle.
     * @param id - ID стиля
     * @param style - музыкальный стиль
     */
    public MusicStyle(int id, String style) {
        this.id = id;
        this.style = style;
    }

    /**
     * Конструктор класса MusicStyle.
     * @param style - музыкальный стиль
     */
    public MusicStyle(String style) {
        this(0, style);
    }

    /**
     * Конструктор класса MusicStyle.
     */
    private MusicStyle() {
        this(0, null);

    }
    /**
     * Задает ID стиля.
     * @param id -  id стиля
     * */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Возвращает id.
     * @return id.
     * */
    public int getId() {
        return id;
    }

    /**
     * Задает стиль музыки.
     * @param style - музыкальный стиль
     * */
    public void setStyle(String style) {
        this.style = style;
    }
    /**
     * Возвращает музыкальный стиль.
     * @return музыкальный стиль
     */
    public String getStyle() {
        return style;
    }

    /**
     * Определяет равенство объектов.
     * @param o - сравниваемый объект
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MusicStyle musicStyle = (MusicStyle) o;

        return musicStyle.id == id;
    }

    /**
     * Задает хеш-функцию.
     * @return хеш-код
     * */
    @Override
    public int hashCode() {
        return id;
    }
}
