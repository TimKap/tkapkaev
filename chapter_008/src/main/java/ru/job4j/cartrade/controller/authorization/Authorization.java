package ru.job4j.cartrade.controller.authorization;

import ru.job4j.cartrade.model.user.User;
import ru.job4j.cartrade.storage.Storage;
import ru.job4j.cartrade.storage.dao.IUserDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import java.io.IOException;
/**
 * Class Authorization описывает авторизацию.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 24.01.2018
 * */
public class Authorization extends HttpServlet {

    /**
     * Возвращает страницу авторизации.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/cartrade/view/authorization/authorization.jsp");
        dispatcher.forward(req, resp);
    }
    /**

    /**
     * Возвращает страницу авторизации.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Storage storage = Storage.getInstance();
        storage.open();
        IUserDAO userDAO = storage.getUserDAO();
        User user = userDAO.credential(login, password);
        storage.submit();
        if (user != null) {
            HttpSession session = req.getSession();
            synchronized (session) {
                if (session.getAttribute("identification") == null) {
                    session.setAttribute("identification", new UserIdentification(user.getId(), null, user.getName(), user.getPassword()));
                }
            }
            resp.sendRedirect(String.format("%s/main", req.getContextPath()));

        } else {
            req.setAttribute("error", "Invalid login, password");
            doGet(req, resp);
        }
    }
}
