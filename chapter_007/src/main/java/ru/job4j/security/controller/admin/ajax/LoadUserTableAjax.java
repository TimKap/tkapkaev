package ru.job4j.security.controller.admin.ajax;


import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.security.model.AdvancedUser;
import ru.job4j.security.model.AdvancedUserSecurityStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Class LoadUserTableAjax описывает обработку ajax запроса на получение списка пользоватедлей.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 06.12.17
 * */
public class LoadUserTableAjax extends HttpServlet {
    /** база данных пользователей. */
    private final AdvancedUserSecurityStore users = AdvancedUserSecurityStore.getInstance();

    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(LoadUserTableAjax.class);


    /**
     * Возвращает список пользователей в формате ajax.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/ajax");
        try {
            List<AdvancedUser> list = users.getAllUsers();
            StringBuilder json = new StringBuilder();
            json.append("[");
            Gson gson = new Gson();
            Iterator<AdvancedUser> iterator = list.iterator();
            if (iterator.hasNext()) {
                String jsonUser = gson.toJson(iterator.next());
                json.append(jsonUser);
                while (iterator.hasNext()) {
                    jsonUser = ", " + gson.toJson(iterator.next());
                    json.append(jsonUser + " ");
                }
            }
            json.append("]");

            PrintWriter writer = resp.getWriter();
            writer.append(json);
            writer.flush();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
