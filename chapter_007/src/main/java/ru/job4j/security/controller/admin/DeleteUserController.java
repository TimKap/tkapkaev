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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * InsertController описывает добавление пользователя в базу данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 18.1.2017
 * */
public class DeleteUserController extends HttpServlet {

    /** база данных пользователей. */
    private final AdvancedUserSecurityStore users = AdvancedUserSecurityStore.getInstance();
    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(InsertUserController.class);

    /**
     * Удаление пользователя.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String sessionLogin;
        synchronized (session) {
            sessionLogin = session.getAttribute("login").toString();
        }
        String deletedLogin = req.getParameter("login");
        try {
            if (!deletedLogin.equals("")) {
                AdvancedUser user = new AdvancedUser.AdvancedUserBuilder().addLogin(deletedLogin).build();
                users.delete(user);
            }
            if (deletedLogin.equals(sessionLogin)) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/exit");
                dispatcher.forward(req, resp);
            } else {
                resp.sendRedirect(String.format("%s/admin/userRedactor", req.getContextPath()));
            }

        } catch (SQLException e) {
            LOGGER.error(e);
            resp.sendError(501);
        }

    }
}
