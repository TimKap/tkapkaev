package ru.job4j.testtask.model.entities;

/**
 * Class Role описывает роль.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 13.12.2017
 * */
public class Role {

    /** ID.*/
    private int id;
    /** роль. */
    private String role;
    /**
     * Конструктор класса Role.
     * @param id - ID роли
     * @param role - название роли
     * */
    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    /**
     * Конструктор класса Role.
     * @param role - название роли
     * */
    public Role(String role) {
        this(0, role);
    }

    /**
     * Конструктор класса User.
     * */
    private Role() {
        this(0, null);
    }
    /**
     * Задает ID.
     * @param id - id роли
     * */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Возвращает ID.
     * @return ID
     * */
    public  int getId() {
        return id;
    }

    /**
     * Задает роль.
     * @param role - название роли
     * */
    public void setRole(String role) {
        this.role = role;
    }
    /**
     * Возвращает роль.
     * @return роль
     * */
    public String getRole() {
        return role;
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
        Role ro = (Role) o;
        return ro.id == id;
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
