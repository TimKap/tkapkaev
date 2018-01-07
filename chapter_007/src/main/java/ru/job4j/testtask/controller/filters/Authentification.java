package ru.job4j.testtask.controller.filters;


import ru.job4j.testtask.controller.authorization.UserIdentification;
import ru.job4j.testtask.model.entities.Role;
import ru.job4j.testtask.model.storage.dao.exception.OperationException;
import ru.job4j.testtask.model.storage.dao.implementations.MusicShopStorageSingleTone;
import ru.job4j.testtask.model.storage.dao.interfaces.IMusicShopStorage;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import ru.job4j.testtask.model.storage.dao.interfaces.IRoleDAO;

/**
 * Class AuthentificationFilter описывает фильтр аутентификация.
 * @author timur Kapkaev (timur.kap@yabndex.ru)
 * @version $ID$
 * @since 06.01.2017
 * */
@WebFilter(
        urlPatterns = "*"
)
public class Authentification implements Filter {
    /** хранилище музыкального магазина.*/
    private final IMusicShopStorage storage = MusicShopStorageSingleTone.getStorage();
    /** логгер. */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Инициализация фильтра.
     * @param filterConfig - коонфигурация фильтра.
     * */
    @Override
    public void init(FilterConfig filterConfig) {

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
        IRoleDAO rolesDAO = storage.getRoleDAO();
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        try {
            List<Role> roles = rolesDAO.getAllElements();
            String uri = req.getRequestURI();
            HttpSession session = req.getSession();
            UserIdentification identification = (UserIdentification) session.getAttribute("identification");

            if (identification == null) {
                if (!uri.contains("/authorization")) {
                    resp.sendRedirect(String.format("%s/testTask/authorization", req.getContextPath()));
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
               if (uriContainsRole(uri, roles)) {
                   String role = identification.getRole();
                   if (uri.contains("/" + role)) {
                       filterChain.doFilter(servletRequest, servletResponse);
                   } else {
                       resp.sendRedirect(String.format("%s/testTask", req.getContextPath()));
                   }
               } else {
                   filterChain.doFilter(servletRequest, servletResponse);
               }
            }
        } catch (OperationException e) {
            LOGGER.error(e);
            resp.sendError(501);

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
    private boolean uriContainsRole(String uri, List<Role> roles) {
        boolean result = false;
        for (Role role : roles) {
            if (uri.contains("/" + role.getRole())) {
                result = true;
                break;
            }
        }
        return result;
    }

}
