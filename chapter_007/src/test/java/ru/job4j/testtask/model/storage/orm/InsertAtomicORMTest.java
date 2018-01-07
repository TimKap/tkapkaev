package ru.job4j.testtask.model.storage.orm;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.apache.commons.dbcp.BasicDataSource;

import ru.job4j.testtask.model.entities.User;
import ru.job4j.testtask.model.storage.dao.implementations.TestDataBasePoolConnection;

import java.io.IOException;
import java.sql.Connection;

import java.sql.Statement;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;

/**
 * Class InsetAtomicORMTest содержит тесты для класса InsertAtomicORM.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 02.01.2017
 * */
public class InsertAtomicORMTest {
    /**
     * Пул соединений с тестируемой базой данных.
     * */
    private final BasicDataSource connectionPool;

    {
        BasicDataSource pool = null;
        try {
            pool = new TestDataBasePoolConnection().connectionPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connectionPool = pool;
    }



    /**
     * Тест для insert и get пустого объекта с атомарными полями.
     * @throws Exception -
     * */
    @Test
    public void whenInsertEmptyObjectThenGetEmptyObject() throws Exception {
        /*prepared database*/
        Connection con = connectionPool.getConnection();
        Statement st = con.createStatement();
        st.execute("DELETE FROM users;");


        /*execute test*/
        ArrayList<User> users = new ArrayList<>();
        User tom = new User.UserBuilder().build();
        users.add(tom);
        InsertAtomicORM insertAtomicORM = new InsertAtomicORM(con);
        insertAtomicORM.execute(users, User.class);

        GetAtomicORM getAtomicORM = new GetAtomicORM(con);
        HashMap<String, Set> idMatches = new HashMap<>();
        Set<Integer> idSet = new HashSet<>();
        idSet.add(tom.getId());
        idMatches.put("id", idSet);
        Collection<User> result = getAtomicORM.execute(idMatches, User.class);

        /*check test*/
        User tomResult = null;
        for (User user : result) {
            if (user.getId() == tom.getId()) {
                tomResult = user;
            }
        }

        /*check result*/
        assertThat(result.size(), is(1));
        assertThat(tom.getId(), is(tomResult.getId()));
        assertThat(tom.getName(), is(tomResult.getName()));
        assertThat(tom.getSurname(), is(tomResult.getSurname()));
    }
    /**
     * Тест для insert and get.
     * Добавление непустого объекта.
     * @throws Exception -
     * */
    @Test
    public void whenInsertElementThenGetSameElement() throws Exception {
        /*prepared database*/
        Connection con = connectionPool.getConnection();
        Statement st = con.createStatement();
        st.execute("DELETE FROM users;");


        /*execute test*/
        ArrayList<User> users = new ArrayList<>();
        User tom = new User.UserBuilder().addName("Tom").addSurname("Garison").build();
        users.add(tom);
        InsertAtomicORM insertAtomicORM = new InsertAtomicORM(con);
        insertAtomicORM.execute(users, User.class);

        /*check test*/
        GetAtomicORM getAtomicORM = new GetAtomicORM(con);
        HashMap<String, Set> idMatches = new HashMap<>();
        Set<Integer> idSet = new HashSet<>();
        idSet.add(tom.getId());
        idMatches.put("id", idSet);
        Collection<User> result = getAtomicORM.execute(idMatches, User.class);

        User tomResult = null;
        for (User user : result) {
            if (user.getId() == tom.getId()) {
                tomResult = user;
            }
        }

        assertThat(result.size(), is(1));
        assertThat(tom.getId(), is(tomResult.getId()));
        assertThat(tom.getName(), is(tomResult.getName()));
        assertThat(tom.getSurname(), is(tomResult.getSurname()));
    }



    /**
     * Тест для update and get.
     * Обновление коллекции объектов.
     * @throws Exception -
     * */
    @Test
    public void whenInsertCollectionElementsThenGetSameCollectionElement() throws Exception {
        /*prepared database*/
        Connection con = connectionPool.getConnection();
        Statement st = con.createStatement();
        st.execute("DELETE FROM users;");



        ArrayList<User> users = new ArrayList<>();
        User tom = new User.UserBuilder().addName("Tom").addSurname("Garison").build();
        User ann = new User.UserBuilder().addName("Ann").addSurname("Anderson").build();
        users.add(tom);
        users.add(ann);
        InsertAtomicORM insertAtomicORM = new InsertAtomicORM(con);
        insertAtomicORM.execute(users, User.class);

        /*execute test*/


        /*check test*/
        GetAtomicORM getAtomicORM = new GetAtomicORM(con);
        HashMap<String, Set> idMatches = new HashMap<>();
        Set<Integer> idSet = new HashSet<>();
        idSet.add(tom.getId());
        idSet.add(ann.getId());
        idMatches.put("id", idSet);
        Collection<User> result = getAtomicORM.execute(idMatches, User.class);


        User tomResult = null;
        User annResult = null;
        for (User user : result) {
            if (user.getId() == tom.getId()) {
                tomResult = user;
            } else {
                if (user.getId() == ann.getId()) {
                    annResult = ann;
                }
            }
        }

        /*check result*/
        assertThat(result.size(), is(2));
        assertThat(tom.getId(), is(tomResult.getId()));
        assertThat(tom.getName(), is(tomResult.getName()));
        assertThat(tom.getSurname(), is(tomResult.getSurname()));

        assertThat(ann.getId(), is(annResult.getId()));
        assertThat(ann.getName(), is(annResult.getName()));
        assertThat(ann.getSurname(), is(annResult.getSurname()));
    }
}