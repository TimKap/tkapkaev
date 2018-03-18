package ru.job4j.testtask.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.CollectionTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.FetchType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class Account описывает аккаунт пользователя.
 * */
@Entity
@Table(name = "accounts")
public class Account {
    /** id. */
    @Id
    @Column (name = "id")
    private String id;
    /** login. */
    @Column (name = "login")
    private String login;
    /** password. */
    @Column (name = "password")
    private String password;
    /** urlRegisters.*/
    @OneToMany
    @JoinTable(
            name = "accountUrlRegisters",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "urlRegister_id", unique = true)
    )
    private Set<UrlRegister> urlRegisters = new HashSet<>();


    /** статистика аккаунта. */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "statistics")
    @MapKeyColumn(name = "url")
    @Column(name = "redirects")
    private Map<String, Integer> statistics = new HashMap<>();

    /**
     * Конструктор Account.
     * */
    public Account() {

    }
    /**
     * Возвращает id.
     * @return id
     * */
    public String getId() {
        return id;
    }

    /**
     * Задает id.
     * @param id - id пользователя
     * */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Возвращает login.
     * @return login
     * */
    public String getLogin() {
        return login;
    }

    /**
     * Задает login.
     * @param login - логин рользователя
     * */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Возвращает password.
     * @return password
     * */
    public String getPassword() {
        return password;
    }

    /**
     * Задает password.
     * @param password - пароль пльзователя.
     * */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Возвращает статистику.
     * @return статситика аккаунта
     * */
    public Map<String, Integer> getStatistics() {
        return statistics;
    }

    /**
     * Задает статистику аккаунта.
     * @param statistics - статистика аккаунта.
     * */
    public void setStatistics(Map<String, Integer> statistics) {
        this.statistics = statistics;
    }


    /**
     * Возвращает зарегистрированные Url.
     * @return зарегистрированные url.
     * */
    public Set<UrlRegister> getUrlRegisters() {
        return urlRegisters;
    }

    /**
     * Задает зарегистрированные url.
     * @param urlRegisters - регистрируемые url.
     * */
    public void setUrlRegisters(Set<UrlRegister> urlRegisters) {
        this.urlRegisters = urlRegisters;
    }
}
