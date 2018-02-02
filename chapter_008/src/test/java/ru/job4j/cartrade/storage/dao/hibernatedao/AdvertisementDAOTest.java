package ru.job4j.cartrade.storage.dao.hibernatedao;

import org.junit.Test;
import ru.job4j.cartrade.model.advertisement.Advertisement;
import ru.job4j.cartrade.model.car.Car;
import ru.job4j.cartrade.model.user.User;
import ru.job4j.cartrade.storage.Storage;
import ru.job4j.cartrade.storage.dao.IAdvertisementDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class AdvertisementDAOTest содержит тесты для AdvertisementDAO.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 01.02.2018
 * */
public class AdvertisementDAOTest {
    /** всмпомогательный объект.*/
    private final Auxiliary auxiliary = new Auxiliary();

    /**
     * Тест для get и add.
     * */
    @Test
    public void whenAddAdvertisementThenGetAdvertisement() {
        /*prepare test*/
        /*execute test*/
        Car bmw = auxiliary.prepareBMW();
        User tom = auxiliary.prepareTom();
        bmw.getOwners().add(tom);
        tom.getCars().add(bmw);
        Storage storage = Storage.getInstance();
        storage.open();
        storage.getCarDAO().persist(bmw);
        storage.getUserDAO().persist(tom);

        Advertisement advertisement = auxiliary.prepareAdvertisement(tom, bmw);

        IAdvertisementDAO advertisementDAO = storage.getAdvertisementDAO();
        advertisementDAO.persist(advertisement);
        storage.submit();
        /*get result*/
        storage.open();
        advertisementDAO = storage.getAdvertisementDAO();
        Advertisement result = advertisementDAO.get(advertisement.getId());
        /*check result*/
        assertThat(result, is(advertisement));
        storage.submit();
    }

    /**
     * Тест для update.
     * */
    @Test
    public void whenUpdateAdvertisementThenGetModifiedAdvertisement() {
        /*prepare test*/
        Car bmw = auxiliary.prepareBMW();
        User tom = auxiliary.prepareTom();
        bmw.getOwners().add(tom);
        tom.getCars().add(bmw);
        Storage storage = Storage.getInstance();
        storage.open();
        storage.getCarDAO().persist(bmw);
        storage.getUserDAO().persist(tom);

        Advertisement advertisement = auxiliary.prepareAdvertisement(tom, bmw);

        IAdvertisementDAO advertisementDAO = storage.getAdvertisementDAO();
        advertisementDAO.persist(advertisement);
        storage.submit();
        /*execute test*/
        advertisement.getComments().add("This is second comment");
        storage.open();
        advertisementDAO = storage.getAdvertisementDAO();
        advertisementDAO.update(advertisement);
        /*get result*/
        Advertisement result = advertisementDAO.get(advertisement.getId());
        /*check result*/
        assertThat(result, is(advertisement));
        storage.submit();
    }

    /**
     * Тест для getAll.
     * */
    @Test
    public void whenGetAllThenGetAllAdvertisements() {
        /*prepare test*/

        User tom = auxiliary.prepareTom();
        Car bmw = auxiliary.prepareBMW();

        Storage storage = Storage.getInstance();
        storage.open();
        storage.getUserDAO().persist(tom);
        storage.getCarDAO().persist(bmw);
        tom.getCars().add(bmw);
        bmw.getOwners().add(tom);

        User ann = auxiliary.prepareAnn();
        Car audi = auxiliary.prepareAudi();

        storage.getUserDAO().persist(ann);
        storage.getCarDAO().persist(audi);
        ann.getCars().add(audi);
        audi.getOwners().add(ann);

        Advertisement advertisement1 = auxiliary.prepareAdvertisement(tom, bmw);
        Advertisement advertisement2 = auxiliary.prepareAdvertisement(ann, audi);
        List<Advertisement> expected = new ArrayList<>(Arrays.asList(advertisement1, advertisement2));
        IAdvertisementDAO advertisementDAO = storage.getAdvertisementDAO();
        advertisementDAO.persist(advertisement1);
        advertisementDAO.persist(advertisement2);
        storage.submit();
        /*execute test*/
        /*get result*/
        storage.open();
        advertisementDAO = storage.getAdvertisementDAO();
        List<Advertisement> result = advertisementDAO.getAll();
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
        User tom = auxiliary.prepareTom();
        bmw.getOwners().add(tom);
        tom.getCars().add(bmw);
        Storage storage = Storage.getInstance();
        storage.open();
        storage.getCarDAO().persist(bmw);
        storage.getUserDAO().persist(tom);

        Advertisement advertisement = auxiliary.prepareAdvertisement(tom, bmw);

        IAdvertisementDAO advertisementDAO = storage.getAdvertisementDAO();
        advertisementDAO.persist(advertisement);
        storage.submit();
        /*execute test*/
        storage.open();
        advertisementDAO = storage.getAdvertisementDAO();
        advertisementDAO.remove(advertisement);
        /*get result*/
        Advertisement result = advertisementDAO.get(advertisement.getId());
        /*check result*/
        assertThat(result == null, is(true));
        storage.submit();
    }

