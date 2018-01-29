package ru.job4j.todo.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import ru.job4j.todo.model.Item;

import ru.job4j.todo.storage.ItemDAO;
import ru.job4j.todo.storage.Storage;

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
        Storage storage = Storage.getInstance();
        storage.open();
        ItemDAO itemDAO = storage.getItemDAO();
        Item item = itemDAO.get(inputJson.getId());
        item.setDone(inputJson.getDone());
        storage.submit();
        String outputJson = gson.toJson(item);
        PrintWriter writer = resp.getWriter();
        writer.append(outputJson);
        writer.flush();
    }
}
