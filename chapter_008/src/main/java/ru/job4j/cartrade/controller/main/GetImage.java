package ru.job4j.cartrade.controller.main;

import ru.job4j.cartrade.model.advertisement.Advertisement;
import ru.job4j.cartrade.model.photo.Photo;
import ru.job4j.cartrade.storage.Storage;
import ru.job4j.cartrade.storage.dao.IAdvertisementDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Iterator;


/**
 * Class GetImage описывает контроллер возвращающий список объявлений.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 29.01.2018
 * */
public class GetImage extends HttpServlet {

    /**
     * Возвращает фотографию.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("advId"));
        Storage storage = Storage.getInstance();
        storage.open();
        IAdvertisementDAO advertisementDAO = storage.getAdvertisementDAO();
        Advertisement advertisement = advertisementDAO.get(id);
        Iterator<Photo> iterator = advertisement.getProduct().getPhotos().iterator();
        byte[] photo = null;
        if (iterator.hasNext()) {
            photo = iterator.next().getFile();
        }
        storage.submit();
        String photo64 = Base64.getEncoder().encodeToString(photo);
        PrintWriter writer = resp.getWriter();
        writer.append(photo64);
        writer.flush();
    }
}
