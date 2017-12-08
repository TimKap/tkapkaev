package ru.job4j.security.controller.admin.ajax;

import com.google.gson.Gson;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import java.util.Set;

import ru.job4j.security.model.AdvancedUserSecurityStore;

/**
 * Class GetCountriesAjax описывает обработку ajax запроса на получение списка стран.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 07.12.17
 * */
public class GetCountriesAjax extends HttpServlet {
    /** база данных пользователей. */
    private final AdvancedUserSecurityStore users = AdvancedUserSecurityStore.getInstance();
    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(GetCountriesAjax.class);


    /**
     * Возвращает список стран в формате ajax.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("text/json");

            Set<String> countries = users.getCountries();
            OutputJsonObjectRepresentation[] output = new OutputJsonObjectRepresentation[countries.size()];
            int i = 0;
            for (String country : users.getCountries()) {
                output[i] = new OutputJsonObjectRepresentation();
                output[i].setCountry(country);
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
        private String country;
        /**
         * Здает название страны.
         * @param country - название страны
         * */
        private void setCountry(String country) {
            this.country = country;
        }
    }
}
