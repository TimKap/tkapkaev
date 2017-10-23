package ru.jib4j.testtask.webextractor;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;

/**
 * Class WEBExtractor описывает извлечение вакансий с WEB ресурса.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 15.10.2017
 * @param <E> - тип извлекаемых данных с веб-страницы
 */
public abstract class WEBExtractor<E> {
    /** формат адреса i-й страницы. */
    private String addressFormatIwebPage;

    /**
     * Конструктор класса WEBExtractor.
     * @param addressFormatIwebPage - формат адреса i-й страницы
     * */
    public WEBExtractor(String addressFormatIwebPage) {
        this.addressFormatIwebPage = addressFormatIwebPage;
    }

    /**
     * Конструктор класса WEBExtractor.
     * */
    public WEBExtractor() {
    }
    /**
     * Задает формат адреса i-й страницы.
     * @param addressFormatIwebPage - адрес траницы.
     * */
    public void setAddressFormatIwebPage(String addressFormatIwebPage) {
        this.addressFormatIwebPage = addressFormatIwebPage;
    }

    /**
     * Возвращает формат адреса i-й страницы.
     * @return бформат адреса i-й страницы
     * */
    public String getAddressFormatIwebPage() {
        return addressFormatIwebPage;
    }
    /**
     * Возвращает представление  веб-страницы.
     * @param webAddress - адрес страницы
     * @return представление веб-страницы
     * @throws IOException - ошибка создания документа
     * */
    public Document webPageRepresentation(String webAddress) throws IOException {
        return Jsoup.connect(webAddress).get();
    }

    /**
     * Возвращает представление веб-страницы с соответствующим номером относительно базового адреса.
     * @param page - номер веб-страницы
     * @return представление веб-страницы
     * @throws IOException - ошибка создания документа
     * */
    public Document webPageRepresentation(int page) throws IOException {
        return webPageRepresentation(String.format(addressFormatIwebPage, page));
    }

    /**
     * Возвращает вакансии со страницы.
     * @param webPageRepresentation - представление веб-страницы
     * @return список данных извлеченных с веб-страницы
     * */
    public abstract List<E> extract(Document webPageRepresentation);

}
