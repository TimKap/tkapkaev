package ru.job4j.cartrade.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Class HibernateUtil описывает вспомогательный класс работы с Hibernate.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 20.01.2018
 * */
public class HibernateUtil {
    /** экземпляр SessionFactory. */
    private static final SessionFactory SESSION_FACTORY;
    /** объект сессии. */
    private static final ThreadLocal THREAD_SESSION;
    /** объект транзакции. */
    private static final ThreadLocal THREAD_TRANSACTION;

    static {
        Configuration configuration = new Configuration();
        configuration.configure("/advertisement.cfg.xml");
        SESSION_FACTORY = configuration.buildSessionFactory();
        THREAD_SESSION = new ThreadLocal();
        THREAD_TRANSACTION = new ThreadLocal();
    }
    /**
     * возвращает сессию.
     * @return hibernate сессия
     * */
    public Session getSession() {
        Session session = (Session) THREAD_SESSION.get();
        if (session == null) {
            session = SESSION_FACTORY.openSession();
            THREAD_SESSION.set(session);
        }
        return session;
    }

    /** активирует транзакцию. */
    public void beginTransaction() {
        Transaction transaction = (Transaction) THREAD_TRANSACTION.get();
        if (transaction == null) {
            transaction = getSession().beginTransaction();
            THREAD_TRANSACTION.set(transaction);
        }
    }
    /**
     * проверяет открытие транзакции.
     * @return true, если транзакция была активирована.
     * */
    public boolean isTransactionOpen() {
        Object transaction = THREAD_TRANSACTION.get();
        return transaction != null;
    }
    /** закрывает транзакцию. */
    public void commitTransaction() {
        Transaction transaction = (Transaction) THREAD_TRANSACTION.get();
        if (isTransactionOpen()) {
            transaction.commit();
            THREAD_TRANSACTION.set(null);
        }
    }
    /** закрывает сессию. */
    public void closeSession() {
        Session session = (Session) THREAD_SESSION.get();
        if (session != null) {
            session.close();
            THREAD_SESSION.set(null);
        }
    }
    /** закрывает Session Factory. */
    public void closeSessionFactory() {
        SESSION_FACTORY.close();
    }
}
