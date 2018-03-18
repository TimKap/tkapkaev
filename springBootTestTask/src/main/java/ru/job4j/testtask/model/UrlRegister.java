package ru.job4j.testtask.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Class UrlRegister описывает модель регистра URL.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 16.03.2018
 * */
@Entity
@Table(name = "urlRegisters")
public class UrlRegister {
    /** id url регистра. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /** длинный url. */
    @Column(name = "longUrl")
    private String longUrl;
    /** сокращенный url. */
    @Column(name = "shortUrl")
    private String shortUrl;

    /** статус. */
    @Column (name = "status")
    private Integer status;

    /** redirects. */
    @Column (name = "redirects")
    private long redirects;

    /**
     * Конструктор UrlRegister.
     * */
    public UrlRegister() {

    }
    /**
     * Возвращает id.
     * @return id
     * */
    public long getId() {
        return id;
    }

    /**
     * Задает id.
     * @param id - id
     * */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Вовзращает длинный url.
     * @return длинный url
     * */
    public String getLongUrl() {
        return longUrl;
    }

    /**
     * Задает длинный url.
     * @param longUrl - длинный url
     * */
    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    /**
     * Возвращает укороченный url.
     * @return укороченный url
     * */
    public String getShortUrl() {
        return shortUrl;
    }

    /**
     * Задает укороченный url.
     * @param shortUrl - укороченный url.
     * */
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    /**
     * Возвращает статус.
     * @return статус
     * */
    public Integer getStatus() {
        return status;
    }

    /**
     * Задает статус.
     * @param status - статус
     * */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Вовзращает количество редиректов.
     * @return количество редиректов
     * */
    public long getRedirects() {
        return redirects;
    }

    /**
     * Задает количество редиректов.
     * @param redirects - количество редиректов.
     * */
    public void setRedirects(long redirects) {
        this.redirects = redirects;
    }

    /**
     * Проверяет равенство объектов.
     * @param o - объект с которым сравнивается текущий объект
     * @return true, если объекты равны.
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UrlRegister that = (UrlRegister) o;

        return longUrl != null ? longUrl.equals(that.longUrl) : that.longUrl == null;
    }

    /**
     * Хэщ-функция.
     * @return хеш-код
     * */
    @Override
    public int hashCode() {
        return longUrl != null ? longUrl.hashCode() : 0;
    }
}
