package ru.job4j.security.controller.filters;


import ru.job4j.security.controller.authorization.UserIdentification;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class AuthoentificationFilte описывает фильтр авторизации пользователя.
 * "@author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 17.11.2017
 * */

public class AuthorizationFilter implements Filter {


    /**
     * Инициализация фильтра.
     * @param filterConfig - коонфигурация фильтра.
     * @throws ServletException - ошибка сервлета.
     * */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    /**
     * Фильтр авторизации.
     * @param servletRequest - фильтруемый запрос.
     * @param servletResponse - фильтруемый ответ
     * @throws IOException - ошибка ввода/вывода
     * @throws ServletException - ошибка сервлета
     * */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String uri = httpRequest.getRequestURI();
        if (uri.contains("/authorization")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpSession session = httpRequest.getSession();
            UserIdentification identification = (UserIdentification) session.getAttribute("identification");
            if (identification == null) {
                HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
                httpResponse.sendRedirect(String.format("%s/authorization", httpRequest.getContextPath()));
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
