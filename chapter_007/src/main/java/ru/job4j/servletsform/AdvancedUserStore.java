package ru.job4j.servletsform;

import ru.job4j.firstservlets.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * Class AdvancedUserStore описывает работу с базой данных пользователей User.
 * @author Timur Kapkaev(timur.kap@yandex.ru)
 * @version $ID$
 * @since 15.11.2017
 * */
public class AdvancedUserStore extends AdvancedStore<User> {

    /** экземпляр AdvancedUserStore. */
    private static final AdvancedUserStore INSTANCE = new AdvancedUserStore();

    /**
     * Конструктор класса AdvancedUserStore.
     * */
    private AdvancedUserStore() {
        super(
                 resultSet -> {
                    String login = resultSet.getString("login");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    Date date = new Date(resultSet.getTimestamp("date").getTime());
                    return new User(name, login, email, date);
                });
    }

    /**
     * Возвращает экземпляр AdvancedUserStore.
     * @return экземпляр AdvancedUserStore
     * */
    public static AdvancedUserStore getInstance() {
        return INSTANCE;
    }

    /**
     * Извлекает имя таблицы пользователей из конфигурационного файла.
     * @return имя таблицы пользователей
     * @throws IOException - ошибка конфигурационного файла
     * */
    @Override
    protected String extractTableName() throws IOException {
        InputStream propertyFile = AdvancedStore.class.getResourceAsStream("/config.properties");
        Properties properties = new Properties();
        properties.load(propertyFile);
        return properties.getProperty("table");
    }
}
