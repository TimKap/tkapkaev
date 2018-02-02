package ru.job4j.cartrade.storage.dao.hibernatedao;

import org.hibernate.Session;
import ru.job4j.cartrade.model.photo.Photo;
import ru.job4j.cartrade.storage.dao.IPhotoDAO;

import java.util.List;

/**
 * Class PhotoDAO описывает hibernate реализацию IPhotoDAO.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 24.01.2018
 * */
public class PhotoDAO implements IPhotoDAO {

    /** hibernate сессия. */
    private final Session session;
    /**
     * Конструктор PhotoDAO.
     * @param session - hibernate сессия.
     * */
    public PhotoDAO(Session session) {
        this.session = session;
    }

    /**
     * Возвращает фотографию из хранилища по id.
     * @param id - id фотографии
     * @return фотография (null, если фотография не найдена)
     * */
    @Override
    public Photo get(long id) {
        List<Photo> photos = session.createQuery(String.format("FROM Photo p WHERE p.id = %d", id)).list();
        return photos.size() != 0 ? photos.get(0) : null;
    }

    /**
     * Возвращает все фотографии из хранилища.
     * @return список всех фотографий, находящихся в хранилище
     * */
    @Override
    public List<Photo> getAll() {
        List<Photo> photos = session.createQuery("FROM Photo").list();
        return photos;
    }

    /**
     * Добавляет фотографию в хранилище.
     * @param photo - добаляемая фотография.
     * @return добавленная фотография
     * */
    @Override
    public Photo persist(Photo photo) {
        session.save(photo);
        return photo;
    }

    /**
     * Обновляет фотографию, находящуюся в хранилище.
     * @param modifiedPhoto - обновленная фотография
     * @return обновленная фотография
     * */
    @Override
    public Photo update(Photo modifiedPhoto) {
        session.update(modifiedPhoto);
        return modifiedPhoto;
    }

    /**
     * Удаляет из хранилища заданную фотографию.
     * @param photo - удаляемая фотография
     * @return удаленная фотография
     * */
    @Override
    public Photo remove(Photo photo) {
        session.remove(photo);
        return photo;
    }
}
