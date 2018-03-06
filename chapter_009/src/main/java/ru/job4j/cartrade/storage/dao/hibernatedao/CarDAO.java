package ru.job4j.cartrade.storage.dao.hibernatedao;

import org.hibernate.Session;
import ru.job4j.cartrade.model.car.Car;
import ru.job4j.cartrade.storage.dao.ICarDAO;

import java.util.List;

/**
 * Class CarDAO описывает hibernate реализацию ICarDAO.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 24.01.2018
 * */
public class CarDAO implements ICarDAO {

    /** hibernate сессия. */
    private final Session session;
    /**
     * Конструктор CarDAO.
     * @param session - hibernate сессия.
     * */
    public CarDAO(Session session) {
        this.session = session;
    }

    /**
     * Возвращает автомобиль из хранилища по id.
     * @param id - id автомобиля
     * @return автомобиль (null, если автомобиль не найден)
     * */
    @Override
    public Car get(long id) {
//        List<Car> cars = session.createQuery(String.format("FROM Car c WHERE c.id = %d", id)).list();
//        return cars.size() != 0 ? cars.get(0) : null;
        return session.get(Car.class, id);
    }

    /**
     * Возвращает все автомобили из хранилища.
     * @return список всех автомобилей, находящихся в хранилище
     * */
    @Override
    public List<Car> getAll() {
        List<Car> cars = session.createQuery("FROM Car").list();
        return cars;
    }

    /**
     * Добавляет автомобиль в хранилище.
     * @param car - добаляемый автомобиль.
     * @return добавленный автомобиль
     * */
    @Override
    public Car persist(Car car) {
        session.save(car);
        return  car;
    }

    /**
     * Обновляет авомобиль, находящуюся в хранилище.
     * @param modifiedCar - обновленный автомобиль
     * @return обновленный автомобиль
     * */
    @Override
    public Car update(Car modifiedCar) {
        session.update(modifiedCar);
        return modifiedCar;
    }

    /**
     * Удаляет из хранилища заданный автомобиль.
     * @param car - удаляемый автомобиль.
     * @return удаленный автомобиль
     * */
    @Override
    public Car remove(Car car) {
        session.delete(car);
        return car;
    }
}
