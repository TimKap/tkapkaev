package ru.job4j.testtask.model.entities;

/**
 * Class Address описывает адрес.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 13.12.2017
 * */
public class Address {
    /** id. */
    private int id;
    /** country. */
    private String country;
    /** city. */
    private String city;
    /** Street. */
    private String street;

    /**
     * Конструктор Address.
     * @param id - ID адреса
     * @param country - страна
     * @param city - город
     * @param street - улица
     * */
    public  Address(int id, String country, String city, String street) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
    }

    /**
     * Конструктор Address.
     * @param country - страна
     * @param city - город
     * @param street - улица
     * */
    public  Address(String country, String city, String street) {
        this(0, country, city, street);
    }


//    /**
//     * Конструктор Address.
//     * @param id - ID фдреса.
//     * */
//    public Address(int id) {
//        this(id, null, null, null);
//    }

    /**
     * Конструктор класса User.
     * */
    private Address() {
        this(0, null, null, null);
    }
    /**
     * Возвращает ID адреса.
     * @return ID адреса
     * */
    public int getId() {
        return id;
    }

    /**
     * Задает ID.
     * @param id - ID
     * */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Возвращает страну.
     * @return страна
     * */
    public String getCountry() {
        return country;
    }

    /**
     * Задает страну.
     * @param country - страна
     * */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Возвращает город.
     * @return город
     * */
    public String getCity() {
        return city;
    }

    /**
     * Задает город.
     * @param city - сгород
     * */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Возвращает улицу.
     * @return цлица
     * */
    public String getStreet() {
        return street;
    }

    /**
     * Задает цлицу.
     * @param street - улицу
     * */
    public void setStreet(String street) {
        this.street = street;
    }
    /**
     * Определяет равенство объектов.
     * @param o - сравниваемый объект
     * */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Address address = (Address) o;
        return address.id == id;
    }

    /**
     * Задает хеш-функцию.
     * @return хеш-код
     * */
    @Override
    public int hashCode() {
        return id;
    }


}
