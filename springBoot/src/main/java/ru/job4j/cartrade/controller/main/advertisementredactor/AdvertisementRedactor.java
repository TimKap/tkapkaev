package ru.job4j.cartrade.controller.main.advertisementredactor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cartrade.controller.authorization.UserIdentification;
import ru.job4j.cartrade.model.car.Body;
import ru.job4j.cartrade.model.car.Car;
import ru.job4j.cartrade.model.car.Engine;
import ru.job4j.cartrade.model.photo.Photo;
import ru.job4j.cartrade.model.user.User;
import ru.job4j.cartrade.security.UserPrincipal;
import ru.job4j.cartrade.storage.service.AdvertisementService;

import java.io.IOException;

/**
 * Class AdvertisementRedactor описывает контроллер редактора объявлений.
 * @author Timur Kapkaev (tikmur.kap@yandex.ru)
 * @version $Id$
 * @since 27.02.2018
 * */
@Controller
public class AdvertisementRedactor {

    /** сервис объявлений. */
    @Autowired
    private AdvertisementService advertisementService;
    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(AdvertisementRedactor.class);
    /**
     * Возвращает страницу редактора объявлений.
     * @return  адрес редактора объявлений.
     * */
    @RequestMapping(value = "/advertisementredactor", method = RequestMethod.GET)
    public String showPage() {
        return "cartrade/main/advertisementredactor/advertisementredactor";
    }
    /**
     * Добавляет объявление.
     * @param file - файл с фотографией
     * @param model - модель автомобиля
     * @param bodyType - тип кузова
     * @param bodyColor - цвет кузова
     * @param eng - двигатель автомобиля
     * @param comment - комментарий
     * @return адрес главной страницы
     * */
    @RequestMapping(value = "/advertisementredactor", method = RequestMethod.POST, consumes = "multipart/form-data")
    public String addAdvertisement(@RequestParam("photo")MultipartFile file, @RequestParam String model, @RequestParam("bodytype") String bodyType,
                                   @RequestParam("bodycolor") String bodyColor, @RequestParam("engine") String eng, @RequestParam String comment) {

        try {
            Car car = new Car();
            car.setModel(model);
            Body body = new Body(bodyType, bodyColor);
            car.setBody(body);
            Engine engine = new Engine(eng);
            car.setEngine(engine);
            Photo photo = new Photo();
            photo.setFile(file.getBytes());
            car.getPhotos().add(photo);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
            User user = principal.getUser();
            advertisementService.addAdvertisement(new UserIdentification(user.getId(), null, user.getName(), user.getPassword()), car, comment);
        } catch (IOException e) {
            LOGGER.error(e);
        }

        return "redirect:/main";
    }
}
