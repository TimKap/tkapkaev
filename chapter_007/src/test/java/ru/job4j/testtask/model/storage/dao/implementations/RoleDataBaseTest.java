package ru.job4j.testtask.model.storage.dao.implementations;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import ru.job4j.testtask.model.entities.Role;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class RoleDataBaseTest содержит тесты для RoleDataBase.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 22.12.2017
 * */
public class RoleDataBaseTest {
    /** database connection pool. */
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
     * Test for add and get methods.
     * @throws Exception -
     * */
    @Test
    public void whenAddRoleThenGetSameRole() throws Exception {
        /* prepare database */
        RoleDataBase roles = new RoleDataBase(connectionPool);
        roles.removeAll();

        Role role = new Role("ADMIN");

        /* execute test*/
        roles.add(role);

        /*check test*/
        Role result = roles.get(String.valueOf(role.getId()));
        assertThat(result, is(role));
        assertThat(result.getRole(), is(role.getRole()));
    }

    /**
     * Test for update methods.
     * @throws Exception -
     * */
    @Test
    public void whenUpdateRoleThenGetUpdatedRole() throws Exception {
        /* prepare database */
        RoleDataBase musicStyles = new RoleDataBase(connectionPool);
        musicStyles.removeAll();

        Role role = new Role("ADMIN");
        musicStyles.add(role);

        /*execute test*/
        role.setRole("MANDATOR");
        musicStyles.update(role);

        /*check test*/
        Role result = musicStyles.get(String.valueOf(role.getId()));

        assertThat(result, is(role));
        assertThat(result.getRole(), is(role.getRole()));

    }
    /**
     * Test for remove method.
     * @throws Exception -
     * */
    @Test
    public void whenRemoveRoleThenGetEmptyDatabase() throws Exception {
        /* prepare database*/
        RoleDataBase roles = new RoleDataBase(connectionPool);
        roles.removeAll();
        Role role = new Role("ADMIN");

        roles.add(role);

        /* execute test */
        roles.remove(role);

        /* check result */
        Role  result = roles.get(String.valueOf(role.getId()));

        assertThat(result == null, is(true));

    }

    /**
     * Test for getAllElements.
     * @throws Exception -
     * */
    @Test
    public void whenGetAllElementsThenGetListOfRoles() throws Exception {
        /* prepare database*/
        RoleDataBase roles = new RoleDataBase(connectionPool);
        roles.removeAll();
        List<Role> expected = new ArrayList<>();
        expected.add(new Role("ADMIN"));
        expected.add(new Role("USER"));

        roles.add(expected.get(0));
        roles.add(expected.get(1));

        /* execute test*/
        List<Role> result = roles.getAllElements();
        assertThat(result.containsAll(expected), is(true));
    }

}