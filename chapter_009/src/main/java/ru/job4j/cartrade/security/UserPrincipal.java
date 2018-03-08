package ru.job4j.cartrade.security;
import org.springframework.security.core.GrantedAuthority;
import ru.job4j.cartrade.model.user.User;

import java.util.Collection;

/**
 * Class UserPrincipal содержит информацию о пользователе площадки продажи машин.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 08.03.2018
 * */
public class UserPrincipal extends org.springframework.security.core.userdetails.User {
    /** пользователь площадки продажи машин. */
    private User user;
    /**
     * Конструктор класса UserPrincipal.
     * @param user - пользователь площадки продажи машин.
     * @param authorities - роли
     * */
    public UserPrincipal(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getName(), user.getPassword(), authorities);
        this.user = user;
    }
    /**
     * Конструктор класса UserPrincipal.
     * @param user - пользователь площадки продажи автомобилей.
     * @param enabled - set to true if the user is enabled
     * @param accountNonExpired - set to true if the account has not expired
     * @param credentialsNonExpired - set to true if the credentials have not expired
     * @param accountNonLocked - set to true if the account is not locked
     * @param authorities - the authorities that should be granted to the caller if they presented the correct username and password and the user is enabled. Not null.
     * */
    public UserPrincipal(User user, boolean enabled, boolean accountNonExpired,
                         boolean credentialsNonExpired,
                         boolean accountNonLocked,
                         Collection<? extends GrantedAuthority> authorities) {
        super(user.getName(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
    }
    /**
     * Возвращает пользователя площадки продажи машин.
     * @return пользователь площадки продажи машин.
     * */
    public User getUser() {
        return user;
    }
}
