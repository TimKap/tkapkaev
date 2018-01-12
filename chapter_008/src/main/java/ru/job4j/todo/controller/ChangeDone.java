package ru.job4j.todo.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import ru.job4j.todo.model.Item;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

/**
 * Class ChangeDone обрабатывает ajax на изменение состояния выполнения задачи.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 11.01.2018
 * */
@WebServlet (
        urlPatterns = "/changeDone"
)
public class ChangeDone extends HttpServlet {
    /**
     * Обрабатывает ajax запрос на изменение состояния выполнения задачи.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Gson gson = new Gson();
        Item inputJson = gson.fromJson(req.getReader(), Item.class);
        try (SessionFactory hbFactroy = new Configuration().configure().buildSessionFactory()) {
            try (Session hbSession = hbFactroy.openSession()) {
                hbSession.beginTransaction();
                List<Item> items = hbSession.createQuery(String.format("FROM Item I WHERE I.id = %d", inputJson.getId())).list();
                items.get(0).setDone(inputJson.getDone());
                hbSession.update(items.get(0));
                hbSession.getTransaction().commit();
            }
        }
        String outputJson = gson.toJson(inputJson);
        PrintWriter writer = resp.getWriter();
        writer.append(outputJson);
        writer.flush();
    }
}
