package ru.job4j.cartrade.storage.dao.hibernatedao;

import org.junit.Test;
import ru.job4j.cartrade.model.car.Car;
import ru.job4j.cartrade.model.user.User;
import ru.job4j.cartrade.storage.Storage;
import ru.job4j.cartrade.storage.dao.ICarDAO;
import ru.job4j.cartrade.storage.dao.IUserDAO;

import java.util.ArrayList;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class UserDAOTest содержит тесты к UserDAO.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 24.01.2018
 * */
public class UserDAOTest {
    /** вспомогательный объект.*/
    private final Auxiliary auxiliary = new Auxiliary();
    /**
     * Тест для persist и get.
     */
    @Test
    public void whenPersistThenGetPersistedUser() {
        /*prepare test */
        Storage storage = Storage.getInstance();
        storage.open();
        Car bmw = auxiliary.prepareBMW();
        ICarDAO carDAO = storage.getCarDAO();
        carDAO.persist(bmw);

        /*execute test*/
        IUserDAO userDAO = storage.getUserDAO();
        User tom = auxiliary.prepareTom();
        userDAO.persist(tom);
        tom.getCars().add(bmw);
        bmw.getOwners().add(tom);

        /* get result */
        User result = userDAO.get(tom.getId());

        /*check test*/
        assertThat(result, is(tom));
        storage.submit();
    }
    /**
     * Тест для update.
     * */
    @Test
    public void whenUpdateUserThenGetModifiedUser() {
        /* prepare test */
        Storage storage = Storage.getInstance();
        storage.open();
        User tom = auxiliary.prepareTom();
        IUserDAO userDAO = storage.getUserDAO();
        userDAO.persist(tom);
        storage.submit();

        /*execute test*/
        tom.setName("Ann");
        tom.setSurname("Anderson");
        tom.setPassword("ann");
        storage.open();
        userDAO = storage.getUserDAO();
        userDAO.update(tom);

        /* get result*/
        User result = userDAO.get(tom.getId());
        /*check result*/
        assertThat(result, is(tom));
        storage.submit();
    }

    /**
     * Тест для getAll.
     * */
    @Test
    public void whenGetAllThenGetAllUsers() {
        /*prepare test*/
        Storage storage = Storage.getInstance();
        storage.open();
        User tom = auxiliary.prepareTom();
        User ann = auxiliary.prepareAnn();
        IUserDAO userDAO = storage.getUserDAO();
        userDAO.persist(tom);
        userDAO.persist(ann);
        List<User> expected = new ArrayList<>();
        expected.add(ann);
        expected.add(tom);
        /*get result*/
        List<User> result = userDAO.getAll();
        /*check result*/
        assertThat(result.containsAll(expected), is(true));
        storage.submit();
    }
    /**
     * Тест для remove.
     * */
    @Test
    public void whenRemove() {
        /*prepare test*/
        Storage storage = Storage.getInstance();
        storage.open();
        IUserDAO userDAO = storage.getUserDAO();
        ICarDAO carDAO = storage.getCarDAO();
        User tom = auxiliary.prepareTom();
        userDAO.persist(tom);
        Car bmw = auxiliary.prepareBMW();
        carDAO.persist(bmw);
        tom.getCars().add(bmw);
        bmw.getOwners().add(tom);
        storage.submit();

        /*execute test*/
        storage.open();
        userDAO = storage.getUserDAO();
        tom = userDAO.get(tom.getId());
        bmw = storage.getCarDAO().get(bmw.getId());
        bmw.getOwners().remove(tom);
        userDAO.remove(tom);
        /*get result*/
        User result = userDAO.get(tom.getId());
        /*check test*/
        assertThat(result == null, is(true));
        storage.submit();
    }
    /**
     * Тест для credential.
     * */
    @Test
    public void whenCredentialThenGetUser() {
        /*prepare test*/
        User genry = new User();
        genry.setName("Genry");
        genry.setPassword("genry");
        Storage storage = Storage.getInstance();
        storage.open();
        IUserDAO userDAO = storage.getUserDAO();
        userDAO.persist(genry);
        storage.submit();
        /*execute test*/
        /*get result*/
        storage.open();
        userDAO = storage.getUserDAO();
        User result = userDAO.credential(genry.getName(), genry.getPassword());
        /*check result*/
        assertThat(result, is(genry));
    }

}