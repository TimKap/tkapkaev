package ru.job4j.cartrade.model.photo;

import com.google.gson.annotations.Expose;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.util.Arrays;

/**
 * Class Photo описывает фотографию.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 16.01.2018
 * */
@Entity
@Table(name = "photos")
public class Photo {

    /** id. */
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /** photo's file. */
    @Expose
    @Column(name = "file")
    private byte[] file;
    /**
     * Конструктор класса Photo.
     * */
    public Photo() {

    }

    /**
     * Возвращает id фотографии.
     * @return id фотографии.
     * */
    public long getId() {
        return id;
    }

    /**
     * Задает id фотографии.
     * @param id - фотографии
     * */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Возвращает бинарное представление файла фотографии.
     * @return бинарное представление файла фотографии.
     * */
    public byte[] getFile() {
        return file;
    }

    /**
     * Задает бинарное представление файла фотграфии.
     * @param file - бинарное представление файла фотографии
     * */
    public void setFile(byte[] file) {
        this.file = file;
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
        Photo photo = (Photo) o;

        return Arrays.equals(file, photo.file);
    }


    /**
     * Хэш-функция.
     * @return хэш-код
     * */
    @Override
    public int hashCode() {
       int result = 0;
       result = 31 * result + Arrays.hashCode(file);
       return result;
    }


}
