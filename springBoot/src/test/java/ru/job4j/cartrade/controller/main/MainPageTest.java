package ru.job4j.cartrade.controller.main;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;


import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import ru.job4j.cartrade.controller.authorization.UserIdentification;
import ru.job4j.cartrade.model.advertisement.Advertisement;
import ru.job4j.cartrade.security.CartradeUserDetails;

import ru.job4j.cartrade.storage.repositories.AdvertisementRepository;
import ru.job4j.cartrade.storage.repositories.CarRepository;
import ru.job4j.cartrade.storage.repositories.PhotoRepository;
import ru.job4j.cartrade.storage.repositories.UserRepository;
import ru.job4j.cartrade.storage.service.AdvertisementService;

import java.util.List;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Class MainPageTest описывает тесты к контроллеру главной страницы.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 12.03.2018
 * */
@RunWith(SpringRunner.class)
@WebMvcTest(MainPage.class)
public class MainPageTest {


    /** mock экземпляр MVC. */
    @Autowired
    private MockMvc mockMvc;

    /**mock CarRepository bean. */
    @MockBean
    private CarRepository carRepository;
    /** mock UserRepository bean. */
    @MockBean
    private UserRepository userRepository;
    /** mock photo Repository bean. */
    @MockBean
    private PhotoRepository photoRepository;
    /** mock AdvertisementRepository bean. */
    @MockBean
    private AdvertisementRepository advertisementRepository;


    /**
     * Тест showPage.
     * @throws Exception -
     * */
    @Test
    @WithUserDetails(value = "Tom",  userDetailsServiceBeanName = "cartradeUserDetails")
    public void whenShowPageThenReturnMainPage() throws Exception {
        mockMvc.perform(get("/main")
                .accept(MediaType.TEXT_HTML)
        )
                .andExpect(status().isOk())
                .andExpect(view().name("cartrade/main/main"))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    /**
     * Тест для getAdvertisements.
     * @throws Exception -
     * */
    @Test
    @WithUserDetails(value = "Tom",  userDetailsServiceBeanName = "cartradeUserDetails")
    public void whenGetAdvertisementsThenGetList() throws Exception {

        MvcResult response = mockMvc.perform(get("/getAdvertisements")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("filter", "ALL")
                .param("fValue", "")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        List<Advertisement> result = mapper.readValue(response.getResponse().getContentAsString(), mapper.getTypeFactory().constructCollectionType(List.class, Advertisement.class));
        Advertisement advertisement = result.get(0);
        assertThat(advertisement.getId(), is(1L));
        assertThat(advertisement.getSeller().getName(), is("Tom"));
        assertThat(advertisement.getProduct().getModel(), is("Lamborgine"));
    }
    /**
     * Тест для getImage.
     * @throws Exception -
     * */
    @Test
    @WithUserDetails(value = "Tom",  userDetailsServiceBeanName = "cartradeUserDetails")
    public void whenGetImageThenGetString64() throws Exception {
        String expected = Base64.getEncoder().encodeToString(new byte[] {1, 2, 3});
        MvcResult response = mockMvc.perform(get("/getImage")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("advId", "1")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
        String result = response.getResponse().getContentAsString();
        assertThat(result, is(expected));
    }
    /**
     * Тест для changeStatus.
     * @throws Exception -
     * */
    @Test
    @WithUserDetails(value = "Tom",  userDetailsServiceBeanName = "cartradeUserDetails")
    public void whenChangeStatusThenGetModifiedAdvertisement() throws Exception {
        Advertisement reqAdvertisement = CartradeTestData.prepareReqAdvertisement();
        reqAdvertisement.setSold(true);
        ObjectMapper mapper = new ObjectMapper();
        String requestContent = mapper.writeValueAsString(reqAdvertisement);
        MvcResult response = mockMvc.perform(post("/changeStatus")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestContent)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
        String result = response.getResponse().getContentAsString();
        System.out.println(result);
        assertThat(result, is(requestContent));
    }


    /**
     * Class SecurityConfiguration описывает конфигурацию безопасности.
     * @author Timur Kapkaev (timur.kap@yandex.ru)
     * @version $Id$
     * @since 14.03.21018
     * */
    @TestConfiguration
    static class SecurityConfiguration {
        /**
         * Формирует bean CartradeUserDetails.
         * @return bean CartradeUserDetails
         * */
        @Bean
        CartradeUserDetails cartradeUserDetails() {
            return new CartradeUserDetails();
        }
        /**
         * Формирует bean AdvertisementService.
         * @return bean AdvertisementService
         * */
        @Bean
        AdvertisementService advertisementService() {
            AdvertisementService advertisementService =  Mockito.mock(AdvertisementService.class);
            given(advertisementService.getAdvertisements("ALL", "")).willReturn(new ArrayList<>(Arrays.asList(CartradeTestData.prepareAdvertisement())));
            given(advertisementService.getAdvPhoto(1L)).willReturn(CartradeTestData.preparePhoto());

            Advertisement reqAdvertisement = CartradeTestData.prepareReqAdvertisement();
            reqAdvertisement.setSold(true);
            given(advertisementService.changeStatus(new UserIdentification(1L, null, "Tom", "tom"), reqAdvertisement))
                    .willAnswer((Answer<Advertisement>) invocation -> reqAdvertisement);
            given(advertisementService.getUser("Tom")).willReturn(CartradeTestData.prepareUser());
            return advertisementService;
        }

    }


}