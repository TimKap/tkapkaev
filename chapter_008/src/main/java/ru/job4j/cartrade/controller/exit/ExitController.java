package ru.job4j.cartrade.controller.exit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class MainSecurity описывает выход пользователя из системы.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 25.01.2018
 * */

public class ExitController extends HttpServlet {

    /**
     * Завершает сессию.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(String.format("%s/authorization", req.getContextPath()));
    }

}
