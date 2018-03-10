package ru.job4j.cartrade.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.cartrade.controller.authorization.UserIdentification;
import ru.job4j.cartrade.model.advertisement.Advertisement;
import ru.job4j.cartrade.model.car.Car;
import ru.job4j.cartrade.model.photo.Photo;
import ru.job4j.cartrade.model.user.User;
import ru.job4j.cartrade.storage.repositories.AdvertisementRepository;
import ru.job4j.cartrade.storage.repositories.CarRepository;
import ru.job4j.cartrade.storage.repositories.PhotoRepository;
import ru.job4j.cartrade.storage.repositories.UserRepository;

import java.util.Iterator;
import java.util.List;

/**
 * Class AdvertisementService описывает слой persistence сервиса площадки продаж автомобилей.
 * @author Timur Kapkaev (tmur.kap@yandex.ru)
 * @version $Id$
 * @since 06.03.2018
 * */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AdvertisementService {
    /** Репозиторий автомобилей. */
    @Autowired
    private CarRepository carRepository;
    /** Репозиторий пользователей. */
    @Autowired
    private UserRepository userRepository;
    /** Репозиторий объявлений. */
    @Autowired
    private AdvertisementRepository advertisementRepository;
    /** Репозиторий фотографий. */
    @Autowired
    private PhotoRepository photoRepository;

    /**
     * Возвращает пользователя с заданным именем и паролем.
     * @param name - имя пользователя.
     * @param password - пароль пользователя
     * @return пользователь
     * */
    public User credential(String name, String password) {
        return userRepository.findByNameAndPassword(name, password);
    }
    /**
     * Возвращает объявления по заданному параметру.
     * @param type - тип параметра
     * @param value - значение параметра
     * @return список объявлений.
     * */
    public List<Advertisement> getAdvertisements(String type, String value) {
        AdvertisementDispatchPattern dispatcher = new AdvertisementDispatchPattern(advertisementRepository);
        dispatcher.init();
        return dispatcher.extract(type, value);
    }

    /**
     * Возвращает фотографию объявления.
     * @param advId - id объявления.
     * @return фотография объявления
     * */
    public Photo getAdvPhoto(long advId) {
        Advertisement adv = advertisementRepository.findOne(advId);
        Iterator<Photo> iterator = adv.getProduct().getPhotos().iterator();
        Photo photo = null;
        if (iterator.hasNext()) {
            photo = iterator.next();
        }
        return photo;
    }

    /**
     * Изменяет статус объявления.
     * @param identification - идентификация пользователя
     * @param reqAdvertisement - запрашиваемое на обновление объявление
     * @return модифицированное объявление.
     * */
    public Advertisement changeStatus(UserIdentification identification, Advertisement reqAdvertisement)  {
        Advertisement tmpAdv = advertisementRepository.findOne(reqAdvertisement.getId());
        User tmpUser = tmpAdv.getSeller();

        if (identification.getId() == tmpUser.getId()) {
            tmpAdv.setSold(reqAdvertisement.getSold());
        }
        reqAdvertisement.setSold(tmpAdv.getSold());
        return reqAdvertisement;
    }
    /**
     * Добавляет объявление.
     * @param identification - идентификация пользователя.
     * @param car - продаваемый1 автомобиль
     * @param comment - комментарий к объявлению
     * @return добавленное объявление
     * */
    public Advertisement addAdvertisement(UserIdentification identification, Car car, String comment) {
        carRepository.save(car);
        User user = userRepository.findOne(identification.getId());
        user.getCars().add(car);
        car.getOwners().add(user);
        Advertisement advertisement = new Advertisement();
        advertisement.setProduct(car);
        advertisement.setSeller(user);
        advertisement.getComments().add(comment);
        advertisementRepository.save(advertisement);
        return advertisement;
    }

    /**
     * Возвращает пользователя по его имени.
     * @param username - имя пользователя.
     * @return пользователь.
     * */
    public User getUser(String username) {
        return userRepository.findByName(username);
    }

}
