package ru.job4j.todo.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import ru.job4j.todo.model.Item;

import com.google.gson.Gson;
import ru.job4j.todo.storage.ItemDAO;
import ru.job4j.todo.storage.Storage;


/**
 * Возвращает items.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 10.01.2017
 * */
@WebServlet (
        urlPatterns = "/getItems"
)
public class GetItems extends HttpServlet {
    /**
     * Возвращает items.
     * @param req - запрос пользователя
     * @param resp - ответ
     * @throws ServletException - сервлет исключение
     * @throws IOException - ошибка ввода/вывода
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        Storage storage = new Storage();
        storage.open();
        ItemDAO itemDAO = storage.getItemDAO();
        List<Item> items = itemDAO.getAll();
        storage.submit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        PrintWriter writer = resp.getWriter();
        writer.append(json);
        writer.flush();
    }
}
