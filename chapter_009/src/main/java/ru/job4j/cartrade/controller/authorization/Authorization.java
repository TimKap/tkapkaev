package ru.job4j.cartrade.controller.authorization;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.cartrade.model.user.User;
import ru.job4j.cartrade.storage.Storage;
import ru.job4j.cartrade.storage.dao.IUserDAO;

import javax.servlet.http.HttpSession;

/**
 * Class Authorization описывает авторизацию пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 18.02.2018
 * */
@Controller
public class Authorization {
    /** хранилище cartrade. */
    private final Storage storage = Storage.getInstance();
    /**
     * Возращает страницу авторизации.
     * @return путь к странице авторизации
     * */
    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public String showPage() {
        return "cartrade/authorization/authorization";
    }
    /**
     * Авторизация пользователя.
     * @param session - http сессия
     * @param identification - параметры пользователя
     * @param error - ошибка регистрации
     * @return адрес страницы
     * */

    @RequestMapping(value = "/authorization", method = RequestMethod.POST)
    public String authorization(@ModelAttribute("userIdentification")UserIdentification identification, HttpSession session, Model error) {
        storage.open();
        IUserDAO userDAO = storage.getUserDAO();
        User user = userDAO.credential(identification.getLogin(), identification.getPassword());
        storage.submit();
         if (user != null) {
            synchronized (session) {
                if (session.getAttribute("identification") == null) {
                    identification.setId(user.getId());
                    session.setAttribute("identification", identification);
                }
            }
            return "redirect:main";
        } else {
            error.addAttribute("error", "Invalid login, password");
            return "cartrade/authorization/authorization";
        }
    }
}
