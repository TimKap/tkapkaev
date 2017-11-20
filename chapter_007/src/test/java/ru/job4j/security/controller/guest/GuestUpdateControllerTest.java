package ru.job4j.security.controller.guest;

import org.junit.Test;
import org.mockito.Mockito;
import ru.job4j.security.controller.authorization.MockHttpSession;
import ru.job4j.security.model.AdvancedUser;
import ru.job4j.security.model.AdvancedUserSecurityStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class GuestUpdateController содержит тесты для UpdateUserController.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 20.11.2017
 * */
public class GuestUpdateControllerTest {
    /**
     * Тест для метода doPost.
     * @throws Exception - ошибка обновления гостя
     * */
    @Test
    public void doPost() throws Exception {
        /* подготовка базы данных. */
        AdvancedUserSecurityStore.getInstance().clearUsers();


        AdvancedUser user = new AdvancedUser.AdvancedUserBuilder().addLogin("test").addName("Test").
                addEmail("test@mail.ru").addRole("guest").addPassword("test_password").build();
        AdvancedUserSecurityStore.getInstance().insert(user);

        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("login", "test");

        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
        Mockito.when(mockRequest.getParameter("name")).thenReturn("update_test");
        Mockito.when(mockRequest.getParameter("email")).thenReturn("update_test@mail.ru");
        Mockito.when((mockRequest.getParameter("password"))).thenReturn("update_test_password");

        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);


        GuestUpdateController servlet = new GuestUpdateController();
        servlet.doPost(mockRequest, mockResponse);
        user = AdvancedUserSecurityStore.getInstance().searchByPrimaryKey(new AdvancedUser.Key("test"));

        assertThat(user.getLogin(), is("test"));
        assertThat(user.getName(), is("update_test"));
        assertThat(user.getEmail(), is("update_test@mail.ru"));
        assertThat(user.getRole(), is("guest"));
        assertThat(user.getPassword(), is("update_test_password"));
    }

}