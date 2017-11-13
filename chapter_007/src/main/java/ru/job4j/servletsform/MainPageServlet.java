package ru.job4j.servletsform;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;



/**
 * Class MainPageServlet описывает главную страницу с пользователями.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 09.11.2017
 * */
public class MainPageServlet extends HttpServlet {

    /** взаимодействие с базой данных пользователей.*/
    private final AdvancedUserStore users = AdvancedUserStore.getInstance();

    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(MainPageServlet.class);

    /**
     * Возвращает  главную страницу.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            req.setAttribute("users", users.getAllUsers());
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        } catch (SQLException e) {
            LOGGER.error(e);
            resp.sendError(500);
        }

    }
}
