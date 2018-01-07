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
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;



import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class SimplifyORMTest содержит тесты для SimplifyORM.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 27.12.2017
 * */
public class SimplifyORMTest {

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
     * Тест для метода  insert и get.
     * Вставка пустого объекта
     * @throws Exception -
     * */
    @Test
    public void whenInsertEmptyElementThenGetEmptyElement() throws Exception {
        /*prepared database*/
        Connection con = connectionPool.getConnection();
        Statement st = con.createStatement();
        st.execute("DELETE FROM users;");
        st.execute("DELETE FROM roles");
        st.execute("DELETE FROM musicstyles");
        st.execute("DELETE FROM addresses");

        User tom = new User.UserBuilder().build();
        SimplifyORM simplifyORM = new SimplifyORM(con);

        ArrayList<User> users = new ArrayList<>();
        users.add(tom);

        /*execute test*/
        simplifyORM.insert(users, User.class);

        /*check test*/
        HashMap<String, Set> idMatches = new HashMap<>();
        Set<Integer> idSet = new HashSet<>();
        idSet.add(tom.getId());
        idMatches.put("id", idSet);

        Collection<User> result = simplifyORM.get(idMatches, User.class);

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
        assertThat(tom.getAddress(), is(tomResult.getAddress()));
        assertThat(tom.getRole(), is(tomResult.getRole()));
        assertThat(tom.getMusicPreferences(), is(tomResult.getMusicPreferences()));

    }
    /**
     * Тест метода insert и get.
     * Вставка одного элемента
     * @throws Exception -
     * */
    @Test
    public void whenInsertElementThenGetSameElement() throws Exception {
        /*prepared database*/
        Connection con = connectionPool.getConnection();
        Statement st = con.createStatement();
        st.execute("DELETE FROM users;");
        st.execute("DELETE FROM roles");
        st.execute("DELETE FROM musicstyles");
        st.execute("DELETE FROM addresses");

        Address address = new Address("Russia", "Moscow", "Teatralnaya");

        User tom = new User.UserBuilder().addName("Tom").addRole(new Role("ADMIN")).addAddress(address).addSurname("Garison").build();
        MusicStyle rock = new MusicStyle("ROCK");
        MusicStyle classic = new MusicStyle("CLASSIC");
        tom.getMusicPreferences().add(rock);
        tom.getMusicPreferences().add(classic);
        SimplifyORM orm = new SimplifyORM(con);

        ArrayList<User> users = new ArrayList<>();
        users.add(tom);

        /*execute test*/
        orm.insert(users, User.class);

        /* check result*/
        HashMap<String, Set> idMatches = new HashMap<>();
        Set<Integer> idSet = new HashSet<>();
        idSet.add(tom.getId());
        idMatches.put("id", idSet);

        Collection<User> result = orm.get(idMatches, User.class);
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
        assertThat(tom.getAddress(), is(tomResult.getAddress()));
        assertThat(tom.getRole(), is(tomResult.getRole()));
        assertThat(tom.getMusicPreferences().contains(rock), is(true));
        assertThat(tom.getMusicPreferences().contains(classic), is(true));
    }

