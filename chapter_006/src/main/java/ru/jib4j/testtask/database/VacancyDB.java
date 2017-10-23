package ru.jib4j.testtask.database;

import ru.jib4j.testtask.vacancy.Vacancy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.ResultSet;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;
/**
 * Class VacancyDB описывает работу с базой данных вакансий.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 16.10.2017
 */
public class VacancyDB {
    /** url базы данных. */
    private String url;
    /** имя пользователя. */
    private String user;
    /** пароль. */
    private String password;
    /** соединение с базой данных. */
    private Connection connection;
    /** имя таблицы с вакансиями. */
    private String vacancyTable;
    /** имя буферной таблицы с вакансиями. */
    private String bufVacancyTable;

    /**
     * Конструктор класса VacancyDB.
     * @param url      - url базы данных
     * @param user     - имя пользователя
     * @param password - пароль к базе данных
     */
    public VacancyDB(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    /**
     * Устанавка соединения с базлй данных.
     * @return true, если соединение установлено
     * @throws SQLException - ошибка при соединении с базой данных
     */
    public boolean tryConnect() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        connection = conn;
        return conn != null;
    }

    /**
     * Закрытие соединения с базой данных.
     *
     * @throws SQLException - ошибка в процессе закрыти соединения с БД
     */
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Формирование талицы вакансий и ьуферной таблицы.
     * @param vacancyTable - имя таблицы
     * @throws SQLException - ошибка при создании тадлицы.
     */
    public final void createTables(String vacancyTable) throws SQLException {
        this.vacancyTable = vacancyTable;
        this.bufVacancyTable = vacancyTable + "_buf";
        try (Statement st = connection.createStatement()) {
            st.execute(String.format("CREATE TABLE IF NOT EXISTS %s (title varchar(200), author varchar(200), date timestamp, PRIMARY KEY(title, author));", vacancyTable));
            st.execute(String.format("CREATE TABLE IF NOT EXISTS %s (title varchar(200), author varchar(200), date timestamp, PRIMARY KEY(title, author));", bufVacancyTable));
        }
    }


    /**
     * Добавляет ваканси в буфер базы данных.
     * Если вакансия уже существует, то изменяет дату обновления
     * @param vacancies - список вакансий
     * @throws SQLException - ошибка при размещении записей в базе данных
     */
    public void insertToBufer(List<Vacancy> vacancies) throws SQLException {
        Throwable localThrowable = null;
        try {
            try (PreparedStatement ps = connection.prepareStatement(String.format("INSERT  INTO %s VALUES (?, ?, ?) ON CONFLICT DO NOTHING ;", bufVacancyTable))) {
                connection.setAutoCommit(false);
                for (Vacancy vacancy : vacancies) {
                    ps.setString(1, vacancy.getTitle());
                    ps.setString(2, vacancy.getAuthor());
                    ps.setTimestamp(3, new Timestamp(vacancy.getDate().getTime()));
                    ps.addBatch();
                }
                ps.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (Throwable r) {
                    e.addSuppressed(r);
                }
                throw e;
            }
        } catch (Throwable e) {
            localThrowable = e;
            throw e;
        } finally {
            if (localThrowable != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (Throwable e) {
                    localThrowable.addSuppressed(e);
                }
            } else {
                connection.setAutoCommit(true);
            }
        }

    }

    /***
     * Перенос записей из буфера в основную таблицу.
     * @throws SQLException - ошибка переноса записей из буфера в основную таблицу
     */
    public void updateMainTableFromBufer() throws SQLException {

        Throwable localThrowable = null;
        try {
            try (Statement st = connection.createStatement()) {
                connection.setAutoCommit(false);
                st.execute(String.format("INSERT INTO %s (SELECT * FROM %s) ON CONFLICT (title, author) DO UPDATE SET date = EXCLUDED.date", vacancyTable, bufVacancyTable));
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (Throwable r) {
                    e.addSuppressed(r);
                }
                throw e;
            }
        } catch (Throwable e) {
            localThrowable = e;
            throw e;
        } finally {
            if (localThrowable != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (Throwable e) {
                    localThrowable.addSuppressed(e);
                }
            } else {
                connection.setAutoCommit(true);
            }

        }

    }

    /**
     * Очищает буфер таблицы вакансий.
     * @throws SQLException - ошибка при очистке таблицы
     */
    public void clearBufer() throws SQLException {
        try (Statement st = connection.createStatement()) {
            st.execute(String.format("DELETE FROM %s;", bufVacancyTable));
        }
    }
    /**
     * Очищает основную таблицу вакансий.
     * @throws SQLException - ошибка при очистке таблицы
     */
    public void clearMain() throws SQLException {
        try (Statement st = connection.createStatement()) {
            st.execute(String.format("DELETE FROM %s;", vacancyTable));
        }
    }

    /**
     * Возращает вакансии из основной таблицы.
     * @return вакансии из основной таблицы
     * @throws SQLException - ощибка при извлечении вакансий
     */
    public List<Vacancy> vacanciesFromMainTable() throws SQLException {
        return getVacancies(vacancyTable);
    }
    /**
     * Возращает вакансии из буферной таблицы.
     * @return вакансии из обуферной таблицы
     * @throws SQLException - ощибка при извлечении вакансий
     */
    public List<Vacancy> vacanciesFromBuferTable() throws SQLException {
        return getVacancies(bufVacancyTable);
    }


    /**
     * Возвращает вакансии из заданной таблицы.
     * @param table - имя таблицы.
     * @return вакансии
     * @throws SQLException - ощибка SQL запроса
     */
    private List<Vacancy> getVacancies(String table) throws SQLException {
        ArrayList<Vacancy> vacancies = new ArrayList<>();
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s", table))) {
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                Date date = new Date(rs.getTimestamp("date").getTime());
                vacancies.add(new Vacancy(title, author, date));
            }

        }
        return vacancies;
    }
}
