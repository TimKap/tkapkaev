package ru.job4j.cartrade.model.user;

import ru.job4j.cartrade.model.car.Car;

import java.util.HashSet;
import java.util.Set;

/**
 * Class User описывает модель пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 16.01.2018
 * */
public class User {
    /** id. */
    private long id;
    /** name. */
    private String name;
    /** surname. */
    private String surname;
    /** password. */
    private String password;
    /** cars. */
    private Set<Car> cars = new HashSet<>();

    /**
     * Конструктор для User.
     * */
    public User() {

    }

    /**
     * Возвращает id пользователя.
     * @return id
     * */
    public long getId() {
        return id;
    }

    /**
     * Задает id пользователя.
     * @param id - id пользователя
     * */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Возвращает name пользователя.
     * @return name
     * */
    public String getName() {
        return name;
    }

    /**
     * Задает name пользователя.
     * @param name - name пользователя
     * */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает surname пользователя.
     * @return surname
     * */
    public String getSurname() {
        return surname;
    }

    /**
     * Задает surname пользователя.
     * @param surname - surname пользователя
     * */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Возвращает password пользователя.
     * @return password
     * */
    public String getPassword() {
        return password;
    }

    /**
     * Задает password пользователя.
     * @param password - password пользователя
     * */
    public void setPassword(String password) {
        this.password = password;
    }

    /***
     * Возвращает автомобили пользователя.
     * @return автомобили пользователя.
     * */
    public Set<Car> getCars() {
        return cars;
    }

    /**
     * Задает автомобили пользователя.
     * @param cars - автомобили пользователя.
     * */
    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
    /**
     * Равенство объектов.
     * @param o - сравниваемый объект
     * @return true, объекты равны
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

        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) {
            return false;
        }
        return password != null ? password.equals(user.password) : user.password == null;

    }

    /**
     * Хэш-функция.
     * @return хэш-код
     * */
    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;

    }
}