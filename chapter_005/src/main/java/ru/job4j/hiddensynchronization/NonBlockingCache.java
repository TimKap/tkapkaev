package ru.job4j.hiddensynchronization;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Class NonBlockingCache описывает неблокирующий кэш.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 29.08.2017
 */
public class NonBlockingCache {

    /** Контейнер кэша. */
    private ConcurrentHashMap<String, Model> container = new ConcurrentHashMap<>();

    /**
     * Добавление элемента в кэш.
     * @param model - добавляемая модель
     * @return добавленную модель и null, если вставка не удалась
     * */
    public Model add(Model model) {
       return  container.put(model.getId(), model);
    }

    /**
     * Удаляет элемент из кэш.
     * @param id - id  удаляемой модели
     * @return удаленную модель и null, усли удаление не выполнилось
     * */
    public Model delete(String id) {
       return container.remove(id);
    }

    /**
     * Обновляет модель в кэше.
     * @param newModel - обновленная модель
     * @throws OptimisticException - когда два потока одновременно модифицируют запись.
     * */
    public void update(Model newModel) throws OptimisticException {
        String id = newModel.getId();
        Model model = container.get(id);

        if (model != null) {
            AtomicInteger version = model.getVersion();
            int expectValue = version.get();
            if (version.compareAndSet(expectValue, expectValue + 1)) {
                newModel.setVersion(version);
                container.put(id, newModel);
            } else {
                throw new OptimisticException();
            }
        }
    }
    /**
     * Возвращает container.
     * @return container
     * */
    ConcurrentHashMap<String, Model> getContainer() {
        return container;
    }
}
