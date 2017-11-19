package ru.job4j.security.model;

import ru.job4j.servletsform.AdvancedStore;
import ru.job4j.servletsform.IResultSetConverter;

import java.io.IOException;
import java.io.InputStream;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.Set;
import java.util.HashSet;


/**
 * Class AdvancedUserStore описывает работу с базой данных пользователей AdvancedUser.
 * @author Timur Kapkaev(timur.kap@yandex.ru)
 * @version $ID$
 * @since 17.11.2017
 * */
public class AdvancedUserSecurityStore  extends AdvancedStore<AdvancedUser> {

    /** экземпляр AdvancedUserSecurityStore. */
    private static final AdvancedUserSecurityStore INSTANCE = new AdvancedUserSecurityStore();

    /**
     * Конструктор класса AdvancedUserSecurityStore.
     * */
    private AdvancedUserSecurityStore() {
        super( resultSet -> {
            String login = resultSet.getString("login");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            Date date = new Date(resultSet.getTimestamp("date").getTime());
            String role = resultSet.getString("role");
            String password = resultSet.getString("password");
            return new AdvancedUser.AdvancedUserBuilder().addLogin(login).addName(name).addEmail(email).addCreateDate(date).addRole(role).addPassword(password).build();
        });
    }

    /**
     * Возвращает экземпляр AdvancedUserSecurityStore.
     * @return AdvancedUserSecurityStore
     * */
    public static AdvancedUserSecurityStore getInstance() {
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
        String table = properties.getProperty("SecurityTable");
        return table;
    }

    /**
     * Авторизация пользователя.
     * @param login - логин пользователя.
     * @param password - пароль
     * @return пользователя. (null - если авторизация не прошла)
     * @throws SQLException - ошибка авторизации пользователя
     * */
    public AdvancedUser credentialUser(String login, String password) throws SQLException {
        AdvancedUser user = searchByPrimaryKey(new AdvancedUser.Key(login));
        if (user != null && !user.getPassword().equals(password)) {
            user = null;
        }
        return  user;
    }

    /**
     * Возвращает список ролей.
     * @return роли
     * @throws SQLException - ошибка возвращения ролей пользователей.
     * */
    public Set<String> getRoles() throws SQLException {
        HashSet<String> roles = new HashSet<>();
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM roles;")) {
            while (rs.next()) {
                String role = rs.getString("role");
                roles.add(role);
            }
        }
        return roles;
    }
}
