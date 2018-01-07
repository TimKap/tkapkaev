package ru.job4j.testtask.model.storage.dao.implementations;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import ru.job4j.testtask.model.entities.Address;
import ru.job4j.testtask.model.entities.MusicStyle;
import ru.job4j.testtask.model.entities.Role;
import ru.job4j.testtask.model.entities.User;

import java.io.IOException;
import java.util.ArrayList;

//import java.util.Arrays;
//import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class UserDataBaseTest contains tests fro class UserDataBase.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 23.12.2017
 * */
public class UserDataBaseTest {

    /**database connection pool.*/
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
     * Тест для метода add и get.
     * @throws Exception -
     * */
    @Test
    public void whenAddUserThenGetSameUser() throws Exception {
        /* prepare database.*/
        UserDataBase usersDatabase = new UserDataBase(connectionPool);
        usersDatabase.removeAll();

        AddressDataBase addressesDatabase = new AddressDataBase(connectionPool);
        addressesDatabase.removeAll();

        MusicStyleDataBase musicStyleDatabase = new MusicStyleDataBase(connectionPool);
        musicStyleDatabase.removeAll();

        RoleDataBase rolesDatabase = new RoleDataBase(connectionPool);
        rolesDatabase.removeAll();


        Address teatralnaya = new Address("Russia", "Moscow", "Teatralnaya");

        MusicStyle rock = new MusicStyle("ROCK");
        MusicStyle classic = new MusicStyle("CLASSIC");

        Role admin = new Role("ADMIN");


        User user = new User.UserBuilder().addName("Tom").addSurname("Garison").addRole(admin).addAddress(teatralnaya).build();
        user.getMusicPreferences().add(rock);
        user.getMusicPreferences().add(classic);

        /* execute test*/
        usersDatabase.add(user);
        User result = usersDatabase.get(String.valueOf(user.getId()));

        /* check test */
        assertThat(result, is(user));
        assertThat(result.getName(), is(user.getName()));
        assertThat(result.getSurname(), is(user.getSurname()));
        assertThat(result.getRole(), is(user.getRole()));
        assertThat(result.getAddress(), is(user.getAddress()));
        assertThat(result.getMusicPreferences().containsAll(user.getMusicPreferences()), is(true));
    }

    /**
     *  Test for remove method.
     *  @throws Exception -
     * */
    @Test
    public void whenRemoveUserThenGetEmptyDatabase() throws Exception {
        /* prepare database*/
        UserDataBase usersDatabase = new UserDataBase(connectionPool);
        usersDatabase.removeAll();

        AddressDataBase addressesDatabase = new AddressDataBase(connectionPool);
        addressesDatabase.removeAll();

        MusicStyleDataBase musicStyleDatabase = new MusicStyleDataBase(connectionPool);
        musicStyleDatabase.removeAll();

        RoleDataBase rolesDatabase = new RoleDataBase(connectionPool);
        rolesDatabase.removeAll();


        Address teatralnaya = new Address("Russia", "Moscow", "Teatralnaya");

        MusicStyle rock = new MusicStyle("ROCK");
        MusicStyle classic = new MusicStyle("CLASSIC");

        Role admin = new Role("ADMIN");


        User user = new User.UserBuilder().addName("Tom").addSurname("Garison").addRole(admin).addAddress(teatralnaya).build();
        user.getMusicPreferences().add(rock);
        user.getMusicPreferences().add(classic);

        usersDatabase.add(user);

        /* execute test */
        usersDatabase.remove(user);

        /* check test*/
        User result  = usersDatabase.get(String.valueOf(user.getId()));
        assertThat(result == null, is(true));
    }