    /**
     * Тест метода insert и get.
     * Вставка коллекции элементов
     * @throws Exception -
     * */
    @Test
    public void whenInsertCollectionElementThenGetSameCollectionElement() throws Exception {
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

        /*execute test*/
        SimplifyORM orm = new SimplifyORM(con);
        orm.insert(users, User.class);

        /* check result*/
        HashMap<String, Set> idMatches = new HashMap<>();
        Set<Integer> idSet = new HashSet<>();
        idSet.add(tom.getId());
        idSet.add(ann.getId());
        idMatches.put("id", idSet);

        Collection<User> result = orm.get(idMatches, User.class);
        /*check test*/
        User tomResult = null;
        User annResult = null;
        for (User user : result) {
            if (user.getId() == tom.getId()) {
                tomResult = user;
            } else {
                if (user.getId() == ann.getId()) {
                    annResult = user;
                }
            }
        }

        /*check result*/
        assertThat(result.size(), is(2));
        assertThat(tom.getId(), is(tomResult.getId()));
        assertThat(tom.getName(), is(tomResult.getName()));
        assertThat(tom.getSurname(), is(tomResult.getSurname()));
        assertThat(tom.getAddress(), is(tomResult.getAddress()));
        assertThat(tom.getRole(), is(tomResult.getRole()));
        assertThat(tom.getMusicPreferences().contains(rock), is(true));
        assertThat(tom.getMusicPreferences().contains(classic), is(true));

        assertThat(ann.getId(), is(annResult.getId()));
        assertThat(ann.getName(), is(annResult.getName()));
        assertThat(ann.getSurname(), is(annResult.getSurname()));
        assertThat(ann.getAddress(), is(annResult.getAddress()));
        assertThat(ann.getRole(), is(annResult.getRole()));
        assertThat(ann.getMusicPreferences().contains(indy), is(true));
        assertThat(ann.getMusicPreferences().contains(classic), is(true));
    }


    /**
     * Тест метода update и get.
     * Обновление пустого элемента.
     * @throws Exception -
     * */
    @Test
    public void whenUpdateThenGetModifiedElementInDatabase() throws Exception {
        /*prepared database*/
        Connection con = connectionPool.getConnection();
        Statement st = con.createStatement();
        st.execute("DELETE FROM users;");
        st.execute("DELETE FROM roles");
        st.execute("DELETE FROM musicstyles");
        st.execute("DELETE FROM addresses");

        User tom = new User.UserBuilder().build();
        SimplifyORM orm = new SimplifyORM(con);

        ArrayList<User> users = new ArrayList<>();
        users.add(tom);


        orm.insert(users, User.class);
        /*execute test*/
        Address address = new Address("Russia", "Moscow", "Teatralnaya");
        tom.setName("Tom");
        tom.setSurname("Garison");
        tom.setRole(new Role("ADMIN"));
        MusicStyle rock = new MusicStyle("ROCK");
        MusicStyle classic = new MusicStyle("CLASSIC");
        tom.getMusicPreferences().add(rock);
        tom.getMusicPreferences().add(classic);

        orm.update(users, User.class);

        /*check test*/
        HashMap<String, Set> idMatches = new HashMap<>();
        Set<Integer> idSet = new HashSet<>();
        idSet.add(tom.getId());
        idMatches.put("id", idSet);

        Collection<User> result = orm.get(idMatches, User.class);

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
        assertThat(tom.getAddress(), is(tomResult.getAddress()));
        assertThat(tom.getRole(), is(tomResult.getRole()));
        assertThat(tom.getMusicPreferences().containsAll(tomResult.getMusicPreferences()), is(true));
    }

    /**
     * Тест метода update и get.
     * Обновление одного элемента
     * @throws Exception -
     * */
    @Test
    public void whenUpdateElementThenGetModifiedElement() throws Exception {
        /*prepared database*/
        Connection con = connectionPool.getConnection();
        Statement st = con.createStatement();
        st.execute("DELETE FROM users;");
        st.execute("DELETE FROM roles");
        st.execute("DELETE FROM musicstyles");
        st.execute("DELETE FROM addresses");

        Address address = new Address("Russia", "Moscow", "Teatralnaya");

        User tom = new User.UserBuilder().addName("Tom").addRole(new Role("ADMIN")).addAddress(address).addSurname("Garison").build();
        MusicStyle rock = new MusicStyle("ROCK");
        MusicStyle classic = new MusicStyle("CLASSIC");
        tom.getMusicPreferences().add(rock);
        tom.getMusicPreferences().add(classic);
        SimplifyORM orm = new SimplifyORM(con);

        ArrayList<User> users = new ArrayList<>();
        users.add(tom);


        orm.insert(users, User.class);

        /*execute test*/
        tom.setName("TomUpdate");
        tom.setSurname("GarisonUpdate");
        tom.setRole(null);
        address.setStreet("Tverskaya");
        tom.getMusicPreferences().clear();
        MusicStyle indy = new MusicStyle("INDY");
        tom.getMusicPreferences().add(indy);

        orm.update(users, User.class);


        /* check result*/
        HashMap<String, Set> idMatches = new HashMap<>();
        Set<Integer> idSet = new HashSet<>();
        idSet.add(tom.getId());
        idMatches.put("id", idSet);

        Collection<User> result = orm.get(idMatches, User.class);
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
        assertThat(tom.getAddress(), is(tomResult.getAddress()));
        assertThat(tom.getRole(), is(tomResult.getRole()));
        assertThat(tom.getMusicPreferences().contains(indy), is(true));
    }

