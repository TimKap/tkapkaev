package ru.job4j.todo.storage;

/**
 * Class Storage описывает хранилище данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 23.01.2018
 * */
public class Storage {
    /** вспомогательный объект работы с hibernate. */
    private static final HibernateUtil HIBERNATE_UTIL = HibernateUtil.getInstance();

    /** объект хранилища. */
    private static final Storage INSTANCE = new Storage();

    /**
     * Конструктор класса Storage.
     * */
    private Storage() {

    }
    /**
     * Возвращает объект хранилища.
     * @return экземпляр хранилища.
     * */
    public static Storage getInstance() {
        return INSTANCE;
    }
    /**
     * Открывает хранилище.
     * */
    public void open() {
        HIBERNATE_UTIL.beginTransaction();
    }

    /**
     * Возвращает экземпляр ItemDAO объекта.
     * @return  item из хранилища
     *
     * */
    public ItemDAO getItemDAO() {
        if (HIBERNATE_UTIL.isTransactionOpen()) {
            return new ItemDAO(HIBERNATE_UTIL.getSession());
        }
        return null;
    }
    /**
     * Потдтверждает операции.
     * */
    public void submit() {
        HIBERNATE_UTIL.commitTransaction();
        HIBERNATE_UTIL.closeSession();
    }
    /**
     * Прекращает работу с хранилищем.
     * */
    public void close() {
        HIBERNATE_UTIL.closeSessionFactory();
    }

}
