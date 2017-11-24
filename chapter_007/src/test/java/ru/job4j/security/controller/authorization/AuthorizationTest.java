package ru.job4j.security.controller.authorization;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.mockito.Mockito;
import ru.job4j.security.model.AdvancedUser;
import ru.job4j.security.model.AdvancedUserSecurityStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Class AuthorizationTest содержит тесты для сервлета AuthorizationTest.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 20.11.2017
 * */
public class AuthorizationTest {
    /**
     * Тест для метода doPost.
     * @throws Exception - исключение метода doPost
     * */
    @Test
    public void whenDoPostThenSuccessfulAuthorization() throws Exception {

        AdvancedUserSecurityStore.getInstance().clearUsers();
        AdvancedUser user = new AdvancedUser.AdvancedUserBuilder().addLogin("tom").addName("Tom").addRole("admin").
                addEmail("tom@mail.ru").addPassword("tom").build();
        AdvancedUserSecurityStore.getInstance().insert(user);

        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
        String password = "tom";
        String login = "tom";
        Mockito.when(mockRequest.getParameter("password")).thenReturn(password);
        Mockito.when(mockRequest.getParameter("login")).thenReturn(login);
        MockHttpSession session = new MockHttpSession();
        Mockito.when(mockRequest.getSession()).thenReturn(session);

        String expectedLogin = login;
        String expectedRole = "admin";
        Authorization servlet = new Authorization();
        servlet.doPost(mockRequest, mockResponse);

        UserIdentification identification = (UserIdentification) session.getAttribute("identification");
        assertThat(identification.getLogin(), is(expectedLogin));
        assertThat(identification.getRole(), is(expectedRole));

    }

}