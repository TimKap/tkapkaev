package ru.job4j.security.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.security.model.AdvancedUser;
import ru.job4j.security.model.AdvancedUserSecurityStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


/**
 * UserController описывает логику страницы пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 17.1.2017
 * */
public class UserController extends HttpServlet {

    /** база данных пользователей. */
    private final AdvancedUserSecurityStore users = AdvancedUserSecurityStore.getInstance();

    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    /**
     * Возвращает страницу пользователя.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login;
        synchronized (session) {
            login = session.getAttribute("login").toString();
        }

        try {
            AdvancedUser user = users.searchByPrimaryKey(new AdvancedUser.Key(login));
            user.insertUserToRequest(req);
            RequestDispatcher dispatcher = req.getRequestDispatcher(String.format("/WEB-INF/security/views/%s/%s.jsp", user.getRole(), user.getRole()));
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            LOGGER.error(e);
            resp.sendError(501);
        }
    }
}
