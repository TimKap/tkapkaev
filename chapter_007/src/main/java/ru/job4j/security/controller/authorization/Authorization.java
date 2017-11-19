package ru.job4j.security.controller.authorization;

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
 * Class Authorization описывает авторизацию.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 16.11.2017
 * */
public class Authorization extends HttpServlet {

    /** взаимодействие с базой данных пользователей. */
    private final AdvancedUserSecurityStore users = AdvancedUserSecurityStore.getInstance();

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
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/security/views/authorization/authorization.jsp");
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
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            AdvancedUser user = users.credentialUser(login, password);
            if (user != null) {
                HttpSession session = req.getSession();
                synchronized (session) {
                    session.setAttribute("login", user.getLogin());
                    session.setAttribute("role", user.getRole());
                }
                resp.sendRedirect(String.format("%s/", req.getContextPath()));
            } else {
                req.setAttribute("error", "Invalid user login and password");
                doGet(req, resp);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            resp.sendError(501);
        }
    }
}
