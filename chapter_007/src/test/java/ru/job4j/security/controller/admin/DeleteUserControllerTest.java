package ru.job4j.security.controller.admin;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.mockito.Mockito;
import ru.job4j.security.controller.authorization.MockHttpSession;
import ru.job4j.security.model.AdvancedUser;
import ru.job4j.security.model.AdvancedUserSecurityStore;


/**
 * Class DeleteUserControllerTest содержит тест для DeleteUserController.
 * @author Timur Kpakaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 20.11.2017
 */
public class DeleteUserControllerTest {

    /**
     * Тест для метода doPost.
     * @throws Exception - ошибка удаления элемента
     * */
    @Test
    public void whenDoPostThenDeleteElementFromDatabase() throws Exception {

        /* подготовка базы данных. */
        AdvancedUserSecurityStore.getInstance().clearUsers();
        String loginOfDeletedElement = "test";
        AdvancedUser deletedUser = new AdvancedUser.AdvancedUserBuilder().addLogin(loginOfDeletedElement).addName("Test").
                addEmail("test@mail.ru").addRole("admin").addPassword("test_password").build();
        AdvancedUserSecurityStore.getInstance().insert(deletedUser);

        /* подготовка теста */
        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
        Mockito.when(mockRequest.getParameter("login")).thenReturn(loginOfDeletedElement);

        HttpSession session = new MockHttpSession();
        session.setAttribute("login", "login");
        Mockito.when(mockRequest.getSession()).thenReturn(session);



        /* Тест операции удаления */
        DeleteUserController deleteServlet = new DeleteUserController();
        deleteServlet.doPost(mockRequest, mockResponse);

        AdvancedUser user = AdvancedUserSecurityStore.getInstance().searchByPrimaryKey(new AdvancedUser.Key(loginOfDeletedElement));

        assertThat(user == null, is(true));

    }
}