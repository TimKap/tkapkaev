package ru.job4j.cartrade.controller.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.cartrade.model.advertisement.Advertisement;

import ru.job4j.cartrade.storage.Storage;
import ru.job4j.cartrade.storage.dao.IAdvertisementDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 * Class GetAdvertisement описывает контроллер возвращающий список объявлений.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 29.01.2018
 * */
public class GetAdvertisements extends HttpServlet {
    /**
     * Возвращает  список объявлений.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            Storage storage = Storage.getInstance();
            storage.open();
            IAdvertisementDAO advertisementDAO = storage.getAdvertisementDAO();
            DispatchPattern dispatcher = new DispatchPattern(advertisementDAO);
            dispatcher.init();
            List<Advertisement> advertisements = dispatcher.extract(req.getParameter("filter"), req.getParameter("fValue"));
            String json = gson.toJson(advertisements);
            PrintWriter writer = resp.getWriter();
            writer.append(json);
            writer.flush();
            storage.submit();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
