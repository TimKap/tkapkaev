package ru.job4j.security.controller.admin.ajax;

import ru.job4j.security.model.AdvancedUserSecurityStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.google.gson.Gson;

import java.io.PrintWriter;
import java.sql.SQLException;

import java.util.Set;
/**
 * Class GetRolesAjax описывает обработку ajax запроса на получение списка ролей.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 06.12.17
 * */
public class GetRolesAjax extends HttpServlet {
    /** база данных пользователей. */
    private final AdvancedUserSecurityStore users = AdvancedUserSecurityStore.getInstance();
    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(GetRolesAjax.class);

    /**
     * Возвращает список ролей в формате ajax.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            resp.setContentType("text/json");
            Set<String> roles = users.getRoles();
            OutputJsonObjectRepresentation[] output = new OutputJsonObjectRepresentation[roles.size()];
            int i = 0;
            for (String role : roles) {
                output[i] = new OutputJsonObjectRepresentation();
                output[i].setRole(role);
                i++;
            }
            Gson gson = new Gson();
            String json = gson.toJson(output);
            PrintWriter writer = resp.getWriter();
            writer.append(json);
            writer.flush();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
    /**
     * Class OutputJsonObjectRepresentation объектное представление данных, отправляемых в формате json.
     * @aгthor Timur Kapkaev (timur.kap@yandex.ru)
     * @version $ID$
     * @since 07.12.2017
     * */
    private static class OutputJsonObjectRepresentation {
        /** Название страны. */
        private String role;
        /**
         * Здает роль.
         * @param role - роль
         * */
        private void setRole(String role) {
            this.role = role;
        }
    }
}
