package ru.job4j.cartrade.controller.main;

import ru.job4j.cartrade.model.advertisement.Advertisement;
import ru.job4j.cartrade.storage.dao.IAdvertisementDAO;
import ru.job4j.cartrade.storage.Storage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 * Class MainPage описывает контроллер главной страницы.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 25.01.2018
 * */
public class MainPage extends HttpServlet {

    /**
     * Возвращает  главную страницу.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/cartrade/view/main/main.jsp");
        Storage storage = new Storage();
        storage.open();
        IAdvertisementDAO advertisementDAO = storage.getAdvertisementDAO();
        List<Advertisement> advertisements = advertisementDAO.getAll();
        storage.submit();
        req.setAttribute("advertisements", advertisements);
        dispatcher.forward(req, resp);
    }
}
