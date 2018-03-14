package ru.job4j.cartrade.controller.main.advertisementredactor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import org.springframework.mock.web.MockMultipartFile;

import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import ru.job4j.cartrade.controller.main.CartradeTestData;

import ru.job4j.cartrade.security.CartradeUserDetails;
import ru.job4j.cartrade.storage.repositories.AdvertisementRepository;
import ru.job4j.cartrade.storage.repositories.CarRepository;
import ru.job4j.cartrade.storage.repositories.PhotoRepository;
import ru.job4j.cartrade.storage.repositories.UserRepository;
import ru.job4j.cartrade.storage.service.AdvertisementService;



import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Class AdvertisementRedactorTest содержит тесты для AdvertisementRedactor.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 14.03.2018
 * */
@RunWith(SpringRunner.class)
@WebMvcTest(AdvertisementRedactor.class)
public class AdvertisementRedactorTest {
    /** mockMvc. */
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
     * Тест для addAdvertisement.
     * @throws Exception -
     * */
    @Test
    @WithUserDetails(value = "Tom", userDetailsServiceBeanName = "cartradeUserDetails")
    public void whenAddAdvertisementThenGetRedirectToMainPage() throws Exception {
        MockMultipartFile file = new MockMultipartFile("photo", new byte[]{1, 2, 3});
        mockMvc.perform(fileUpload("/advertisementredactor")
                .file(file)
                .contentType("multipart/form-data")
                .param("model", "Lamborgine")
                .param("bodytype", "Sport")
                .param("bodycolor", "black")
                .param("engine", "BiTurbo")
                .param("comment", "Hey, I'm new car")
        )
                .andExpect(status().is3xxRedirection());

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
            given(advertisementService.getUser("Tom")).willReturn(CartradeTestData.prepareUser());
            return advertisementService;
        }

    }

}