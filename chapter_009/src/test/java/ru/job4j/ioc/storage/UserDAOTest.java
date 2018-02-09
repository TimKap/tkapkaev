package ru.job4j.ioc.storage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.junit.Test;
import ru.job4j.ioc.model.User;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class UserDAOTest содержит тесты к UserDAO.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 09.02.2018
 * */
public class UserDAOTest {
    /** userDAO. */
    private UserDAO userDAO;
    /**
     * Возвращает UserDAO.
     * @return экземпляр UserDAO.
     * */
    private UserDAO getUserDAO() {
        if (userDAO == null) {
            ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
            userDAO = context.getBean(UserDAO.class);
        }
        return userDAO;
    }

    /**
     * Тест для persist and get.
     * */
    @Test
    public void whenPersistObjectThenGetSameObject() {
        /*prepare test*/
        /*execute test*/
        UserDAO userDAO = getUserDAO();
        User tom = new User("Tom", "Garison", "tom");
        userDAO.persist(tom);
        /*get result*/
        User result = userDAO.get(tom.getId());
        /*check result*/
        assertThat(result.getId(), is(tom.getId()));
        assertThat(result.getLogin(), is(tom.getLogin()));
        assertThat(result.getPassword(), is(tom.getPassword()));
        assertThat(result.getSurname(), is(tom.getSurname()));
    }

    /**
     * Тест для remove.
     * */
    @Test
    public void whenRemoveThenGetNull() {
        /*prepare test*/
        UserDAO userDAO = getUserDAO();
        User tom = new User("Tom", "Garison", "tom");
        userDAO.persist(tom);
        /*execute test*/
        userDAO.remove(tom);
        /*get result*/
        User result = userDAO.get(tom.getId());
        /*check result*/
        assertNull(result);
    }
    /**
     * Тест для update.
     * */
    @Test
    public void whenUpdateThenGetUpdatedElement() {
        /*prepare test*/
        UserDAO userDAO = getUserDAO();
        User tom = new User("Tom", "Garison", "tom");
        userDAO.persist(tom);
        /*execute test*/
        tom.setLogin("Genry");
        tom.setPassword("genry");
        tom.setSurname("Adkinson");
        userDAO.update(tom);
        /*get result*/
        User result = userDAO.get(tom.getId());
        /*check result*/
        assertThat(result.getId(), is(tom.getId()));
        assertThat(result.getLogin(), is(tom.getLogin()));
        assertThat(result.getPassword(), is(tom.getPassword()));
        assertThat(result.getSurname(), is(tom.getSurname()));
    }

}