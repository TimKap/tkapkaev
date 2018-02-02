package ru.job4j.cartrade.storage.dao.hibernatedao;

import org.junit.Test;
import ru.job4j.cartrade.model.photo.Photo;
import ru.job4j.cartrade.storage.Storage;
import ru.job4j.cartrade.storage.dao.IPhotoDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class PhotoDAOTest содержит тесты для PhotoDAO.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 01.02.2018
 * */
public class PhotoDAOTest {
    /**вспомогательный объект.*/
    private final Auxiliary auxiliary = new Auxiliary();

    /**
     * Тест для get и add.
     * */
    @Test
    public void whenAddPhotoThenGetPhoto() {
        /*prepare test*/
        /*execute test*/
        Photo photo = auxiliary.preparePhoto();
        Storage storage = Storage.getInstance();
        storage.open();
        IPhotoDAO photoDAO = storage.getPhotoDAO();
        photoDAO.persist(photo);
        storage.submit();
        /*get result*/
        storage.open();
        photoDAO = storage.getPhotoDAO();
        Photo result = photoDAO.get(photo.getId());
        /*check result*/
        assertThat(result, is(photo));
        storage.submit();
    }

    /**
     * Тест для update.
     * */
    @Test
    public void whenUpdatePhotoThenGetModifiedPhoto() {
        /*prepare test*/
        Photo photo = auxiliary.preparePhoto();
        Storage storage = Storage.getInstance();
        storage.open();
        IPhotoDAO photoDAO = storage.getPhotoDAO();
        photoDAO.persist(photo);
        storage.submit();
        /*execute test*/
        photo.setFile(new byte[] {5, 4, 3, 2});
        storage.open();
        photoDAO = storage.getPhotoDAO();
        photoDAO.update(photo);
        /*get result*/
        Photo result = photoDAO.get(photo.getId());
        /*check result*/
        assertThat(result, is(photo));
        storage.submit();
    }

    /**
     * Тест для getAll.
     * */
    @Test
    public void whenGetAllThenGetAllPhotos() {
        /*prepare test*/
        Photo photo1 = auxiliary.preparePhoto();
        Photo photo2 = auxiliary.preparePhoto();
        Storage storage = Storage.getInstance();
        storage.open();
        IPhotoDAO photoDAO = storage.getPhotoDAO();
        photoDAO.persist(photo1);
        photoDAO.persist(photo2);
        storage.submit();
        List<Photo> expected = new ArrayList<>(Arrays.asList(photo1, photo2));
        /*execute test*/
        /*get result*/
        storage.open();
        photoDAO = storage.getPhotoDAO();
        List<Photo> result = photoDAO.getAll();
        /*check result*/
        assertThat(result.containsAll(expected), is(true));
        storage.submit();
    }

    /**
     * Тест для remove.
     * */
    @Test
    public void whenRemove() {
        /*prepare test*/
        Photo photo = auxiliary.preparePhoto();
        Storage storage = Storage.getInstance();
        storage.open();
        IPhotoDAO photoDAO = storage.getPhotoDAO();
        photoDAO.persist(photo);
        storage.submit();
        /*execute test*/
        storage.open();
        photoDAO = storage.getPhotoDAO();
        photoDAO.remove(photo);
        /*get result*/
        Photo result = photoDAO.get(photo.getId());
        /*check result*/
        assertThat(result == null, is(true));
        storage.submit();
    }
}