package ru.job4j.security.controller.filters;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.security.controller.authorization.UserIdentification;
import ru.job4j.security.model.AdvancedUserSecurityStore;

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
import java.sql.SQLException;
import java.util.Set;

/**
 * Class AuthoentificationFilte описывает фильтр аутентификации пользователя.
 * "@author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 17.11.2017
 * */
public class AuthoentificationFilter implements Filter {

    /** база данных пользователей. */
    private final AdvancedUserSecurityStore users = AdvancedUserSecurityStore.getInstance();
    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger(AuthorizationFilter.class);
    /**
     * Инициализация фильтра.
     * @param filterConfig - коонфигурация фильтра.
     * @throws ServletException - ошибка сервлета.
     * */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Фильтр аутентификации.
     * @param servletRequest - фильтруемый запрос.
     * @param servletResponse - фильтруемый ответ
     * @throws IOException - ошибка ввода/вывода
     * @throws ServletException - ошибка сервлета
     * */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        try {
            Set<String> availableRoles = users.getRoles();
            String uri = httpRequest.getRequestURI();
            if (!uriContainsRole(uri, availableRoles)) {
                /* страница не требует аутентификации */
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                /* страница, требующая аутентификацию */
                HttpSession session = httpRequest.getSession();
                UserIdentification identification = (UserIdentification) session.getAttribute("identification");
                if (identification != null) {
                    String role = identification.getRole();
                    if (uri.contains("/" + role)) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        httpResponse.sendRedirect(String.format("%s", ((HttpServletRequest) servletRequest).getContextPath()));
                    }
                } else {
                    httpResponse.sendRedirect(String.format("%s/authorization", httpRequest.getContextPath()));
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
            httpResponse.sendError(501);
        }

    }

    /**
     * Разрушение фильтра.
     * */
    @Override
    public void destroy() {

    }

    /**
     * Проверяет наличие роли в uri.
     * @param uri - uri
     * @param roles - set  of roles
     * @return true если uri содержит роль.
     * */
    private boolean uriContainsRole(String uri, Set<String> roles) {
        boolean result = false;
        for (String role : roles) {
            if (uri.contains("/" + role)) {
                result = true;
                break;
            }
        }
        return result;
    }
 }
