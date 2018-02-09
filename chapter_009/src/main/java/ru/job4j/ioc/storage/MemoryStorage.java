package ru.job4j.ioc.storage;


import ru.job4j.ioc.model.PersistentClass;

import java.util.Map;

/**
 * Class MemoryStorage описывает хранилище в энергозависимой памяти.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 08.02.2018
 * @param <E> - тип хранимых элементов
 * */
public class MemoryStorage<E extends PersistentClass> implements IStorage<E> {
    /** id generator. */
    private long idGenerator = 0;
    /** хранилище. */
    private final Map<Long, E> memory;

    /**
     * Конструктор класса MemoryStore.
     * @param memory - реализация памяти.
     * */
    public MemoryStorage(Map<Long, E> memory) {
        this.memory = memory;
    }
    /**
     * Сохраняет элемент.
     * @param element - сохраняемый элемент
     * @return сохраненный элемент (null, если не удалось выполнить сохранение)
     * */
    @Override
    public E persist(E element) {
        element.setId(++idGenerator);
        return memory.put(element.getId(), element);
    }
    /**
     * Возвращает элемент из хранилища.
     * @param id - id возвращаемого элемента
     * @return элемент хранилища (null, если элемента не существует)
     * */
    @Override
    public E get(long id) {
        return memory.get(id);
    }
    /**
     * Удаляет элемент из хранилища.
     * @param element - удаляемый элемент
     * @return удаленный элемент (null, при неудачном удалении)
     * */
    @Override
    public E remove(E element) {
       return memory.remove(element.getId());
    }
    /**
     * Обновляет элемент.
     * @param element - обновленный элемент
     * @return обновленный элемент (null, при неудачном обновлении)
     * */
    @Override
    public E update(E element) {
       return memory.put(element.getId(), element);
    }
}
