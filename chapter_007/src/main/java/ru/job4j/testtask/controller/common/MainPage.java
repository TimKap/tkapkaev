package ru.job4j.testtask.controller.common;

import ru.job4j.testtask.controller.authorization.UserIdentification;
import ru.job4j.testtask.model.entities.User;
import ru.job4j.testtask.model.storage.dao.exception.OperationException;
import ru.job4j.testtask.model.storage.dao.implementations.MusicShopStorageSingleTone;
import ru.job4j.testtask.model.storage.dao.interfaces.IMusicShopStorage;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import ru.job4j.testtask.model.storage.dao.interfaces.IUserDAO;

import java.io.IOException;


/**
 * Class MainPage описывает класс сервлет главной страницы.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 06.01.2018
 * */
@WebServlet (
        urlPatterns = "/testTask",
        loadOnStartup = 2
)
public class MainPage extends HttpServlet {
    /** хранилище музыкального магазина. */
    private final IMusicShopStorage storage = MusicShopStorageSingleTone.getStorage();
    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(MainPage.class);

    /**
     * Возвращает  главную страницу.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        UserIdentification identification = (UserIdentification) session.getAttribute("identification");
        String login = identification.getLogin();
        String password = identification.getPassword();
        IUserDAO userDAO = storage.getUserDAO();
        try {
            User user = userDAO.isCredential(login, password);
            req.setAttribute("login", user.getName());
            req.setAttribute("surname", user.getSurname());
            req.setAttribute("password", user.getPassword());
            req.setAttribute("role", user.getRole());
            req.setAttribute("address", user.getAddress());
            RequestDispatcher dispatcher = req.getRequestDispatcher(String.format("/WEB-INF/testtask/views/roles/%s/%s.jsp", identification.getRole().toLowerCase(), identification.getRole().toLowerCase()));
            dispatcher.forward(req, resp);
        } catch (OperationException e) {
            LOGGER.error(e);
            resp.sendError(501);
        }
    }

}
