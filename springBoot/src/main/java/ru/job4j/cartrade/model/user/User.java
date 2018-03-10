package ru.job4j.cartrade.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.job4j.cartrade.model.car.Car;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Class User описывает модель пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 16.01.2018
 * */
@Entity
@Table(name = "users")
public class User {

    /** id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /** name. */
    @Column(name = "name")
    private String name;

    /** surname. */
    @Column(name = "surname")
    private String surname;

    /** password. */
    @Column(name = "password")
    private String password;
    /** cars. */
    @JsonIgnore
    @ManyToMany(mappedBy = "owners")
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
