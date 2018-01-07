package ru.job4j.testtask.model.storage.orm;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import ru.job4j.testtask.model.entities.MusicStyle;

import ru.job4j.testtask.model.entities.User;
import ru.job4j.testtask.model.storage.dao.implementations.TestDataBasePoolConnection;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class LinkORMTest содержит тесты для класса LinkORM.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 02.01.2017
 * */
public class LinkORMTest {

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
     * Тест для setElementComplexFieldValueLink.
     * @throws Exception -
     * */
    @Test
    public void whenSetElementComplexFieldLinkThenGetLinkOnComplexFieldValue() throws Exception {
        /*prepared database*/
        Connection con = connectionPool.getConnection();
        Statement st = con.createStatement();
        st.execute("DELETE FROM users;");
        st.execute("DELETE FROM musicstyles;");

        ArrayList<User> users = new ArrayList<>();
        User tom = new User.UserBuilder().addName("Tom").addSurname("Garison").build();
        users.add(tom);

        ArrayList<MusicStyle> musicStyles = new ArrayList<>();
        MusicStyle rock = new MusicStyle("ROCK");
        MusicStyle classic = new MusicStyle("CLASSIC");
        musicStyles.add(rock);
        musicStyles.add(classic);
        tom.getMusicPreferences().addAll(musicStyles);
        InsertAtomicORM insertAtomicORM = new InsertAtomicORM(con);
        insertAtomicORM.execute(users, User.class);
        insertAtomicORM.execute(musicStyles, MusicStyle.class);

        /*execute test*/
        LinkORM linkORM = new LinkORM(con);
        Field field = User.class.getDeclaredField("musicPreferences");
        linkORM.setElementComplexFieldValueLink(users, field);

        /*check result*/
        Map<Integer, Set<Integer>> connections = linkORM.getElementComplexFieldValueLink(users, field);
        Set<Integer> idFieldValues = connections.get(tom.getId());
        assertThat(idFieldValues.size(), is(2));
        assertThat(idFieldValues.contains(rock.getId()), is(true));
        assertThat(idFieldValues.contains(classic.getId()), is(true));
    }

}