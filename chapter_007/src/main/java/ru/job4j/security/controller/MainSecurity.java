package ru.job4j.security.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



/**
 * Class MainSecurity описывает  главную страницу.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 16.11.2017
 * */
public class MainSecurity extends HttpServlet {

    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(MainSecurity.class);

    /**
     * Возвращает  главную страницу.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String role;
        synchronized (session) {
            role = (String) session.getAttribute("role");
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(String.format("%s", role));
        dispatcher.forward(req, resp);

    }


}
