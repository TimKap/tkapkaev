package ru.job4j.firstservlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Class EchoServlet описывает сервлет, возвращающий эхо-овет.
 * @author Timur Kpakaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 03.07.2017
 * */
public class  EchoServlet extends HttpServlet {
    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(EchoServlet.class);

    /**
     * Обработка пользовательского запроса.
     * @param req - запрос
     * @param resp - ответ
     * @throws ServletException - исключение при обработке запроса
     * @throws IOException - исключение ввода/вывода
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append("Hello World");
        writer.flush();
    }
}
