package ru.job4j.cartrade.storage.dao.hibernatedao;

import ru.job4j.cartrade.model.advertisement.Advertisement;
import ru.job4j.cartrade.model.car.Body;
import ru.job4j.cartrade.model.car.Car;
import ru.job4j.cartrade.model.car.Engine;
import ru.job4j.cartrade.model.photo.Photo;
import ru.job4j.cartrade.model.user.User;

/**
 * Class Auxiliary является вспомогательным классом к тестам.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 01.02.2018
 * */
class Auxiliary {
    /**
     * Подготавливает объект автомобиля BMW.
     * @return BMW
     * */
    final Car prepareBMW() {
        Car bmw = new Car();
        bmw.setModel("BMW");
        Body universal = new Body("Universal", "Black");
        bmw.setBody(universal);
        Engine biTurbo = new Engine("BiTurbo");
        bmw.setEngine(biTurbo);
        return bmw;
    }
    /**
     * Подготавливает объект пользователя Tom.
     * @return Tom
     * */
    final User prepareTom() {
        User tom = new User();
        tom.setName("Tom");
        tom.setSurname("Garison");
        tom.setPassword("tom");
        return tom;
    }

    /**
     * Подготавливает объект автомобиля Audi.
     * @return BMW
     * */
    final Car prepareAudi() {
        Car audi = new Car();
        audi.setModel("Audi");
        Body sedan = new Body("Sedan", "Black");
        audi.setBody(sedan);
        Engine biTurbo = new Engine("BiTurbo");
        audi.setEngine(biTurbo);
        return audi;
    }
    /**
     * Подготавливает объект пользователя Ann.
     * @return Tom
     * */
    final User prepareAnn() {
        User ann = new User();
        ann.setName("Ann");
        ann.setSurname("Anderson");
        ann.setPassword("ann");
        return ann;
    }
    /**
     * Подготавливает объект фотографии.
     * @return фотографию
     * */
    final Photo preparePhoto() {
        Photo photo = new Photo();
        byte[] file = new byte[] {1, 2, 3, 4, 5};
        photo.setFile(file);
        return photo;
    }
    /**
     * Подготавливает объект объявления.
     * @param seller - продавец
     * @param product - продаваемый товар
     * @return объявление.
     * */
    final Advertisement prepareAdvertisement(User seller, Car product) {
        Advertisement advertisement = new Advertisement();
        advertisement.setSeller(seller);
        advertisement.setProduct(product);
        advertisement.getComments().add("Hello world");
        return advertisement;
    }
}
