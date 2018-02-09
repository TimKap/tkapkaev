package ru.job4j.ioc.storage;

import ru.job4j.ioc.model.PersistentClass;

/**
 * Interface IStorage описывает хранилище данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 08.02.2018
 * @param <E> - тип хранимого элемента
 * */
public interface IStorage<E extends PersistentClass> {
    /**
     * Сохраняет элемент.
     * @param element - сохраняемый элемент
     * @return сохраненный элемент (null, если не удалось выполнить сохранение)
     * */
    E persist(E element);
    /**
     * Возвращает элемент из хранилища.
     * @param id - id возвращаемого элемента
     * @return элемент хранилища (null, если элемента не существует)
     * */
    E get(long id);
    /**
     * Удаляет элемент из хранилища.
     * @param element - удаляемый элемент
     * @return удаленный элемент (null, при неудачном удалении)
     * */
    E remove(E element);
    /**
     * Обновляет элемент.
     * @param element - обновленный элемент
     * @return обновленный элемент (null, при неудачном обновлении)
     * */
    E update(E element);

}
