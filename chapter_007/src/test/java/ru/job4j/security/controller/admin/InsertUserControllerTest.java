package ru.job4j.security.controller.admin;

import org.junit.Test;
import org.mockito.Mockito;
import ru.job4j.security.model.AdvancedUser;
import ru.job4j.security.model.AdvancedUserSecurityStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class InsertUserControllerTest содержит тест для InsertUserController.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 20.11.2017
 * */
public class InsertUserControllerTest {

    /**
     * Тест для метода doPost.
     * @throws Exception - ошибка добавления пользователя
     * */
    @Test
    public void whenDoPostThenGetNewUserInDatabase() throws Exception {

        /* подготовка базы данных (удаление всех элементов). */
        AdvancedUserSecurityStore.getInstance().clearUsers();

        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);

        String expectedLogin = "test";
        String expectedName = "Test";
        String expectedEmail = "test@mail.ru";
        String expectedRole = "admin";
        String expectedPassword = "test_password";
        Mockito.when(mockRequest.getParameter("login")).thenReturn(expectedLogin);
        Mockito.when(mockRequest.getParameter("name")).thenReturn(expectedName);
        Mockito.when(mockRequest.getParameter("email")).thenReturn(expectedEmail);
        Mockito.when(mockRequest.getParameter("role")).thenReturn(expectedRole);
        Mockito.when(mockRequest.getParameter("password")).thenReturn(expectedPassword);

        InsertUserController servlet = new InsertUserController();
        servlet.doPost(mockRequest, mockResponse);

        AdvancedUser user = AdvancedUserSecurityStore.getInstance().searchByPrimaryKey(new AdvancedUser.Key(expectedLogin));

        assertThat(user.getLogin(), is(expectedLogin));
        assertThat(user.getName(), is(expectedName));
        assertThat(user.getEmail(), is(expectedEmail));
        assertThat(user.getPassword(), is(expectedPassword));
        assertThat(user.getRole(), is(expectedRole));
    }
}