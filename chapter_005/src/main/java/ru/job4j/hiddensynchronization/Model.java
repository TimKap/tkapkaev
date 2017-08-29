package ru.job4j.hiddensynchronization;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class Model описывает модель.
 * @author Timur Kapkaev (timur.kap@ayndex.ru)
 * @version $Id$
 * @since 29.08.2017
 */
public class Model {
    /** id. */
    private final String id;
    /** версия. */
    private AtomicInteger version = new AtomicInteger();

    /** имя пользователя. */
    private String userName;

    /**
     * Конструктор класса Model.
     * @param id - id модели
     * @param  userName - имя пользователя
     * */
    public Model(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    /**
     * Возвращает id.
     * @return id
     * */
    public String getId() {
        return id;
    }

    /**
     * Возвращает имя пользователя.
     * @return userName
     * */
    public String getUserName() {
        return userName;
    }
    /**
     * Возвращает версию модели.
     * @return version
     * */
    AtomicInteger getVersion() {
        return version;
    }

    /**
     * Обновляет версию, увеличивая старое значение на 1.
     * @param version - версия
     * */
    void setVersion(AtomicInteger version) {
        this.version = version;
    }


}
