package ru.job4j.cartrade.storage.dao.hibernatedao;

import org.junit.Test;
import ru.job4j.cartrade.model.car.Body;
import ru.job4j.cartrade.model.car.Car;
import ru.job4j.cartrade.model.car.Engine;
import ru.job4j.cartrade.model.photo.Photo;
import ru.job4j.cartrade.model.user.User;
import ru.job4j.cartrade.storage.Storage;
import ru.job4j.cartrade.storage.dao.ICarDAO;
import ru.job4j.cartrade.storage.dao.IUserDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class CarDAOTest содержит тесты для CarDAO.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 31.01.2018
 * */

public class CarDAOTest {
    /** вспомогательнеый объект. */
    private final Auxiliary auxiliary = new Auxiliary();

    /**
     * Тест для get и add.
     * */
    @Test
    public void whenAddCarThenGetCar() {
        /*prepare test*/
        Storage storage = Storage.getInstance();
        storage.open();
        IUserDAO userDAO = storage.getUserDAO();
        User tom = auxiliary.prepareTom();
        userDAO.persist(tom);

        /*execute test*/
        Car bmw = auxiliary.prepareBMW();
        Photo photo = auxiliary.preparePhoto();
        bmw.getPhotos().add(photo);
        ICarDAO carDAO = storage.getCarDAO();
        carDAO.persist(bmw);

        bmw.getOwners().add(tom);
        tom.getCars().add(bmw);
        storage.submit();
        /*get result*/
        storage.open();
        carDAO = storage.getCarDAO();
        Car result = carDAO.get(bmw.getId());

        /*check test*/
        assertThat(result, is(bmw));
        storage.submit();
    }
    /**
     * Тест для update.
     * */
    @Test
    public void whenUpdateCarThenGetModifiedCar() {
        /*prepare test*/
        Storage storage = Storage.getInstance();
        storage.open();

        Car bmw = auxiliary.prepareBMW();
        User tom = auxiliary.prepareTom();
        ICarDAO carDAO = storage.getCarDAO();
        IUserDAO userDAO = storage.getUserDAO();
        carDAO.persist(bmw);
        userDAO.persist(tom);
        bmw.getOwners().add(tom);
        tom.getCars().add(bmw);
        Photo photo = auxiliary.preparePhoto();
        bmw.getPhotos().add(photo);
        storage.submit();

        /*execute test*/
        bmw.setBody(new Body("Universal", "Yellow"));
        bmw.setEngine(new Engine("Turbo"));
        photo = new Photo();
        photo.setFile(new byte[] {5, 4, 3, 2});
        bmw.getPhotos().clear();
        bmw.getPhotos().add(photo);
        storage.open();
        User ann = auxiliary.prepareAnn();
        storage.getUserDAO().persist(ann);
        bmw.getOwners().add(ann);
        ann.getCars().add(bmw);
        carDAO = storage.getCarDAO();
        carDAO.update(bmw);
        storage.getUserDAO().get(tom.getId()).getCars().remove(bmw);

        /*get result*/
        Car result = carDAO.get(bmw.getId());
        /*check result*/
        assertThat(result, is(bmw));
        storage.submit();
    }

    /**
     * Тест для getAll.
     * */
    @Test
    public void whenGetAllThenGetAllCars() {
        /*prepare test*/
        Car bmw = auxiliary.prepareBMW();
        Car audi = auxiliary.prepareAudi();
        Storage storage = Storage.getInstance();
        storage.open();
        ICarDAO carDAO = storage.getCarDAO();
        carDAO.persist(bmw);
        carDAO.persist(audi);
        List<Car> expected = new ArrayList<>(Arrays.asList(bmw, audi));
        /*execute test*/
        /*get result*/
        List<Car> result = carDAO.getAll();
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
        Car bmw = auxiliary.prepareBMW();
        Storage storage = Storage.getInstance();
        storage.open();
        ICarDAO carDAO = storage.getCarDAO();
        carDAO.persist(bmw);
        storage.submit();
        /*execute test*/
        storage.open();
        carDAO = storage.getCarDAO();
        carDAO.remove(bmw);
        /*get result*/
        Car result = carDAO.get(bmw.getId());
        /*check result*/
        assertThat(result == null, is(true));
        storage.submit();
    }


}