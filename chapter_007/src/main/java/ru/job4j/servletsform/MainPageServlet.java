package ru.job4j.servletsform;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.firstservlets.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


/**
 * Class MainPageServlet описывает главную страницу с пользователями.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 09.11.2017
 * */
public class MainPageServlet extends HttpServlet {

    /** взаимодействие с базой данных пользователей.*/
    private final AdvancedUserStore users = AdvancedUserStore.getInstance();

    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(MainPageServlet.class);

    /**
     * Возвращает  главную страницу.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder usersTable = new StringBuilder();
        resp.setContentType("text/html");
        int i = 0;
        try {
            for (User user : users.getAllUsers()) {
                i++;
                usersTable.append("<tr><td>" + i + ". " + user.stringForm() + "</td></tr>");
            }

            PrintWriter writer = resp.getWriter();
            writer.append("<!DOCTYPE html>"
                    +
                    "<html lang=\"en\">"
                    +
                    "<head>"
                    +
                    "    <meta charset=\"UTF-8\">"
                    +
                    "    <title>Title</title>"
                    +
                    "</head>"
                    +
                    "<body>"
                    +
                    "<form>"
                    +
                    "Name: <input type='text' name='name'>"
                    +
                    "Login: <input type='text' name='login'>"
                    +
                    "e-mail: <input type='text' name='email'> "
                    +
                    "<p>"
                    +
                    "<input type=\"submit\" value=\"Add \" formaction=\"" + req.getContextPath() + "/mainPage/insertUser\" formmethod=\"post\">"
                    +
                    "<input type=\"submit\" value=\"Delete\" formaction=\""  + req.getContextPath() + "/mainPage/deleteUser\" formmethod=\"post\">"
                    +
                    "<input type=\"submit\" value=\"Update\" formaction=\"" + req.getContextPath() + "/mainPage/updateUser\" formmethod=\"post\"></p>"
                    +
                    "</form>"
                    +
                    "<table>"
                    +
                    usersTable.toString()
                    +
                    "</table>"
                    +
                    "</body>"
                    +
                    "</html>");
            writer.flush();


        } catch (SQLException e) {
            LOGGER.error(e);
            resp.sendError(500);
        }

    }
}
