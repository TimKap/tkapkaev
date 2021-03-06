package ru.job4j.security.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class MainSecurity описывает выход пользователя из системы.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 17.11.2017
 * */
public class ExitController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(String.format("%s/authorization", req.getContextPath()));
    }

}
