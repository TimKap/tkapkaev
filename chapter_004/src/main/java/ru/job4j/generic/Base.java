package ru.job4j.generic;

/**
 * Class Base описывает элемент коллекции.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 07.07.2017
 */
public abstract class Base {
    /** ID объекта. */
    private String id;
    /**
     * Конструктор класса Base.
     * @param id - ID объекта
     * */
    public Base(String id) {
        this.id = id;
    }

    /**
     * Возвращает ID объекта.
     * @return ID объекта
     * */
    public String getId() {
        return id;
    }

    /**
     * Задает ID объекта.
     * @param id - ID полььзователя
     * */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Проверяет равенство объектов Base.
     * @p;aram
     *
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (this.getClass() != o.getClass())) {
           return false;
        }
        Base element = (Base) o;
        return  id != null ? id.equals(element.getId()) : element.getId() == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
