package ru.job4j.cartrade.controller.main;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import ru.job4j.cartrade.controller.authorization.UserIdentification;
import ru.job4j.cartrade.model.advertisement.Advertisement;
import ru.job4j.cartrade.model.photo.Photo;
import ru.job4j.cartrade.model.user.User;
import ru.job4j.cartrade.storage.Storage;
import ru.job4j.cartrade.storage.dao.IAdvertisementDAO;

import java.util.Base64;
import java.util.Iterator;
import java.util.List;

/**
 * Class MainPage Описывает контроллер главной страницы.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 18.02.2018
 * */
@Controller
public class MainPage {
    /** хранилище объявлений. */
    private final Storage storage = Storage.getInstance();
    /**
     * Возвращает главную страницу.
     * @return адрес страницы
     * */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String showPage() {
        return "cartrade/main/main";
    }

    /**
     * Возвращает объявления в формате JSON.
     * @param filter - критерий, по которому выводятся объявления
     * @param fValue - значение фильтра
     * @return список объявлений в формате JSON.
     * */
    @RequestMapping(value = "/getAdvertisements", method = RequestMethod.GET)
    @ResponseBody
    public List<Advertisement> getAdvertisements(@RequestParam String filter, @RequestParam String fValue) {
        storage.open();
        IAdvertisementDAO advertisementDAO = storage.getAdvertisementDAO();
        DispatchPattern dispatcher = new DispatchPattern(advertisementDAO);
        dispatcher.init();
        List<Advertisement> advertisements = dispatcher.extract(filter, fValue);
        storage.submit();
        return advertisements;
    }

    /**
     * Вовзращает картинку в формате 64.
     * @param advId - id объявления, которому принадлежит картинка
     * @return картинка в теле ответа
     * */
    @RequestMapping(value = "/getImage", method = RequestMethod.GET)
    @ResponseBody
    public String getImage(@RequestParam long advId) {
        Storage storage = Storage.getInstance();
        storage.open();
        IAdvertisementDAO advertisementDAO = storage.getAdvertisementDAO();
        Advertisement advertisement = advertisementDAO.get(advId);
        Iterator<Photo> iterator = advertisement.getProduct().getPhotos().iterator();
        byte[] photo = null;
        if (iterator.hasNext()) {
            photo = iterator.next().getFile();
        }
        storage.submit();
        String photo64 = Base64.getEncoder().encodeToString(photo);
        return photo64;
    }
    /**
     * Изменяет статус объявления.
     * @param identification - идентификация пользователя
     * @param advertisement - объявление статус которого необходимо изменить
     * @return модифицированное объявление
     * */
    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Advertisement changeStatus(@SessionAttribute("identification")UserIdentification identification, @RequestBody Advertisement advertisement) {
        Storage storage = Storage.getInstance();
        storage.open();
        IAdvertisementDAO advertisementDAO = storage.getAdvertisementDAO();
        Advertisement tmpAdv = advertisementDAO.get(advertisement.getId());
        User tmpUser = tmpAdv.getSeller();

        if (identification.getId() == tmpUser.getId()) {
            tmpAdv.setSold(advertisement.getSold());
        }
        advertisement.setSold(tmpAdv.getSold());
        storage.submit();
        return advertisement;
    }
}
