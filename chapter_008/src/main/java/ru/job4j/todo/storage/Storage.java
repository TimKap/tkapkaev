package ru.job4j.todo.storage;

/**
 * Class Storage описывает хранилище данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 23.01.2018
 * */
public class Storage {
    /** рвспомогательный объект работы с hibernate. */
    private final HibernateUtil hibernateUtil = new HibernateUtil();
    /**
     * Открывает хранилище.
     * */
    public void open() {
        hibernateUtil.beginTransaction();
    }

    /**
     * Возвращает экземпляр ItemDAO объекта.
     * @return  item из хранилища
     *
     * */
    public ItemDAO getItemDAO() {
        if (hibernateUtil.isTransactionOpen()) {
            return new ItemDAO(hibernateUtil.getSession());
        }
        return null;
    }
    /**
     * Потдтверждает операции.
     * */
    public void submit() {
        hibernateUtil.commitTransaction();
        hibernateUtil.closeSession();
    }
    /**
     * Прекращает работу с хранилищем.
     * */
    public void close() {
        hibernateUtil.closeSessionFactory();
    }

}