    /**
     * Test for getAllElements.
     * @throws Exception -
     * */
    @Test
    public void whenGetAllElementsThenGetListOfAddresses() throws Exception {
        /* prepare database.*/
        UserDataBase usersDatabase = new UserDataBase(connectionPool);
        usersDatabase.removeAll();

        AddressDataBase addressesDatabase = new AddressDataBase(connectionPool);
        addressesDatabase.removeAll();

        MusicStyleDataBase musicStyleDatabase = new MusicStyleDataBase(connectionPool);
        musicStyleDatabase.removeAll();

        RoleDataBase rolesDatabase = new RoleDataBase(connectionPool);
        rolesDatabase.removeAll();


        Address teatralnaya = new Address("Russia", "Moscow", "Teatralnaya");

        MusicStyle rock = new MusicStyle("ROCK");
        MusicStyle classic = new MusicStyle("CLASSIC");

        Role admin = new Role("ADMIN");


        User user = new User.UserBuilder().addName("Tom").addSurname("Garison").addRole(admin).addAddress(teatralnaya).build();
        user.getMusicPreferences().add(rock);
        user.getMusicPreferences().add(classic);


        usersDatabase.add(user);
        /* execute test*/
        user.setName("TomUpdate");
        user.setSurname("GarisonUpdate");
        user.setPassword("sto500");
        Address tverskaya = new Address("Russia", "Moscow", "Tverskaya");
        user.setAddress(tverskaya);
        user.getMusicPreferences().clear();
        MusicStyle indy = new MusicStyle("INDY");
        user.getMusicPreferences().add(indy);
        user.getMusicPreferences().add(classic);
        /*execute test*/
        usersDatabase.update(user);
        /*check result*/
        User result = usersDatabase.get(String.valueOf(user.getId()));

        assertThat(result, is(user));
        assertThat(result.getName(), is(user.getName()));
        assertThat(result.getSurname(), is(user.getSurname()));
        assertThat(result.getRole(), is(user.getRole()));
        assertThat(result.getPassword(), is(user.getPassword()));
        assertThat(result.getAddress(), is(user.getAddress()));
        assertThat(result.getMusicPreferences().containsAll(user.getMusicPreferences()), is(true));

    }
    /**
     * Тест для метода isCredential.
     * @throws Exception -
     * */
    @Test
    public void whenIsCredentialThenTrue() throws Exception {
        /* prepare database.*/
        UserDataBase usersDatabase = new UserDataBase(connectionPool);
        usersDatabase.removeAll();

        AddressDataBase addressesDatabase = new AddressDataBase(connectionPool);
        addressesDatabase.removeAll();

        MusicStyleDataBase musicStyleDatabase = new MusicStyleDataBase(connectionPool);
        musicStyleDatabase.removeAll();

        RoleDataBase rolesDatabase = new RoleDataBase(connectionPool);
        rolesDatabase.removeAll();


        Address teatralnaya = new Address("Russia", "Moscow", "Teatralnaya");
        Address tverskaya = new Address("Russia", "Moscow", "Tverskaya");

        MusicStyle rock = new MusicStyle("ROCK");
        MusicStyle classic = new MusicStyle("CLASSIC");

        Role admin = new Role("ADMIN");
        Role guest = new Role("GUEST");


        User tom = new User.UserBuilder().addName("Tom").addPassword("sto500").addSurname("Garison").addRole(admin).addAddress(teatralnaya).build();
        tom.getMusicPreferences().add(rock);
        tom.getMusicPreferences().add(classic);

        User ann = new User.UserBuilder().addName("Ann").addPassword("sto500").addSurname("Anderson").addRole(guest).addAddress(tverskaya).build();

        usersDatabase.add(tom);
        usersDatabase.add(ann);
        /* execute test*/
        User result = usersDatabase.isCredential("Tom", "sto500");

        assertThat(result, is(tom));
    }
    /**
     * Тест для getUsersByAddress.
     * @throws Exception -
     * */
    @Test
    public void whenGetUsersByAddressThenGetUsersWithSpecifiedAddress() throws Exception {
        /* prepare database.*/
        UserDataBase usersDatabase = new UserDataBase(connectionPool);
        usersDatabase.removeAll();

        AddressDataBase addressesDatabase = new AddressDataBase(connectionPool);
        addressesDatabase.removeAll();

        MusicStyleDataBase musicStyleDatabase = new MusicStyleDataBase(connectionPool);
        musicStyleDatabase.removeAll();

        RoleDataBase rolesDatabase = new RoleDataBase(connectionPool);
        rolesDatabase.removeAll();


        Address teatralnaya = new Address("Russia", "Moscow", "Teatralnaya");
        Address tverskaya = new Address("Russia", "Moscow", "Tverskaya");

        MusicStyle rock = new MusicStyle("ROCK");
        MusicStyle classic = new MusicStyle("CLASSIC");

        Role admin = new Role("ADMIN");
        Role guest = new Role("GUEST");


        User tom = new User.UserBuilder().addName("Tom").addPassword("sto500").addSurname("Garison").addRole(admin).addAddress(teatralnaya).build();
        tom.getMusicPreferences().add(rock);
        tom.getMusicPreferences().add(classic);

        User ann = new User.UserBuilder().addName("Ann").addPassword("sto500").addSurname("Anderson").addRole(guest).addAddress(tverskaya).build();

        usersDatabase.add(tom);
        usersDatabase.add(ann);
        /* execute test*/
        User result = usersDatabase.getByAddress(tverskaya).iterator().next();
        /*check test*/
        assertThat(result.getName(), is(ann.getName()));
        assertThat(result.getAddress(), is(ann.getAddress()));
    }

