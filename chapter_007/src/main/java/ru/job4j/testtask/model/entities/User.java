package ru.job4j.testtask.model.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Class User описывает пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 13.12.2017
 * */
public class User {
    /** ID. */
    private int id;
    /** name.*/
    private String name;
    /** surname. */
    private String surname;
    /** музыкальные предпочтения. */
    private final List<MusicStyle> musicPreferences = new ArrayList<>();
    /** адрес. */
    private Address address;
    /** роль. */
    private Role role;
    /**пароль.*/
    private String password;

    /**
     * Конструктор класса User.
     * @param builder - builder класса User.
     * */
     User(UserBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.address = builder.address;
        this.role = builder.role;
        this.password = builder.password;
    }

    /**
     * Конструктор класса User.
     * */
    private User() {
    }

    /**
     * Class UserBuilder описывает builder для пользователя.
     * @author Timur Kapkaev (timur.kap@yandex.ru)
     * @version $ID$
     * @since 14.12.2017
     * */
    public static class UserBuilder {
        /** ID. */
        private int id;
        /** name.*/
        private String name;
        /** surname. */
        private String surname;
        /** адрес. */
        private Address address;
        /** роль. */
        private Role role;
        /**пароль.*/
        private String password;



        /**
         * Добавляет ID пользователя.
         * @param id - ID пользователя.
         * @return UserBuilder
         * */
        public UserBuilder addId(int id) {
            this.id = id;
            return this;
        }

        /**
         * Добавляет  имя пользователя.
         * @param name - имя пользователя.
         * @return UserBuilder
         * */
        public UserBuilder addName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Добавляет  фамилию пользователя.
         * @param surname - фамилия пользователя.
         * @return UserBuilder
         * */
        public UserBuilder addSurname(String surname) {
            this.surname = surname;
            return this;
        }

        /**
         * Добавляет адрес пользователя.
         * @param address - адрес пользователя
         * @return UserBuilder
         * */
        public UserBuilder addAddress(Address address) {
            this.address = address;
            return this;
        }

        /**
         * Добавляет  роль пользователя.
         * @param role - роль пользователя.
         * @return UserBuilder
         * */
        public UserBuilder addRole(Role role) {
            this.role = role;
            return this;
        }
        /**
         * Добавляет пароль пользователю.
         * @param password - пароль
         * @return UserBuilder
         * */
        public UserBuilder addPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * Возвращает User.
         * @return User
         * */
        public User build() {
            return new User(this);
        }

    }


    /**
     * Возвращает ID.
     * @return ID.
     * */
    public int getId() {
        return id;
    }

    /**
     * Задает ID.
     * @param id - ID
     * */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Возвращает имя.
     * @return имя.
     * */
    public String getName() {
        return name;
    }

    /**
     * Задает имя.
     * @param name - имя
     * */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает фамилию.
     * @return фамилия.
     * */
    public String getSurname() {
        return surname;
    }

    /**
     * Задает фамилию.
     * @param surname - фамилия
     * */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Возвращает Iмузыкальные предпочтения.
     * @return музыкальные предпочтения.
     * */
    public List<MusicStyle> getMusicPreferences() {
        return musicPreferences;
    }

    /**
     * Возвращает адрес.
     * @return адрес.
     * */
    public Address getAddress() {
        return address;
    }

    /**
     * Задает адрес.
     * @param address - адрес
     * */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Возвращает роль.
     * @return роль.
     * */
    public Role getRole() {
        return role;
    }

    /**
     * Задает роль.
     * @param role - роль
     * */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Возвращает пароль.
     * @return пароль.
     * */
    public String getPassword() {
        return password;
    }

    /**
     * Задает пароль.
     * @param password - пароль
     * */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Определяет равенство объектов.
     * @param o - сравниваемый объект
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return id == user.id;
    }

    /**
     * Задает хеш-функцию.
     * @return хеш-код
     * */
    @Override
    public int hashCode() {
        return id;
    }
}
