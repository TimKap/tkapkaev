package ru.job4j.testtask.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import ru.job4j.testtask.model.Account;
import ru.job4j.testtask.model.UrlRegister;
import ru.job4j.testtask.persistent.respositories.AccountRepository;
import ru.job4j.testtask.persistent.respositories.UrlRegisterRepository;


import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.Random;



/**
 * Class AccountService описывает сервис аккаунтов.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 15.03.2018
 * */
@Service
@Transactional (propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AccountService {

    /** accountRepository. */
    @Autowired
    private AccountRepository accountRepository;

    /** registerUrlRepository. */
    @Autowired
    private UrlRegisterRepository urlRegisterRepository;

    /** набор символов для чисел по основанию 62. */
    private static final  String SYMBOLS62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /** основание числе. */
    private static final Long NUMBER_BASE = 62L;
    /**
     * Регистрация аккаунта.
     * @param id - id регистрируемого аккакунта.
     * @return аккакунт (null, если аккаунт уже существует)
     * */
    public Account openAccount(String id) {
        Account account = accountRepository.findOne(id);
        if (account == null) {
            account = new Account();
            account.setId(id);
            account.setLogin(id);
            account.setPassword(passwordGenerator());
            accountRepository.save(account);
        } else {
            account = null;
        }
        return account;
    }

    /**
     * Вовзращает аккаунт по логину.
     * @param login - логин
     * @return аккаунт
     * */
    public Account findAccountByLogin(String login) {
        return accountRepository.findBylogin(login);
    }

    /**
     * Регистрация url.
     * @param longUrl - длинный Url.
     * @param status - статус
     * @param accountId - id аккаунта
     * @param basePath - база сокращенного пути
     * @return UrlRegister
     * */
    public UrlRegister registrationLongUrl(String longUrl, Integer status, String accountId, String basePath) {
        Account account = accountRepository.findOne(accountId);
        Set<UrlRegister> urlRegisters = account.getUrlRegisters();
        UrlRegister urlRegister = new UrlRegister();
        urlRegister.setLongUrl(longUrl);
        if (urlRegisters.contains(urlRegister)) {
            for (UrlRegister register : urlRegisters) {
                if (register.equals(urlRegister)) {
                    urlRegister = register;
                    urlRegister.setStatus(status);
                    break;
                }
            }
        } else {
            urlRegister.setStatus(status);
            urlRegisterRepository.save(urlRegister);
            urlRegister.setShortUrl(shortUrlGenerator(basePath, urlRegister.getId()));
            account.getUrlRegisters().add(urlRegister);
        }
        return urlRegister;
    }



    /**
     * Генератор 8-символьного пароля.
     * @return пароль из 8-ми символов
     * */
    private String passwordGenerator() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        String charset = "0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
        for (int i = 0; i < 8; i++) {
            char symbol = charset.charAt(random.nextInt(charset.length()));
            builder.append(symbol);
        }
        return builder.toString();
    }

    /**
     * Возвращает статистику аккаунта.
     * @param accountId - id  аккаунта
     * @return статистика аккаунта
     * */
    public Map<String, Long> accountStatistic(String accountId) {
        Account account = accountRepository.findOne(accountId);
        HashMap<String, Long> statistic = new HashMap<>();
        if (account != null) {
            Set<UrlRegister> urlRegisters = account.getUrlRegisters();
            for (UrlRegister urlRegister : urlRegisters) {
                statistic.put(urlRegister.getLongUrl(), urlRegister.getRedirects());
            }
        }
        return statistic;
    }
    /**
     * Возвращает полный адрес по сокращенному.
     * @param shortUrl - сокращенный url
     * @return полный Url.
     * */
    public String getLongUrl(String shortUrl) {
        UrlRegister urlRegister = urlRegisterRepository.findByShortUrl(shortUrl);
        long redirects = urlRegister.getRedirects();
        redirects++;
        urlRegister.setRedirects(redirects);
        String longUrl = urlRegister.getLongUrl();
        return longUrl;
    }

    /**
     * Генератор короткого адреса.
     * @param basePath - базовый путь сокращенного url.
     * @param id - id длинного url
     * @return short Url.
     * */
    private String shortUrlGenerator(String basePath, long id) {
        ArrayList<Integer> digits = new ArrayList<>();
        do {
            int mod = (int) (id % NUMBER_BASE);
            digits.add(mod);
            id /= NUMBER_BASE;
        } while (id > NUMBER_BASE);
        if (id != 0) {
            digits.add((int) id);
        }
        StringBuilder builder = new StringBuilder(basePath + "/");
        Collections.reverse(digits);
        for (Integer digit : digits) {
            builder.append(SYMBOLS62.charAt(digit));
        }
        return builder.toString();
    }



}
