package ru.job4j.cartrade.controller.main;

import com.google.gson.Gson;
import ru.job4j.cartrade.controller.authorization.UserIdentification;
import ru.job4j.cartrade.model.advertisement.Advertisement;
import ru.job4j.cartrade.model.user.User;
import ru.job4j.cartrade.storage.Storage;
import ru.job4j.cartrade.storage.dao.IAdvertisementDAO;
import ru.job4j.cartrade.storage.dao.IUserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class MainPage описывает контроллер изменения состояния объявления.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 25.01.2018
 * */
public class ChangeStatus extends HttpServlet {

    /**
     * Изменяет состояние объявления.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UserIdentification identification = (UserIdentification) session.getAttribute("identification");
        Storage storage = new Storage();
        storage.open();
        IUserDAO userDAO = storage.getUserDAO();
        User user = userDAO.get(identification.getId());

        Gson gson = new Gson();
        Advertisement advertisement = gson.fromJson(req.getReader(), Advertisement.class);
        IAdvertisementDAO advertisementDAO = storage.getAdvertisementDAO();

        Advertisement tmpAdv = advertisementDAO.get(advertisement.getId());
        User tmpUser = tmpAdv.getSeller();
        if (user.equals(tmpUser)) {
            tmpAdv.setSold(advertisement.getSold());
        }
        advertisement.setSold(tmpAdv.getSold());
        storage.submit();

        String json = gson.toJson(advertisement);
        PrintWriter writer = resp.getWriter();
        writer.append(json);
        writer.flush();
    }
}
