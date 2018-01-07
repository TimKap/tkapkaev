package ru.job4j.testtask.controller.authorization;


import ru.job4j.testtask.model.entities.User;
import ru.job4j.testtask.model.storage.dao.exception.OperationException;
import ru.job4j.testtask.model.storage.dao.implementations.MusicShopStorageSingleTone;
import ru.job4j.testtask.model.storage.dao.interfaces.IMusicShopStorage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import ru.job4j.testtask.model.storage.dao.interfaces.IUserDAO;

/**
 * Class Authorization описывает авторизацию.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 04.01.2017
 * */
@WebServlet (
        urlPatterns = "/testTask/authorization",
        loadOnStartup = 1
)


public class Authorization extends HttpServlet {
    /** хранилище музыкального магазина. */
    private final IMusicShopStorage storage = MusicShopStorageSingleTone.getStorage();
    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(Authorization.class);
    /**
     * Возвращает страницу авторизации.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/testtask/views/authorization/authorization.jsp");
       dispatcher.forward(req, resp);
    }
    /**
     * Авторизация пользователя.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            IUserDAO userDAO = storage.getUserDAO();
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            User user = userDAO.isCredential(login, password);
            if (user != null) {
                HttpSession session = req.getSession();
                synchronized (session) {
                    UserIdentification identification = new UserIdentification(user.getRole().getRole(), user.getName(), user.getPassword());
                    session.setAttribute("identification", identification);
                }
            }
            resp.sendRedirect(String.format("%s/testTask", req.getContextPath()));
        } catch (OperationException e) {
            LOGGER.error(e);
            resp.sendError(501);
        }

    }
}
