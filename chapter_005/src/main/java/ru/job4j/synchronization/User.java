package ru.job4j.synchronization;

/**
 * Class User описывает пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 21.08.2017
 */
public class User {
    /** id. */
    private final int id;
    /** счет. */
    private int amount;

    /** Конструктор класса User.
     * @param id - id пользователя
     * @param amount - счет пользователя
     * */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }
    /**
     * Возвращает id пользователя.
     * @return id пользователя
     * */
    public int getId() {
        return id;
    }
    /**
     * Возвращает счет пользователя.
     * @return счет пользователя
     * */
    public int getAmount() {
        return amount;
    }

    /**
     * Добавляет средства.
     * @param amount - размер добавляемых средств
     * */
    public void plus(int amount) {
        this.amount += amount;
    }
    /**
     * Снимает средства со счета.
     * @param amount - размер снимаемых средств
     * */
    public void minus(int amount) {
        this.amount -= amount;
    }

}
