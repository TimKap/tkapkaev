package ru.job4j.cartrade.controller.filter;

import ru.job4j.cartrade.controller.authorization.UserIdentification;

import javax.servlet.FilterConfig;
import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class AuthorizationFilter описывает фильтр авторизации пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 06.01.2018
 * */
public class AuthorizationFilter implements Filter {
    /**
     * Инициализация фильтра.
     * @param filterConfig - коонфигурация фильтра.
     * */
    @Override
    public void init(FilterConfig filterConfig) {

    }

    /**
     * Фильтр авторизации.
     * @param servletRequest - фильтруемый запрос.
     * @param servletResponse - фильтруемый ответ
     * @throws IOException - ошибка ввода/вывода
     * @throws ServletException - ошибка сервлета
     * */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (req.getRequestURI().contains("/authorization")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            HttpSession session = req.getSession();
            UserIdentification identification = (UserIdentification) session.getAttribute("identification");
            if (identification == null) {
                resp.sendRedirect(String.format("%s/authorization", req.getContextPath()));
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    /**
     * Разрушение фильтра.
     * */
    @Override
    public void destroy() {

    }
}