    /**
     * Тест для getByModel.
     * */
    @Test
    public void whenGetByModelThenGetAdvertisement() {
        /* prepare test*/
        Storage storage = Storage.getInstance();
        storage.open();
        IAdvertisementDAO advertisementDAO = storage.getAdvertisementDAO();
        List<Advertisement> advertisements = advertisementDAO.getAll();
        for (Advertisement advertisement : advertisements) {
            advertisementDAO.remove(advertisement);
        }

        Car bmw = auxiliary.prepareBMW();
        User tom = auxiliary.prepareTom();
        bmw.getOwners().add(tom);
        tom.getCars().add(bmw);
        storage.getCarDAO().persist(bmw);
        storage.getUserDAO().persist(tom);

        Advertisement advertisement = auxiliary.prepareAdvertisement(tom, bmw);
        advertisementDAO.persist(advertisement);
        storage.submit();
        /*execute test*/
        /*get result*/
        storage.open();
        advertisementDAO = storage.getAdvertisementDAO();
        List<Advertisement> result = advertisementDAO.getByModel(bmw.getModel());
        /*check result*/
        assertThat(result.contains(advertisement), is(true));
        storage.submit();
    }
    /**
     * Тест для getBySellerName.
     * */
    @Test
    public void whenGetBySellerNameThenGetAdvertisement() {
        /* prepare test*/
        Storage storage = Storage.getInstance();
        storage.open();
        IAdvertisementDAO advertisementDAO = storage.getAdvertisementDAO();
        List<Advertisement> advertisements = advertisementDAO.getAll();
        for (Advertisement advertisement : advertisements) {
            advertisementDAO.remove(advertisement);
        }

        Car bmw = auxiliary.prepareBMW();
        User tom = auxiliary.prepareTom();
        bmw.getOwners().add(tom);
        tom.getCars().add(bmw);
        storage.getCarDAO().persist(bmw);
        storage.getUserDAO().persist(tom);

        Advertisement advertisement = auxiliary.prepareAdvertisement(tom, bmw);
        advertisementDAO.persist(advertisement);
        storage.submit();
        /*execute test*/
        /*get result*/
        storage.open();
        advertisementDAO = storage.getAdvertisementDAO();
        List<Advertisement> result = advertisementDAO.getBySellerName(tom.getName());
        /*check result*/
        assertThat(result.contains(advertisement), is(true));
        storage.submit();
    }

    /**
     * Тест для getByStatus.
     * */
    @Test
    public void whenGetByStatusThenGetAdvertisementWithSoldStatus() {
                /* prepare test*/
        Storage storage = Storage.getInstance();
        storage.open();
        IAdvertisementDAO advertisementDAO = storage.getAdvertisementDAO();
        List<Advertisement> advertisements = advertisementDAO.getAll();
        for (Advertisement advertisement : advertisements) {
            advertisementDAO.remove(advertisement);
        }

        Car bmw = auxiliary.prepareBMW();
        User tom = auxiliary.prepareTom();
        bmw.getOwners().add(tom);
        tom.getCars().add(bmw);
        storage.getCarDAO().persist(bmw);
        storage.getUserDAO().persist(tom);

        Advertisement advertisement = auxiliary.prepareAdvertisement(tom, bmw);
        advertisementDAO.persist(advertisement);
        advertisement.setSold(true);
        storage.submit();
        /*execute test*/
        /*get result*/
        storage.open();
        advertisementDAO = storage.getAdvertisementDAO();
        List<Advertisement> result = advertisementDAO.getByStatus(true);
        /*check result*/
        assertThat(result.contains(advertisement), is(true));
        storage.submit();

    }

}