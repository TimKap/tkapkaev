package ru.job4j.cartrade.controller.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.job4j.cartrade.storage.service.AdvertisementService;


/**
 * Class Authorization описывает авторизацию пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 18.02.2018
 * */
@Controller
public class Authorization {

    /** сервис объявлений. */
    @Autowired
    private AdvertisementService advertisementService;


    /**
     * Возращает страницу авторизации.
     * @return путь к странице авторизации
     * */
    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public String showPage() {
        return "cartrade/authorization/authorization";
    }
}
