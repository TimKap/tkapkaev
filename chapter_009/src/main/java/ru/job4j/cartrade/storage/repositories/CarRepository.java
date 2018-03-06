package ru.job4j.cartrade.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.cartrade.model.car.Car;

/**
 * Interface CarRepository описывает репозиторий автомобилей.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 04.03.2018
 * */
public interface CarRepository extends CrudRepository<Car, Long> {
}
