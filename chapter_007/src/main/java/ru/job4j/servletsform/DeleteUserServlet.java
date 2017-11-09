package ru.job4j.servletsform;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Class DeleteUserServlet обеспечивает удаление пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 09.11.2017
 * */
public class DeleteUserServlet extends HttpServlet {
    /** взаимодействие с базой данных пользователей.*/
    private final AdvancedUserStore users = AdvancedUserStore.getInstance();

    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(DeleteUserServlet.class);

    /**
     * Удаляет пользователя.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        resp.setContentType("text/html");
        try {
            users.delete(login);
            PrintWriter writer = resp.getWriter();
            writer.append("DELETE request has been executed\r\n");
            writer.flush();
        } catch (SQLException e) {
            LOGGER.error(e);
            resp.sendError(500);
        }

    }
}
