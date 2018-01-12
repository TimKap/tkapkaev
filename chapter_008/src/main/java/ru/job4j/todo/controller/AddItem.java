package ru.job4j.todo.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import com.google.gson.Gson;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import ru.job4j.todo.model.Item;

/**
 * Class AddItem описывает сервлет, добавляющий задание задание.
 * @author Timur kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 09.01.2017
 * */
@WebServlet (
        urlPatterns = "/addItem"
)
public class AddItem extends HttpServlet {
    /**
     * Обрабатывает ajax запрос на добавление задачи.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Gson gson = new Gson();
        InputJsonObject  inputJson = gson.fromJson(req.getReader(), InputJsonObject.class);
        Item item;
        try (SessionFactory hbFactory = new Configuration().configure().buildSessionFactory()) {
            try (Session hbSession = hbFactory.openSession()) {
                item = new Item();
                item.setDescription(inputJson.getDescription());
                item.setCreated(new Timestamp(System.currentTimeMillis()));
                hbSession.getTransaction();
                hbSession.save(item);
            }
        }
        String outputJson = gson.toJson(item);
        PrintWriter writer = resp.getWriter();
        writer.append(outputJson);
        writer.flush();
    }
}
/**
 *
 * */
class InputJsonObject {


    /** описанеи item. */
    private String description;

    /**
     * Возвращает описание.
     * @return описание
     * */
    public String getDescription() {
        return description;
    }
}