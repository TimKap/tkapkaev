package ru.job4j.testtask.model.storage.orm;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import ru.job4j.testtask.model.entities.Address;
import ru.job4j.testtask.model.entities.MusicStyle;
import ru.job4j.testtask.model.entities.Role;
import ru.job4j.testtask.model.entities.User;
import ru.job4j.testtask.model.storage.dao.implementations.TestDataBasePoolConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * RemoveORMTest содержит тесты для RemoveORM.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 02.01.2017
 * */
public class RemoveORMTest {
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
     * Тест для Remove.
     * @throws Exception -
     * */
    @Test
    public void whenRemoveThenGetEmptyCollection() throws Exception {

        /*prepared database*/
        Connection con = connectionPool.getConnection();
        Statement st = con.createStatement();
        st.execute("DELETE FROM users;");
        st.execute("DELETE FROM roles");
        st.execute("DELETE FROM musicstyles");
        st.execute("DELETE FROM addresses");

        Address teatralnaya = new Address("Russia", "Moscow", "Teatralnaya");
        Address tverskaya = new Address("Russia", "Moscow", "Tverskaya");
        Role admin = new Role("ADMIN");
        Role guest = new Role("GUEST");

        MusicStyle rock = new MusicStyle("ROCK");
        MusicStyle classic = new MusicStyle("CLASSIC");
        MusicStyle indy = new MusicStyle("INDY");

        User tom = new User.UserBuilder().addName("Tom").addSurname("Garison").addRole(admin).addAddress(teatralnaya).build();
        tom.getMusicPreferences().add(rock);
        tom.getMusicPreferences().add(classic);

        User ann = new User.UserBuilder().addName("Ann").addSurname("Anderson").addRole(guest).addAddress(tverskaya).build();
        ann.getMusicPreferences().add(indy);
        ann.getMusicPreferences().add(classic);

        ArrayList<User> users = new ArrayList<>();
        users.add(tom);
        users.add(ann);

        SimplifyORM orm = new SimplifyORM(con);
        orm.insert(users, User.class);

        /*execute test*/
        RemoveORM removeORM = new RemoveORM(con);
        removeORM.execute(users, User.class);
         /*check result*/
        HashMap<String, Set> idMatches = new HashMap<>();
        Set<Integer> idSet = new HashSet<>();
        idSet.add(tom.getId());
        idSet.add(ann.getId());
        idMatches.put("id", idSet);

        Collection<User> result = orm.get(idMatches, User.class);
        assertThat(result.size(), is(0));
    }

}