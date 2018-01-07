package ru.job4j.testtask.model.storage.transaction;


import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class Transaction описывает транзакцию.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 22.12.2017
 * @param <E> - тип данных, возвращаемых транззакцией.
 * */
public abstract class Transaction<E> {

    /** соединение с базой данных. */
    private final Connection connection;
    /** уровень изоляции транзакции.*/
    private Integer isolationLvl = null;
    /**
     * Конструктор класса Transaction.
     * @param connection - соединение с базой данных.
     * */
    public Transaction(Connection connection) {
       this.connection = connection;
    }

    /**
     * Возврат соединеия с базой данных для транзакции.
     * @return соединение с базой данных.
     * */
    public Connection getTransactionConnection() {
        return connection;
    }

    /**
     * Задает уровень изоляции транзакции.
     * @param isolationLvl - уровень изоляции транзакции.
     * */
    public void setIsolationLevel(int isolationLvl) {
        this.isolationLvl = isolationLvl;

    }
    /**
     * Тело транзакции.
     * @return результат выполнения тела транзакции (null, если транзакция не возвращает данных
     * или результат невозможно получить)
     * @throws SQLException - ошибка выполнения тела транзакции.
     * */
    public abstract E runStatements() throws SQLException;

    /**
     * Выполнение транзакции.
     * @return результат, вычисленный транзакцией (null, если последовательность не возвращает данных
     * или результат невозможно получить)
     * @throws SQLException - ошибка выполнения транзакции
     * */
    public E execute() throws SQLException {
        Throwable localThrow = null;
       try {

           connection.setAutoCommit(false);
           if (isolationLvl != null) {
               connection.setTransactionIsolation(isolationLvl);
           }
           return runStatements();
       } catch (Throwable e) {
           localThrow = e;
           try {
               if (connection != null) {
                   connection.rollback();
               }
           } catch (Throwable r) {
               localThrow.addSuppressed(r);
           }
           throw e;
       } finally {
           if (connection != null) {
               try {
                   connection.setAutoCommit(true);
               } catch (Throwable r) {
                   if (localThrow != null) {
                       localThrow.addSuppressed(r);
                   } else {
                       throw r;
                   }
               }
           }
       }
    }

}
