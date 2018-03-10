package ru.job4j.cartrade.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.cartrade.model.user.User;
import ru.job4j.cartrade.storage.service.AdvertisementService;

import java.util.ArrayList;
import java.util.List;

/**
 * Class CartradeUserDetails предоставляет реализацию UserDetailsService для площадки продажи автомобилей.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 08.03.2018
 * */
@Service
public class CartradeUserDetails implements UserDetailsService {
    /** сервис объявлений. */
    @Autowired
    private AdvertisementService advertisementService;
    /**
     * Возвращает информацию о пользователе по его имени.
     * @param username - имя пользователя
     * @return информация о пользователе.
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = advertisementService.getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user");
        }
        UserPrincipal principal = new UserPrincipal(user, getAuthorities(null));
        return principal;
    }

    /**
     * Возвращает коллекцю из GrantedAuthority элементов.
     * @param roles - список ролей
     * @return список GrantedAuthority элементов.
     * */
    private  List<GrantedAuthority> getAuthorities(List<String> roles) {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        if (roles != null) {
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authorities;
    }
}
