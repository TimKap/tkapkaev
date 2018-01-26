package ru.job4j.cartrade.model.car;


import ru.job4j.cartrade.model.photo.Photo;
import ru.job4j.cartrade.model.user.User;



import java.util.HashSet;

import java.util.Set;

/**
 * Class Car описывает автомобиль.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 16.01.2018
 * */
public class Car {
    /** id. */
    private long id;
    /** model. */
    private String model;
    /** engine.*/
    private Engine engine;
    /** body. */
    private Body body;
    /** owners. */
    private Set<User> owners = new HashSet<>();
    /** photos. */
    private Set<Photo> photos = new HashSet<>();


    /**
     * Конструктор класса Car.
     * */
    public Car() {

    }

    /**
     * Возвращает id  автомобиля.
     * @return id автомобиля.
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
     * Возвращает модель автомобиля.
     * @return модель автомобиля.
     * */
    public String getModel() {
        return model;
    }

    /**
     * Задает модель автомобиля.
     * @param model - модель автомобиля
     * */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Возвращает двигатель автомобиля.
     * @return двигатель автомобиля.
     * */
    public Engine getEngine() {
        return engine;
    }

    /**
     * Задает двигатель автомобиля.
     * @param engine - двигатель автомобиля
     * */
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    /**
     * Возвращает кузов автомобиля.
     * @return кузов автомобиля.
     * */
    public Body getBody() {
        return body;
    }

    /**
     * Задает корпус автомобиля.
     * @param body - корпус автомобиля
     * */
    public void setBody(Body body) {
        this.body = body;
    }

    /**
     * Возвращает владельцев автомобиля.
     * @return владельцев автомобиля.
     * */
    public Set<User> getOwners() {
        return owners;
    }

    /**
     * Задает владельцев автомобиля.
     * @param owners - владельцы автомобиля
     * */
    public void setOwners(Set<User> owners) {
        this.owners = owners;
    }

    /**
     * Возвращает фотографии автомобиля.
     * @return фотографии автомобиля
     * */
    public Set<Photo> getPhotos() {
        return photos;
    }

    /**
     * Задает фотографии атомобиля.
     * @param photos - фотографии автомобиля
     * */
    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
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
        Car car = (Car) o;

        if (model != null ? !model.equals(car.model) : car.model != null) {
            return false;
        }
        if (engine != null ? !engine.equals(car.engine) : car.engine != null) {
            return false;
        }
        if (body != null ? !body.equals(car.body) : car.body != null) {
            return false;
        }
        return photos != null ? photos.equals(car.photos) : car.photos == null;

    }
    /**
     * Хэш-функция.
     * @return хэш-код
     * */
    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (engine != null ? engine.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (photos != null ? photos.hashCode() : 0);
        return result;
    }
}
