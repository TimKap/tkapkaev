package ru.job4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Class Application описывает главный гласс в фреймворке Spring Boot.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 09.03.2018
 * */
@SpringBootApplication
public class Application {
    /**
     * Точка входа.
     * @param args - фргументы командной строки.
     * */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}
