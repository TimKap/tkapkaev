package ru.job4j.security.model;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;


/**
 * AdvancedUserStoreSecurityStore содержит тесты для AdvancedUserSecurityStore.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 20.11.2017
 * */
public class AdvancedUserSecurityStoreTest {


    /**
     * Тест для метода clearUsers.
     * @throws Exception - ошибка очистки базы данных
     * */
    @Test
    public void whenClearUsersThenGetEmptyDB() throws Exception {
        AdvancedUserSecurityStore store = AdvancedUserSecurityStore.getInstance();
        store.clearUsers();
        List<AdvancedUser> list = store.getAllUsers();
        assertThat(list.size(), is(0));

    }
    /**
     * Тест для метода insert.
     * @throws Exception -
     * */
    @Test
    public void whenInsertThenGetUserInDatabase() throws Exception {
        AdvancedUserSecurityStore store = AdvancedUserSecurityStore.getInstance();
        store.clearUsers();

        AdvancedUser user = new AdvancedUser.AdvancedUserBuilder().addLogin("tom").addName("Tom").addEmail("tom@mail.ru").
                addRole("admin").addPassword("tom").build();

        store.insert(user);

        AdvancedUser result = store.searchByPrimaryKey(new AdvancedUser.Key("tom"));

        assertThat(result.getLogin(), is(user.getLogin()));
        assertThat(result.getName(), is(user.getName()));
        assertThat(result.getEmail(), is(user.getEmail()));
        assertThat(result.getRole(), is(user.getRole()));
        assertThat(result.getPassword(), is(user.getPassword()));
    }
    /**
     * Тест для метода delete.
     * @throws Exception -
     * */
    @Test
    public void whenDeleteThenGetDatabaseWithoutOneElement() throws Exception {
        AdvancedUserSecurityStore store = AdvancedUserSecurityStore.getInstance();
        store.clearUsers();

        AdvancedUser user = new AdvancedUser.AdvancedUserBuilder().addLogin("tom").addName("Tom").addEmail("tom@mail.ru").
                addRole("admin").addPassword("tom").build();
        store.insert(user);

        AdvancedUser deletedUser = new AdvancedUser.AdvancedUserBuilder().addLogin("tom").build();
        store.delete(deletedUser);

        AdvancedUser result = store.searchByPrimaryKey(new AdvancedUser.Key("tom"));
        assertThat(result == null, is(true));
    }

    /**
     * Тест для метода update.
     * @throws Exception -
     * */
    @Test
    public void whenUpdateThenGetUpdatedElementInDatabase() throws Exception {
        AdvancedUserSecurityStore store = AdvancedUserSecurityStore.getInstance();
        store.clearUsers();

        AdvancedUser user = new AdvancedUser.AdvancedUserBuilder().addLogin("tom").addName("Tom").addEmail("tom@mail.ru").
                addRole("admin").addPassword("tom").build();

        store.insert(user);

        AdvancedUser updatedUser = new AdvancedUser.AdvancedUserBuilder().addLogin("tom").addName("updateTom").addEmail("update_tom@mail.ru").
                addRole("guest").addPassword("update_tom").build();

        store.update(updatedUser);
        user = store.searchByPrimaryKey(new AdvancedUser.Key("tom"));

        assertThat(user.getLogin(), is(updatedUser.getLogin()));
        assertThat(user.getName(), is(updatedUser.getName()));
        assertThat(user.getEmail(), is(updatedUser.getEmail()));
        assertThat(user.getRole(), is(updatedUser.getRole()));
        assertThat(user.getPassword(), is(updatedUser.getPassword()));
    }

    /**
     * Тест жля метода isCredential.
     * @throws Exception -
     * */
    @Test
    public void whenCredentialUserThenGetUser() throws Exception {
        AdvancedUserSecurityStore store = AdvancedUserSecurityStore.getInstance();
        store.clearUsers();

        AdvancedUser user = new AdvancedUser.AdvancedUserBuilder().addLogin("tom").addName("Tom").addEmail("tom@mail.ru").
                addRole("admin").addPassword("tom").build();

        store.insert(user);

        AdvancedUser result = store.credentialUser("tom", "tom");
        assertThat(result.getLogin(), is(user.getLogin()));
        assertThat(result.getName(), is(user.getName()));
        assertThat(result.getEmail(), is(user.getEmail()));
        assertThat(result.getRole(), is(user.getRole()));
        assertThat(result.getPassword(), is(user.getPassword()));

    }


}