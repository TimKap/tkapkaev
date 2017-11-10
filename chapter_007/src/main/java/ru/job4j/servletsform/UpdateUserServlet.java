package ru.job4j.servletsform;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.firstservlets.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Class UpdateUserServlet обеспечивает обновление пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 09.11.2017
 * */
public class UpdateUserServlet extends HttpServlet {
    /** взаимодействие с базой данных пользователей.*/
    private final AdvancedUserStore users = AdvancedUserStore.getInstance();

    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(UpdateUserServlet.class);

    /**
     * Обновляет пользователя.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user = new User(name, login, email, null);
        try {
            users.update(user);
            PrintWriter writer = resp.getWriter();
            writer.append("User has been updated\r\n");
            writer.flush();
        } catch (SQLException e) {
            LOGGER.error(e);
            resp.sendError(500);
        }

    }
}
