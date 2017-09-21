package ru.job4j.models;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Class ItemDatabase описывает работу с базой данных заявок.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 17.08.2017
 */
public class ItemDatabase {
    /** имя базы данных. */
    private String database;
    /** имя пользователя. */
    private String user;
    /** пароль. */
    private String password;
    /**запрос на добавление заявки.*/
    private String addQuery;
    /** запрос на удаление заявки.*/
    private String deleteQuery;
    /** запрос на очистку таблицы.*/
    private String cleanQuery;
    /** запрос на вывод всех заявок.*/
    private String selectAllQuery;
    /** запрос на получение заявок по имени. */
    private String selectByNameQuery;
    /** запрос на получение заявко по id. */
    private String selectByIDQuery;
    /** обновление заявки. */
    private String updateQuery;

    /** соединение с базой данных. */
    private Connection dbConnection;
    /**
     * Конструктор класса ItemDatabase.
     */
    private ItemDatabase() {

    }

    /**
     * Создание нового объекта ItemDatabase.
     * @return объект ItemDatabase
     * @throws Exception при нарушении создания объекта ItemDatabase
     * */
    public static ItemDatabase newInstance() throws Exception {
        ItemDatabase database = new ItemDatabase();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("settings.xml"));
        database.setDBConnectionParameters(doc);

        doc = builder.parse("queries.xml");
        database.setQueries(doc);
        database.dbConnection = ItemDatabase.getConnection(database.database, database.user, database.password);

        Statement st = database.dbConnection.createStatement();
        String query = database.getElementValue(doc, "table");
        st.execute(query);
        st.close();
        return database;
    }

    /**
     * Возвращает значение тега из документа.
     * @param doc - xml документ (DOM представление)
     * @param element - имя элемнта документа
     * @return значение элемента
     * */
    private String getElementValue(Document doc, String element) {
        return doc.getElementsByTagName(element).item(0).getTextContent();
    }

    /**
     * Устанавливает параметры соединения с БД.
     * @param doc - xml окумент(DOM представление)
     * */
    private void setDBConnectionParameters(Document doc) {
        database = getElementValue(doc, "database");
        user = getElementValue(doc, "user");
        password = getElementValue(doc, "password");
    }
    /**
     * Устанавливает запросы для работы с БД.
     * @param doc - xml окумент(DOM представление)
     * */
    private void setQueries(Document doc) {
        addQuery = getElementValue(doc, "add");
        deleteQuery = getElementValue(doc, "delete");
        selectAllQuery = getElementValue(doc, "selectAll");
        selectByNameQuery = getElementValue(doc, "selectByName");
        selectByIDQuery = getElementValue(doc, "selectByID");
        updateQuery = getElementValue(doc, "update");
        cleanQuery = getElementValue(doc, "clean");
    }


    /**
     * Возвращает соединение с базой данных.
     * @param database - Имя БД
     * @param user - имя пользователя
     * @param password - пароль
     * @return соединение с БД)
     * @throws SQLException - исключение при установке связи с БД
     * */
    public static Connection getConnection(String database, String user, String password) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/" + database;
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Закрывает соединение с БД.
     * @throws SQLException - An exception that provides information on a database access
     * error or other errors;
     * */

    public void close() throws SQLException {
        if (dbConnection != null) {
            dbConnection.close();
        }
    }
    /**
     * Добавляет заявку в базу данных.
     * @param item - заявка
     * @throws SQLException - An exception that provides information on a database access
     * error or other errors;
     * */
    public void add(Item item) throws SQLException {
        try (PreparedStatement ps = dbConnection.prepareStatement(addQuery)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            StringBuilder comments = new StringBuilder();
            if (item.getComments() != null) {
                for (String row : item.getComments()) {
                    comments.append(row);
                }
            }
            ps.setString(3, comments.toString());
            ps.execute();
        }
    }

    /**
     * Удаляет заявку по ID.
     * @param item - заявка.
     * @throws SQLException - An exception that provides information on a database access
     * error or other errors
     * */
    public void delete(Item item) throws SQLException {
        int id = Integer.valueOf(item.getId());
        try (PreparedStatement ps = dbConnection.prepareStatement(deleteQuery)) {
            ps.setInt(1, id);
            ps.execute();
        }
    }

    /** Очищает таблицу.
     * @throws SQLException - An exception that provides information on a database access error or other errors
     * */
    public void clean() throws SQLException {

        try (Statement st = dbConnection.createStatement()) {
            st.execute(cleanQuery);
            st.execute("ALTER SEQUENCE items_id_seq RESTART WITH 1;");
        }
    }

    /**
     * Редактирование заявки.
     * @param item - заявка
     * @throws SQLException - An exception that provides information on a database access
     * error or other errors
     * */
    public void update(Item item) throws SQLException {
        int id = Integer.valueOf(item.getId());
        try (PreparedStatement ps = dbConnection.prepareStatement(updateQuery)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());

            StringBuilder comments = new StringBuilder();
            if (item.getComments() != null) {
                for (String row : item.getComments()) {
                    comments.append(row);
                }
            }
            ps.setString(3, comments.toString());
            ps.setInt(4, id);
            ps.execute();
        }
    }

    /**
     * Получение списка заявок.
     * @return список всех заявок
     * @throws SQLException - An exception that provides information on a database access
     * error or other errors
     * */
    public List<Item> findAll() throws SQLException {
        List<Item> items = new ArrayList<>();
        try (Statement st = dbConnection.createStatement();
             ResultSet rs = st.executeQuery(selectAllQuery)) {
            while (rs.next()) {
                Item item = createItem(rs);
                items.add(item);
            }
        }
        return items;
    }

    /**
     * Получение списка заявок с одинаковым именем.
     * @param name - имя заявки
     * @return список заявок с одинаковым именем.
     * @throws SQLException - An exception that provides information on a database access
     * error or other errors
     */
    public List<Item> findByName(String name) throws SQLException {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement ps = dbConnection.prepareStatement(selectByNameQuery)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Item item = createItem(rs);
                    items.add(item);
                }
            }
        }
        return items;
    }

    /**
     * Возвращение заявки по Id.
     * @param id - идентификационный номер
     * @return - заявка
     * @throws SQLException - An exception that provides information on a database access
     * error or other errors
     */
    public Item findByID(String id) throws SQLException {
        Item item = null;
        try (PreparedStatement ps = dbConnection.prepareStatement(selectByIDQuery)) {
            ps.setInt(1, Integer.valueOf(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    item = createItem(rs);
                }
            }
        }
        return item;
    }


    /**
     * Возвращает объект заявки из записи.
     * @param record - запись
     * @return заявка.
     * @throws SQLException - An exception that provides information on a database access
     * error or other errors
     * */
    private Item createItem(ResultSet record) throws SQLException {
        int id = record.getInt("id");
        String name = record.getString("name");
        String description = record.getString("description");
        Timestamp creteTime = record.getTimestamp("create_time");
        long time = creteTime.getTime();
        String comments = record.getString("comments");
        Item item = new Item(name, description, time);
        item.setId(Integer.toString(id));
        item.setComments(comments.split("\\n"));
        return item;
    }

}