    /**
     * Тест для getUsersByRole.
     * @throws Exception -
     * */
    @Test
    public void whenGetByRoleThenReturnUsersWithSpecifiedRole() throws Exception {
        /* prepare database.*/
        UserDataBase usersDatabase = new UserDataBase(connectionPool);
        usersDatabase.removeAll();

        AddressDataBase addressesDatabase = new AddressDataBase(connectionPool);
        addressesDatabase.removeAll();

        MusicStyleDataBase musicStyleDatabase = new MusicStyleDataBase(connectionPool);
        musicStyleDatabase.removeAll();

        RoleDataBase rolesDatabase = new RoleDataBase(connectionPool);
        rolesDatabase.removeAll();


        Address teatralnaya = new Address("Russia", "Moscow", "Teatralnaya");
        Address tverskaya = new Address("Russia", "Moscow", "Tverskaya");

        MusicStyle rock = new MusicStyle("ROCK");
        MusicStyle classic = new MusicStyle("CLASSIC");

        Role admin = new Role("ADMIN");
        Role guest = new Role("GUEST");


        User tom = new User.UserBuilder().addName("Tom").addPassword("sto500").addSurname("Garison").addRole(admin).addAddress(teatralnaya).build();
        tom.getMusicPreferences().add(rock);
        tom.getMusicPreferences().add(classic);

        User ann = new User.UserBuilder().addName("Ann").addPassword("sto500").addSurname("Anderson").addRole(guest).addAddress(tverskaya).build();

        usersDatabase.add(tom);
        usersDatabase.add(ann);
        /* execute test*/
        User result = usersDatabase.getByRole(admin).iterator().next();
        /*check test*/
        assertThat(result.getName(), is(tom.getName()));
        assertThat(result.getRole(), is(tom.getRole()));

    }

    /**
     * Тест для getUsersByMusicPreferences.
     * @throws Exception -
     * */
    @Test
    public void whenGetByMusicPreferencesThenGetUserWithSpecifiedPreferences() throws Exception {
                /* prepare database.*/
        UserDataBase usersDatabase = new UserDataBase(connectionPool);
        usersDatabase.removeAll();

        AddressDataBase addressesDatabase = new AddressDataBase(connectionPool);
        addressesDatabase.removeAll();

        MusicStyleDataBase musicStyleDatabase = new MusicStyleDataBase(connectionPool);
        musicStyleDatabase.removeAll();

        RoleDataBase rolesDatabase = new RoleDataBase(connectionPool);
        rolesDatabase.removeAll();


        Address teatralnaya = new Address("Russia", "Moscow", "Teatralnaya");
        Address tverskaya = new Address("Russia", "Moscow", "Tverskaya");

        MusicStyle rock = new MusicStyle("ROCK");
        MusicStyle classic = new MusicStyle("CLASSIC");

        Role admin = new Role("ADMIN");
        Role guest = new Role("GUEST");


        User tom = new User.UserBuilder().addName("Tom").addPassword("sto500").addSurname("Garison").addRole(admin).addAddress(teatralnaya).build();
        tom.getMusicPreferences().add(rock);
        tom.getMusicPreferences().add(classic);

        User ann = new User.UserBuilder().addName("Ann").addPassword("sto500").addSurname("Anderson").addRole(guest).addAddress(tverskaya).build();
        ann.getMusicPreferences().add(classic);

        usersDatabase.add(tom);
        usersDatabase.add(ann);
        /* execute test*/
        ArrayList<MusicStyle> musicPreferences = new ArrayList<>();
        musicPreferences.add(rock);
        musicPreferences.add(classic);
        User result = usersDatabase.getByMusicPreferences(musicPreferences).iterator().next();
        /*check test*/
        assertThat(result.getName(), is(tom.getName()));
        assertThat(result.getRole(), is(tom.getRole()));


    }



}