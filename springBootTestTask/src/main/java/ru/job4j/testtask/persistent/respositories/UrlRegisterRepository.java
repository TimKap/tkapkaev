package ru.job4j.testtask.persistent.respositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.testtask.model.UrlRegister;

/**
 * Interface UrlRegisterRepository описывает интервейс регистра url.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * */
@Transactional
public interface UrlRegisterRepository extends CrudRepository<UrlRegister, Long> {

    /**
     * Возвращает RegisterURl по короткому url.
     * @param shortUrl - короткий url
     * @return UrlRegister
     * */
    UrlRegister findByShortUrl(String shortUrl);
}
