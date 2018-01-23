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

import ru.job4j.todo.model.Item;
import ru.job4j.todo.storage.ItemDAO;
import ru.job4j.todo.storage.Storage;

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
        String[]  descriptions = gson.fromJson(req.getReader(), String[].class);

        Item item = new Item();
        item.setDescription(descriptions[0]);
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        Storage storage = new Storage();
        storage.open();
        ItemDAO itemDAO = storage.getItemDAO();
        itemDAO.persist(item);
        storage.submit();

        String outputJson = gson.toJson(item);
        PrintWriter writer = resp.getWriter();
        writer.append(outputJson);
        writer.flush();
    }
}
