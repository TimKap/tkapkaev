package ru.job4j.cartrade.controller.main;

import ru.job4j.cartrade.model.advertisement.Advertisement;
import ru.job4j.cartrade.model.car.Body;
import ru.job4j.cartrade.model.car.Car;
import ru.job4j.cartrade.model.car.Engine;
import ru.job4j.cartrade.model.photo.Photo;
import ru.job4j.cartrade.model.user.User;


/**
 * Class CartradeTestData описывает держателя тестовых данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 14.03.2018
 * */
public class CartradeTestData {
    /**
     * Подготавливает пользователя.
     * @return пользователя.
     * */
    public static User prepareUser() {
        User user = new User();
        user.setName("Tom");
        user.setPassword("tom");
        user.setId(1L);
        return user;
    }
    /**
     * Подготавливает автомобиль.
     * @return автомобиль
     * */
    public static Car prepareCar() {
        Car car = new Car();
        car.setModel("Lamborgine");
        car.setBody(new Body("Sport", "Black"));
        car.setEngine(new Engine("BiTurbo"));
        car.setId(1L);
        Photo photo = preparePhoto();
        car.getPhotos().add(photo);
        return car;
    }

    /**
     * Подготавливает фотографию.
     * @return фотография
     * */
    public static Photo preparePhoto() {
        Photo photo = new Photo();
        photo.setId(1L);
        photo.setFile(new byte[]{1, 2, 3});
        return photo;
    }
    /**
     * Подготавливает объявление.
     * @return объявление
     * */
    public static Advertisement prepareAdvertisement() {
        Advertisement advertisement = new Advertisement();
        Car car = prepareCar();
        User user = prepareUser();
        car.getOwners().add(user);
        user.getCars().add(car);
        advertisement.setSeller(user);
        advertisement.setProduct(car);
        advertisement.setId(1L);
        advertisement.setSold(false);
        advertisement.getComments().add("Hey, I'm new Car");
        return advertisement;
    }
    /**
     * Подготавливает запрашиваемое на обноление объявление.
     * @return запрашиваемое на обновление объявление
     * */
    public static Advertisement prepareReqAdvertisement() {
        Advertisement reqAdvertisement = new Advertisement();
        reqAdvertisement.setId(1L);
        return reqAdvertisement;
    }

}
