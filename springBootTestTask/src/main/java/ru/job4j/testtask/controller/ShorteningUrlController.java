package ru.job4j.testtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.job4j.testtask.model.Account;
import ru.job4j.testtask.model.UrlRegister;
import ru.job4j.testtask.service.AccountService;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Class ShorteningUrlController описывает контроллер укорачивающий url..
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 15.03.2018
 * */
@Controller
public class ShorteningUrlController {
    /** base path. */
    private static final String BASE_PATH = "/redirect";
    /** server address.*/
    private static final String SERVER_ADDRESS = "http://localhost";
    /** account service. */
    @Autowired
    private AccountService accountService;

    /**
     * Открывает аккаунт.
     * @param accountId - id аккаунта
     * @return информация о зарегистрированном аккаунте
     * */
    @RequestMapping(value = "/account", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Map<String, String> openAccount(@RequestBody Map<String, String> accountId) {
        Account account = accountService.openAccount(accountId.get("AccountId"));
        HashMap<String, String> response = new HashMap<>();
        if (account != null) {
            response.put("success", "true");
            response.put("description", "new account has been created");
            response.put("password", account.getPassword());
        } else {
            response.put("success", "false");
            response.put("description", "Account already exist");
            response.put("password", "account with that ID already exists");
        }
        return response;
    }
    /**
     * Регистрация URL's.
     * @param registration - тело запроса регистрации
     * @param req - http запрос
     * @return сокращенная ссылка
     * */
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity<HashMap<String, String>> registrationUrl(@RequestBody Map<String, String> registration, HttpServletRequest req) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Account account = accountService.findAccountByLogin(login);
        String longUrl = registration.get("url");
        HttpStatus status = registration.get("redirectType") != null && registration.get("redirectType").equals("301") ? HttpStatus.MOVED_PERMANENTLY : HttpStatus.FOUND;
        UrlRegister urlRegister = accountService.registrationLongUrl(longUrl, Integer.valueOf(status.value()), account.getId(), BASE_PATH);
        HashMap<String, String> response = new HashMap<>();
        response.put("shortUrl", SERVER_ADDRESS + req.getContextPath() + urlRegister.getShortUrl());
        ResponseEntity<HashMap<String, String>> entity = new ResponseEntity<>(response, status);
        return entity;
    }

    /**
     * Возвращает статистику статистики.
     * @param accountId - id аккаунта
     * @return статистика
     * */
    @RequestMapping(value = "/statistic/{AccountId}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Long> accountStatistic(@PathVariable("AccountId")String accountId) {
        Map<String, Long> statistic = accountService.accountStatistic(accountId);
        return statistic;
    }
    /**
     * Перенаправляет запрос.
     * @param req - http запрос
     * @return ссылка на полный адрес
     * */
    @RequestMapping(value = BASE_PATH + "/**", method = RequestMethod.GET)
    public String redirect(HttpServletRequest req) {
        String shortUrl = req.getRequestURI().substring(req.getContextPath().length());
        String longUrl = accountService.getLongUrl(shortUrl);
        return "redirect:" + longUrl;
    }

    /**
     * Вовзращает страницу помощи.
     * @return help page
     * */
    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String helpPage() {
        return "help";
    }
}
