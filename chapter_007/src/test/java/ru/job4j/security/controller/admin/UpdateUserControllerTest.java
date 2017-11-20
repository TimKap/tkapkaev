package ru.job4j.security.controller.admin;

import org.junit.Test;
import org.mockito.Mockito;

import ru.job4j.security.model.AdvancedUser;
import ru.job4j.security.model.AdvancedUserSecurityStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class UpdateUserControllerTest содержит тесты для UpdateUserController.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 20.11.2017
 * */
public class UpdateUserControllerTest {

    /**
     * Тест для метода dopPost.
     * @throws Exception - ошибка обновления пользователя.
     * */
    @Test
    public void  whenDoPostThenGetUpdatedUSer() throws Exception {

        /* подготовка базы данных (удаление всех элементов). */
       AdvancedUserSecurityStore.getInstance().clearUsers();


        String loginOfUpdatedElement = "test";
        AdvancedUser deletedUser = new AdvancedUser.AdvancedUserBuilder().addLogin(loginOfUpdatedElement).addName("Test").
                addEmail("test@mail.ru").addRole("admin").addPassword("test_password").build();
        AdvancedUserSecurityStore.getInstance().insert(deletedUser);

        /* подготовка теста */
        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);


        /* тест обновления пользователя.*/

        String updateName = "updateTest";
        String updateEmail = "updatetest@mail.ru";
        String updateRole = "guest";
        String updatePassword = "update_test_password";
        Mockito.when(mockRequest.getParameter("login")).thenReturn(loginOfUpdatedElement);
        Mockito.when(mockRequest.getParameter("name")).thenReturn(updateName);
        Mockito.when(mockRequest.getParameter("email")).thenReturn(updateEmail);
        Mockito.when(mockRequest.getParameter("role")).thenReturn(updateRole);
        Mockito.when(mockRequest.getParameter("password")).thenReturn(updatePassword);

        UpdateUserController updateServlet = new UpdateUserController();
        updateServlet.doPost(mockRequest, mockResponse);

        AdvancedUser user = AdvancedUserSecurityStore.getInstance().searchByPrimaryKey(new AdvancedUser.Key(loginOfUpdatedElement));

        assertThat(user.getName(), is(updateName));
        assertThat(user.getEmail(), is(updateEmail));
        assertThat(user.getPassword(), is(updatePassword));
        assertThat(user.getRole(), is(updateRole));

    }

}