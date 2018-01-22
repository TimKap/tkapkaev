package ru.job4j.security.controller.admin.ajax;


import ru.job4j.security.model.AdvancedUserSecurityStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.google.gson.Gson;
/**
 * Class GetCitiesAjax описывает обработку ajax запроса на получение списка городов в соответствующей стране.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 07.12.17
 * */
public class GetCitiesAjax extends HttpServlet {
    /** база данных пользователей. */
    private final AdvancedUserSecurityStore users = AdvancedUserSecurityStore.getInstance();

    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Возвращает список городов в соответтсвующей стране в формате ajax.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        Reader reader = req.getReader();
        String[] country = gson.fromJson(reader, String[].class);
        try {
            resp.setContentType("text/json");
            Set<String> cities = users.getCities(country[0]);
            String json = gson.toJson(cities);
            PrintWriter writer = resp.getWriter();
            writer.append(json);
            writer.flush();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
