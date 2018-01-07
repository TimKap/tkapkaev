package ru.job4j.testtask.controller.filters;

import ru.job4j.testtask.controller.authorization.UserIdentification;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * Class AuthoentificationFilte описывает фильтр авторизации пользователя.
 * "@author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 06.01.2018
 * */
@WebFilter (
        urlPatterns = "*"
)
public class AuthorizationFilter implements Filter {
    /**
     * Инициализация фильтра.
     * @param filterConfig - коонфигурация фильтра.
     * */
    @Override
    public void init(FilterConfig filterConfig) { }
    /**
     * Фильтр авторизации.
     * @param servletRequest - фильтруемый запрос.
     * @param servletResponse - фильтруемый ответ
     * @throws IOException - ошибка ввода/вывода
     * @throws ServletException - ошибка сервлета
     * */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uri = req.getRequestURI();
        if (uri.contains("/authorization")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpSession session = req.getSession();
            UserIdentification identification = (UserIdentification) session.getAttribute("identification");
            if (identification == null) {
                HttpServletResponse resp = (HttpServletResponse) servletResponse;
                resp.sendRedirect(String.format("%s/testTask/authorization", req.getContextPath()));
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }
    /**
     * Разрушение фильтра.
     * */
    @Override
    public void destroy() { }
}
