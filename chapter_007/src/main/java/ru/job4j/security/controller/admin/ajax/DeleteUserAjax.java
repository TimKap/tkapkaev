package ru.job4j.security.controller.admin.ajax;

import com.google.gson.Gson;
import ru.job4j.security.model.AdvancedUser;
import ru.job4j.security.model.AdvancedUserSecurityStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


import java.sql.SQLException;




/**
 * Class DeleteUSerAjax описывает обработку ajax запроса на удаление пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 18.1.2017
 * */
public class DeleteUserAjax extends HttpServlet {
    /** база данных пользователей. */
    private final AdvancedUserSecurityStore users = AdvancedUserSecurityStore.getInstance();
    /** логгер. */
    private  static final Logger  LOGGER = LogManager.getLogger(DeleteUserAjax.class);


    /**
     * Обарабатывает ajax запрос на удаление пользователя.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Gson gson = new Gson();
        AdvancedUser user = gson.fromJson(req.getReader(), AdvancedUser.class);
        try {
            users.delete(user);
        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }

}
