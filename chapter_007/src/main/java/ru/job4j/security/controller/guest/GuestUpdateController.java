package ru.job4j.security.controller.guest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.security.controller.authorization.UserIdentification;
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
 * Class GuestUpdateController описывает обновление гостя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 16.11.2017
 * */
public class GuestUpdateController extends HttpServlet {
    /** база данных пользователей. */
    private final AdvancedUserSecurityStore users = AdvancedUserSecurityStore.getInstance();

    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(GuestUpdateController.class);

    /**
     * Возвращает страницу обновления гостя.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserIdentification identification = (UserIdentification) session.getAttribute("identification");
        if (identification != null) {
            String login = identification.getLogin();
            try {
                AdvancedUser user = users.searchByPrimaryKey(new AdvancedUser.Key(login));
                user.insertUserToRequest(req);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/security/views/guest/guestUpdate.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException e) {
                LOGGER.error(e);
                resp.sendError(501);
            }
        } else {
            resp.sendRedirect(String.format("%s/authorization", req.getContextPath()));
        }
    }

    /**
     * Обновляет аккаунт польователя.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserIdentification identification = (UserIdentification) session.getAttribute("identification");
        if (identification != null) {
            String login = identification.getLogin();
            try {
                AdvancedUser user = users.searchByPrimaryKey(new AdvancedUser.Key(login));
                String name = req.getParameter("name");
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                user.modifUser(name, email, null, password);
                users.update(user);
                resp.sendRedirect(String.format("%s/", req.getContextPath()));
            } catch (SQLException e) {
                LOGGER.error(e);
                resp.sendError(501);
            }
        } else {
            resp.sendRedirect(String.format("%s/authorization", req.getContextPath()));
        }

    }

}
