package ru.job4j.security.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.security.model.AdvancedUser;
import ru.job4j.security.model.AdvancedUserSecurityStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

/**
 * UpdateUserController описывает обновление пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 18.1.2017
 * */
public class UpdateUserController extends HttpServlet {
    /** база данных пользователей. */
    private final AdvancedUserSecurityStore users = AdvancedUserSecurityStore.getInstance();
    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(InsertUserController.class);

    /**
     * Обновляет пользователя.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String role = req.getParameter("role");
        String password = req.getParameter("password");
        try {
            if (!login.equals("")) {
                AdvancedUser user = users.searchByPrimaryKey(new AdvancedUser.Key(login));
                user.modifUser(name, email, role, password);
                users.update(user);
            }
            resp.sendRedirect(String.format("%s/admin/userRedactor", req.getContextPath()));
        } catch (SQLException e) {
            LOGGER.error(e);
            resp.sendError(501);
        }
    }
}
