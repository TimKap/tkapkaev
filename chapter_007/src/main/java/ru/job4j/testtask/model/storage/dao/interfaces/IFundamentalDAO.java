package ru.job4j.testtask.model.storage.dao.interfaces;

import ru.job4j.testtask.model.storage.dao.exception.OperationException;

import java.util.List;

/**
 * Interface IFundamentalDAO описывает базовые операции DAO.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 13.112.2017
 * @param <E> - тип объектов, который представляет DAO
 * */
public interface IFundamentalDAO<E> {
    /**
     * Возвращает из хранилища список всех представлений элементов.
     * @return список
     * @throws OperationException - ошибка выполнения операции
     * */
    List<E> getAllElements() throws OperationException;

    /**
     * Возвращает представление элемента по ключу.
     * @param key -  ключ
     * @return элемент из хранилища.
     * @throws OperationException - ошибка выполнения операции
     * */
    E get(String key) throws OperationException;

    /**
     * Добавляет элемент в хранилище.
     * @param element - добавляемый в хранилище элемент
     * @return добавленный элемнт.
     * @throws OperationException - ошибка выполнения операции
     * */
    E add(E element) throws OperationException;

    /**
     * Удаляет элемент из хранилища.
     * @param element - удаляемый элемент
     * @return удаленный элемент
     * @throws OperationException - ошибка выполнения операции
     * */
    E remove(E element) throws OperationException;

    /**
     * Ыносит обновление в элемент хранилища.
     * @param modification - обновление
     * @return обновленный элемент
     * @throws OperationException - ошибка операции.
     * */
    E update(E modification) throws OperationException;
}
