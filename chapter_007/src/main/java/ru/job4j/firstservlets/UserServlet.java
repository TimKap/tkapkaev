package ru.job4j.firstservlets;

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
 * Class UserServlet описывает сервлет пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 04.11.2017
 * */
public class UserServlet extends HttpServlet {

    /** взаимодействие с базой данных пользователей.*/
    private final UserStore users = UserStore.getInstance();

    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(UserServlet.class);
    /**
     * Возвращает запрашиваемого пользователя.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        PrintWriter writer = resp.getWriter();
        try {
            User user = users.getUser(login);
            if (user != null) {
                writer.append(user.stringForm());
            } else {
                writer.append("User not found");
            }
            writer.flush();
        } catch (SQLException e) {
            resp.sendError(500);
            LOGGER.error(e);
        }

    }

    /**
     * Добавляет пользователя.
     * @param req - запрос
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        User user = new User(name, login, email, null);
        resp.setContentType("test/html");
        try {
            PrintWriter writer = resp.getWriter();
            if (users.insert(user)) {
                writer.append(String.format("Added new user Name: %s,  Login: %s, E-mail: %s added", name, login, email));
            } else {
                writer.append("User already exist\r\n");
            }
            writer.flush();
        } catch (SQLException e) {
            LOGGER.error(e);
            resp.sendError(500);
        }

    }


    /**
     * Удаляет пользователя.
     * @param req - запрос
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     */
    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        resp.setContentType("text/html");
        try {
            users.delete(login);
            PrintWriter writer = resp.getWriter();
            writer.append("DELETE request has been executed\r\n");
            writer.flush();
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(500);
        }

    }

    /**
     * Обновляет пользователя или создает нового.
     * @param req - апрос
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user = new User(name, login, email, null);
        try {
            users.update(user);
            PrintWriter writer = resp.getWriter();
            writer.append("User has benn updated\r\n");
            writer.flush();
        } catch (SQLException e) {
            LOGGER.error(e);
            resp.sendError(500);
        }
    }

    /**
     * Разрушение сервлета.
     * */
    @Override
    public void destroy() {
        try {
            users.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
