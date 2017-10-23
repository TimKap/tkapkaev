package ru.jib4j.testtask.vacancy;

/**
 * Интерфейс  IFilter предоставляет метод для фильтрации данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 18.10.2017
 * @param <E> - тип фильтруемых данных
 */
public interface IFilter<E> {
    /**
     * Фильтр входных данных.
     * @param  inputData - входные данные
     * @return отфильтрованные данные
     * */
    E filter(E inputData);
}
