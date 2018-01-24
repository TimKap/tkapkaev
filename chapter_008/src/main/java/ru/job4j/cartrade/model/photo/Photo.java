package ru.job4j.cartrade.model.photo;

/**
 * Class Photo описывает фотографию.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 16.01.2018
 * */
public class Photo {
    /** id. */
    private long id;
    /** photo's file. */
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
}
