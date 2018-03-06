package ru.job4j.cartrade.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.cartrade.model.photo.Photo;

/**
 * Interface PhotoRepository описывает репозиторий фотографий.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 04.03.2018
 * */
public interface PhotoRepository extends CrudRepository<Photo, Long> {
}
