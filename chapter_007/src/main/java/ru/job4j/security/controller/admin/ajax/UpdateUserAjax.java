package ru.job4j.security.controller.admin.ajax;

import ru.job4j.security.model.AdvancedUser;
import ru.job4j.security.model.AdvancedUserSecurityStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.google.gson.Gson;


/**
 * Class UpdateUserAjax описывает обработку ajax запроса на обновление пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 06.12.2017
 */
public class UpdateUserAjax extends HttpServlet {
    /** база данных пользователей. */
    private final AdvancedUserSecurityStore users = AdvancedUserSecurityStore.getInstance();
    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(UpdateUserAjax.class);

    /**
     * Обарабатывает ajax запрос на обновление пользователя.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        AdvancedUser user = gson.fromJson(req.getReader(), AdvancedUser.class);
        try {
            AdvancedUser updatedUser = users.searchByPrimaryKey(new AdvancedUser.Key(user.getLogin()));
            updatedUser.modifUser(user.getName(), user.getEmail(), user.getRole(), user.getPassword(), user.getCity(), user.getCountry());
            users.update(updatedUser);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

}
