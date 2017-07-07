package ru.job4j.generic;

/**
 * Interface хранилища элементов.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 07.07.2017
 * @param <E> - тип объекта помещаемого в хранилище
 */
public interface Store<E extends Base> {
    /**
     * Добавление элемента.
     * @param o - добавляемый объект
     * */
    void add(E o);

    /**
     * Заменяет один объект другим.
     * @param id - ID заменяемого объекта
     * @param src - заменяющий объект
     * @return заменяемый объект
     */
    boolean update(String id, E src);

    /**
     * Удаляет объект из хранилища.
     * @param o - удаляемый объект
     * @return удаленный объект
     * */
    boolean remove(E o);


}
