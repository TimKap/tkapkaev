package ru.job4j.cartrade.storage.dao;

import java.util.List;

/**
 * Interface IGenericDAO описывает общие операции DAO.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 23.01.2018
 * @param <E> - тип сущности, с которым работает DAO.
 * */
public interface IGenericDAO<E> {
    /**
     * Возвращает сущность из хранилища по id.
     * @param id - id сущности
     * @return сущность
     * */
    E get(long id);
    /**
     * Возвращает все сущности из хранилища.
     * @return список всех сущностей, находящихся в хранилище
     * */
    List<E> getAll();
    /**
     * Добавляет сущность в хранилище.
     * @param entity - добаляемая сущность.
     * @return добавленная сущность
     * */
    E persist(E entity);
    /**
     * Обновляет сущность, находящуюся в хранилище.
     * @param modifiedEntity - обновленная сущность
     * @return обновленная сущность
     * */
    E update(E modifiedEntity);

    /**
     * Удаляет из хранилища заданную сущность.
     * @param entity - удаляемая сущность
     * @return удаленная сущность
     * */
    E remove(E entity);
}
