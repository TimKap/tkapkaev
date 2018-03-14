package ru.job4j.cartrade.controller.authorization;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.cartrade.storage.service.AdvertisementService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Class AuthorizationTest описывает тест для контроллера авторизации.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 14.03.2018
 * */
@RunWith(SpringRunner.class)
@WebMvcTest(Authorization.class)
public class AuthorizationTest {
    /**mock MockMvc.*/
    @Autowired
    private MockMvc mockMvc;
    /**mock AdvertisementService bean. */
    @MockBean
    private AdvertisementService advertisementService;

    /**
     * Тест для showPage.
     * @throws Exception -
     * */
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenShowPageThenReturnAuthorizationPage() throws Exception {
        mockMvc.perform(get("/authorization").accept("text/html;charset='UTF-8'"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset='UTF-8'"))
                .andExpect(view().name("cartrade/authorization/authorization"));
    }
}