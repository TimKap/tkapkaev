package ru.jib4j.testtask.vacancy;

/**
 * Интерфейс IHandler - обработчик данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 18.10.2017
 * @param <E> - тип данных, обрабатываемых фильтром.
 */
public interface IHandler<E> extends IFilter<E> {
     /**
      * Определяет возможность продолжения обработки данных.
      * @param inputData - входные данные
      * @return true, если дальнейшая обработка данных возможна
      * */
     boolean isContinue(Object inputData);
}
