package ru.job4j.cartrade.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Class SecurityConfiguration описывает конфигурацию безопасности проекта.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 09.03.2018
 * */
@Configuration
@ImportResource("classpath:security-context.xml")
public class SecurityConfiguration {
}
