package ru.job4j.generic;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class UserStore содержит тесты для класса USerStore.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 07.07.207
 */
public class UserStoreTest {

    /**
     * Тест для метода Add.
     * */
    @Test
    public void whenAddThenUserStoreHasNewObject() {
        Base[] expected = {new User("1"), new User("2")};

        UserStore userStore = new UserStore(5);
        userStore.add(new User("1"));
        userStore.add(new User("2"));

        SimpleArray<User> result = userStore.getList();

        assertThat(result.get(0), is(expected[0]));
        assertThat(result.get(1), is(expected[1]));

    }

    /**
     * Тест для метода remove.
     * */
    @Test
    public void whenDeleteThenGetStoreWithoutOneElement() {
        Base[] expected = {new User("2")};

        UserStore userStore = new UserStore(5);
        userStore.add(new User("1"));
        userStore.add(new User("2"));

        userStore.remove(new User("1"));
        SimpleArray<User> result = userStore.getList();

        assertThat(result.get(0), is(expected[0]));

    }
    /**
     * Тест для метода update.
     * */
    @Test
    public void whenUpdateThenGetStoreWithModifiedElement() {
        Base expected = new User("100");

        UserStore userStore = new UserStore(5);
        userStore.add(new User("1"));
        userStore.add(new User("2"));

        userStore.update("2", new User("100"));
        SimpleArray<User> result = userStore.getList();

        assertThat(result.get(1), is(expected));

    }
}