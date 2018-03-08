package ru.job4j.cartrade.controller.main;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ru.job4j.cartrade.controller.authorization.UserIdentification;
import ru.job4j.cartrade.model.advertisement.Advertisement;
import ru.job4j.cartrade.model.photo.Photo;
import ru.job4j.cartrade.model.user.User;
import ru.job4j.cartrade.storage.service.AdvertisementService;

import java.util.Base64;
import java.util.List;

import ru.job4j.cartrade.security.UserPrincipal;
/**
 * Class MainPage Описывает контроллер главной страницы.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 18.02.2018
 * */
@Controller
public class MainPage {
    /** сервис объявлений. */
    @Autowired
    private AdvertisementService advertisementService;

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
        List<Advertisement> advertisements = advertisementService.getAdvertisements(filter, fValue);
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
        Photo photo = advertisementService.getAdvPhoto(advId);
        String photo64 = "";
        if (photo != null) {
            photo64 = Base64.getEncoder().encodeToString(photo.getFile());
        }
        return photo64;
    }
    /**
     * Изменяет статус объявления.
     * @param reqAdvertisement - запрашиваемое на обновление объявление
     * @return модифицированное объявление
     * */
    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Advertisement changeStatus(@RequestBody Advertisement reqAdvertisement) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        User user = principal.getUser();
        return advertisementService.changeStatus(new UserIdentification(user.getId(), null, user.getName(), user.getPassword()), reqAdvertisement);
    }
}
