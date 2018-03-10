package ru.job4j.cartrade.controller.exit;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

/**
 * Class MainSecurity описывает выход пользователя из системы.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 25.01.2018
 * */
@Controller
public class ExitController extends HttpServlet {
    /**
     * Завершает сессию.
     * @param session - http session
     * @return адрес страницы регистрации
     * */
//    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public String exit(HttpSession session) {
        session.invalidate();
        return "cartrade/authorization/authorization";
    }

}
