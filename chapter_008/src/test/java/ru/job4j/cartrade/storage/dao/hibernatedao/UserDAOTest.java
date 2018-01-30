package ru.job4j.cartrade.storage.dao.hibernatedao;

import org.junit.Test;
import ru.job4j.cartrade.model.car.Car;
import ru.job4j.cartrade.model.user.User;
import ru.job4j.cartrade.storage.Storage;
import ru.job4j.cartrade.storage.dao.IUserDAO;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class UserDAOTest содержит тесты к UserDAO.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 24.01.2018
 * */
public class UserDAOTest {
    /**
     * Тест для persist и get.
     */
    @Test
    public void whenPersistThenGetPersistedUser() {
        /*prepare test */
        Storage storage = Storage.getInstance();
        storage.open();
        IUserDAO userDAO = storage.getUserDAO();

        User user = new User();
        user.setName("Tom");
        user.setSurname("Garison");
        user.setPassword("tom");
        Set<Car> cars = new HashSet<>();
        Car bmw = new Car();
        bmw.setModel("BMW");
        Car audi = new Car();
        audi.setModel("Audi");
        cars.add(bmw);
        cars.add(audi);
        user.setCars(cars);

        /* execute test */
        userDAO.persist(user);
        /* get result */
        User result = userDAO.get(user.getId());
        storage.submit();
        storage.close();

        /*check test*/
        assertThat(result, is(user));
    }
    /**
     * Demonstrate.
     */
    @Test
    public void demonstrate() {
        Storage storage = Storage.getInstance();
        storage.open();
        IUserDAO userDAO = storage.getUserDAO();
        User user = userDAO.credential("Tom", "tom");
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        storage.submit();
        storage.close();
    }
}