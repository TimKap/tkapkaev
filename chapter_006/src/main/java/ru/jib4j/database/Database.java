package ru.jib4j.database;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamConstants;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Class Database Описывает пример работы с базой данных с использованием jdbc.
 *
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 12.09.2017
 */
public class Database {
    /**
     * имя базы данных.
     */
    private String database;
    /**
     * имя пользователя.
     */
    private String username;
    /**
     * пароль.
     */
    private String password;
    /**
     * количество записей.
     */
    private int recordsNumber;

    /**
     * Возвращает имя базы данных.
     *
     * @return имя базы данных
     */
    public String getDatabase() {
        return database;
    }


    /**
     * Задает имя базы данных.
     *
     * @param database -  имя базы данных
     */
    public void setDatabase(String database) {
        this.database = database;
    }

    /**
     * Возвращает имя пользователя.
     *
     * @return имя пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Задает имя пользователя.
     *
     * @param username - имя пользователя
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * Задает пароль.
     *
     * @param password - пароль
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Возвращает количество записей таблицы.
     *
     * @return количество записей таблицы
     */
    public int getRecordsNumber() {
        return recordsNumber;
    }

    /**
     * Задает количество записей в таблице.
     *
     * @param recordsNumber - количество записей
     */
    public void setRecordsNumber(int recordsNumber) {
        this.recordsNumber = recordsNumber;
    }

    /**
     * Подключение к базе данных.
     *
     * @return соединение (null в случае неудачного подключения).
     */
    private Connection tryConnect() {

        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + database;
            connection = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;

    }

    /**
     * Возвращает данные из базы данных.
     *
     * @return массив чисел  (null, если соединение с БД не было установлено)
     */
    public int[] getNumbersFromDatabase() {

//        /* установить соединение с БД */

        int[] numbers = null;
        SQLException myException = null;
        try (Connection con = tryConnect()) {
            if (con != null) {
                numbers = new int[recordsNumber];
                try (Statement statement = con.createStatement();
                     PreparedStatement prStatement = con.prepareStatement("INSERT INTO TEST (FIELD) VALUES(?);")
                ) {
                    /* подготовка таблицы TEST*/
                    statement.execute("CREATE TABLE IF NOT EXISTS TEST (FIELD integer primary key);");
                    statement.execute("DELETE FROM TEST;");
                    con.setAutoCommit(false);

                    /* заполнение таблицы TEST*/
                    for (int i = 0; i < recordsNumber; i++) {
                        prStatement.setInt(1, i + 1);
                        prStatement.addBatch();
                    }
                    prStatement.executeBatch();
                    con.commit();
                    try (ResultSet result = statement.executeQuery("SELECT FIELD FROM TEST;")) {
                    /* извлечение данных из TEST */
                        int i = 0;
                        while (result.next()) {
                            numbers[i++] = result.getInt("FIELD");
                        }
                        con.commit();
                    }
                } catch (SQLException e) {
                    myException = e;
                    try {
                        con.rollback();
                    } catch (SQLException m) {
                        myException.addSuppressed(m);
                    }
                } finally {
                    if (myException != null) {
                        try {
                            con.setAutoCommit(true);
                        } catch (SQLException e) {
                            myException.addSuppressed(e);
                        }
                        throw myException;
                    } else {
                        con.setAutoCommit(true);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numbers;
    }

    /**
     * Создает XML файл с данными из базы данных.
     * @param numbers - числа из баззы данных
     * @param xmlName - имя содаваемого XML файлы
     * @return XML файл
     * */
    public File createXML(int[] numbers, String xmlName) {
        File file = new File(xmlName);
        XMLStreamWriter writer = null;
        try {
            /*Запись XML документа средствами StAX анализатора */
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            writer = factory.createXMLStreamWriter(new FileOutputStream(file));
            /*<?xml version="1.0" ?>*/
            writer.writeStartDocument();
            /*<entries>*/
            writer.writeStartElement("entries");

            for (Integer number : numbers) {
                /*<entry>*/
                writer.writeStartElement("entry");
                /*<field>*/
                writer.writeStartElement("field");
                writer.writeCharacters(number.toString());
                /*</field>*/
                writer.writeEndElement();
                /*</entry>*/
                writer.writeEndElement();
            }
            /*</entries>*/
            writer.writeEndElement();
            writer.writeEndDocument();

        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }

        return file;
    }

    /**
     * Выполняет преобразование XML -> XML с помощью XSLT технологии.
     * @param inputXML - файл исходного XML документа
     * @param outputXMLName - имя сформированного XML документа, в соответствии с xsl шаблоном
     * @param xslName - имя файла шаблона
     * @return XML файл, сформированный в соответрсвии с шаблоном
     * */
    public File transformXML(File inputXML, String outputXMLName, String xslName) {
        File file = new File(outputXMLName);

        try {
            /* фабрика трансформаторов */
            TransformerFactory trFactory = TransformerFactory.newInstance();
            /* задать поток шаблона */
            Source stylesheet = new StreamSource(new File(xslName));
            /* сформировать трансформатор */
            Transformer transformer = trFactory.newTransformer(stylesheet);

            /* задать поток исходного документа*/
            Source xml = new StreamSource(inputXML);
            /* задать поток выходного документа */
            Result result = new StreamResult(file);
            /* формирование документа в соответствии с xsl шаблоном*/
            transformer.transform(xml, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * Извлечение чисел из XML документа.
     * @param xml - xml файл
     * @return массив чисел
     * */
    private int[]  parseXML(File xml) {
        int[] numbers = new int[recordsNumber];
        XMLStreamReader reader = null;
        try {
            XMLInputFactory xmlFactory = XMLInputFactory.newInstance();
            reader = xmlFactory.createXMLStreamReader(new FileInputStream(xml));
            int i = 0;
            while (reader.hasNext()) {
                if (reader.next() == XMLStreamConstants.START_ELEMENT) {
                    if (reader.getLocalName().equals("entry")) {
                        String tmp = reader.getAttributeValue(null, "field");
                        numbers[i++] = Integer.valueOf(tmp);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }

        }
        return numbers;
    }

    /**
     * Выполняет преобразования.
     * Запись чисел в БД -> чтение чисел из БД -> запись чисел в XML файл -> преобразование XML к нужному формату -> чтение чисел из преобразованного XML

     * @return массив чисел, записанных в базу данных
     * */
    public int[] workWithDataBase() {
        Database database = new Database();
        database.setDatabase(this.database);
        database.setUsername(username);
        database.setPassword(password);
        database.setRecordsNumber(recordsNumber);
        int[] numbers = database.getNumbersFromDatabase();
        File xml = database.createXML(numbers, "./1.xml");
        File transformedXML = database.transformXML(xml, "./2.xml", "./stylesheet.xsl");
        int[] result = database.parseXML(transformedXML);
        long sum = 0;
        for (Integer number:result) {
            sum += number;
        }
        System.out.println("Arithmetic sum: " + sum);
        return result;
    }
}
