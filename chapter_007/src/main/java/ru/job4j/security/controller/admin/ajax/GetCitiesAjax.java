package ru.job4j.security.controller.admin.ajax;

import ru.job4j.security.model.AdvancedUserSecurityStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;
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
        InputJsonObjectRepresentation country = gson.fromJson(req.getReader(), InputJsonObjectRepresentation.class);
        try {
            resp.setContentType("text/json");
            Set<String> cities = users.getCities(country.getCountry());
            OutputJsonObjectRepresentation[] output = new OutputJsonObjectRepresentation[cities.size()];
            int i = 0;
            for (String city : cities) {
                output[i] = new OutputJsonObjectRepresentation();
                output[i].setCity(city);
                i++;
            }

            String json = gson.toJson(output);
            PrintWriter writer = resp.getWriter();
            writer.append(json);
            writer.flush();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    /**
     * Class InputJsonObjectRepresentation объектное представление данных, полученных в формате json .
     * @author Timur Kapkaev (timur.kap@yandex.ru)
     * @version $ID$
     * @since 07.12.2017
     * */
    private static class InputJsonObjectRepresentation {
        /** Название страны. */
        private String country;
        /**
         * Возвращает название страны.
         * @return название страны
         * */
        private String getCountry() {
            return country;
        }
    }

    /**
     * Class OutputJsonObjectRepresentation объектное представление данных, отправляемых в формате json.
     * @author Timur Kapkaev (timur.kap@yandex.ru)
     * @version $ID$
     * @since 07.12.2017
     * */
    private static class OutputJsonObjectRepresentation {
        /** Название города. */
        private String city;
        /**
         * Здает название города.
         * @param city - название города
         * */
        private void setCity(String city) {
            this.city = city;
        }
    }
}