    /**
     * Тест метода insert и get.
     * Вставка коллекции элементов
     * @throws Exception -
     * */
    @Test
    public void whenUpdateCollectionElementThenGetModifiedCollectionElement() throws Exception {
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

        User tom = new User.UserBuilder().addName("Tom").addRole(admin).addSurname("Garison").addAddress(teatralnaya).build();
        tom.getMusicPreferences().add(rock);


        User ann = new User.UserBuilder().addName("Ann").addSurname("Anderson").addAddress(tverskaya).build();
        ann.getMusicPreferences().add(classic);


        ArrayList<User> users = new ArrayList<>();
        users.add(tom);
        users.add(ann);


        SimplifyORM orm = new SimplifyORM(con);
        orm.insert(users, User.class);
        /*execute test*/
        tom.getMusicPreferences().clear();
        ann.getMusicPreferences().clear();
        tom.getMusicPreferences().add(indy);
        ann.getMusicPreferences().add(indy);
        ann.getMusicPreferences().add(rock);

        tom.setAddress(tverskaya);
        ann.setAddress(teatralnaya);

        tom.setRole(guest);
        ann.setRole(guest);

        orm.update(users, User.class);
        /* check result*/
        HashMap<String, Set> idMatches = new HashMap<>();
        Set<Integer> idSet = new HashSet<>();
        idSet.add(tom.getId());
        idSet.add(ann.getId());
        idMatches.put("id", idSet);

        Collection<User> result = orm.get(idMatches, User.class);
        /*check test*/
        User tomResult = null;
        User annResult = null;
        for (User user : result) {
            if (user.getId() == tom.getId()) {
                tomResult = user;
            } else {
                if (user.getId() == ann.getId()) {
                    annResult = user;
                }
            }
        }

        /*check result*/
        assertThat(result.size(), is(2));
        assertThat(tom.getId(), is(tomResult.getId()));
        assertThat(tom.getName(), is(tomResult.getName()));
        assertThat(tom.getSurname(), is(tomResult.getSurname()));
        assertThat(tom.getAddress(), is(tomResult.getAddress()));
        assertThat(tom.getRole(), is(tomResult.getRole()));
        assertThat(tom.getMusicPreferences().contains(indy), is(true));

        assertThat(ann.getId(), is(annResult.getId()));
        assertThat(ann.getName(), is(annResult.getName()));
        assertThat(ann.getSurname(), is(annResult.getSurname()));
        assertThat(ann.getAddress(), is(annResult.getAddress()));
        assertThat(ann.getRole(), is(annResult.getRole()));
        assertThat(ann.getMusicPreferences().contains(indy), is(true));
        assertThat(ann.getMusicPreferences().contains(rock), is(true));
    }

    /**
     * Тетс дял getAllElementsId.
     * @throws Exception -
     * */
    @Test
    public void whenGetAllElementsIdThenGetAllID() throws Exception {
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

        SimplifyORM simplifyORM = new SimplifyORM(con);
        simplifyORM.insert(users, User.class);

        /*execute test*/
        Set<Integer> result = simplifyORM.getAllElementsId(User.class);
        assertThat(result.size(), is(2));
        assertThat(result.contains(tom.getId()), is(true));
        assertThat(result.contains(ann.getId()), is(true));
    }

}