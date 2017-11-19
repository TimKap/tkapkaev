package ru.job4j.security.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.security.model.AdvancedUser;
import ru.job4j.security.model.AdvancedUserSecurityStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * UserRedactorController описывает редактор пользователей базы данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 17.1.2017
 * */
public class UserRedactorController extends HttpServlet {
    /** база данных пользователей. */
   private final AdvancedUserSecurityStore users = AdvancedUserSecurityStore.getInstance();
    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(UserRedactorController.class);

    /**
     * Возвращает страницу редактора пользователей.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<AdvancedUser> list = users.getAllUsers();
            req.setAttribute("users", list);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/security/views/admin/userRedactor.jsp");
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            LOGGER.error(e);
            resp.sendError(501);
        }
    }
}
