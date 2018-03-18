package ru.job4j.testtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class Application описывает главный классв фреймворке SpringBoot.
 * @author Timur Kapakev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 15.03.2018
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
