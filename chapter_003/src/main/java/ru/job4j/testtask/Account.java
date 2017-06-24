package ru.job4j.testtask;

/**
 * Class Account описывает счет пользователя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 24.06.2017
 */
public class Account {

    /** Реквизит счета. */
    private String requisites;
    /** Сумма на счете. */
    private double value;

    /**
     * Конструктор класса Account.
     * @param requisites - реквизит счета.
     * @param value - сумма неа счете
     * */
    public Account(String requisites, double value) {
        this.requisites = requisites;
        this.value = value;
    }
    /**
     * Конструктор класса Account.
     * @param requisites - реквизит счета.
     * */
    public Account(String requisites) {
        this(requisites, 0);
    }
    /**
     * Возвращает реквизит счета.
     * @return реквизит счета.
     * */
    public String getRequisites() {
        return requisites;
    }

    /**
     * Возвращает сумму денежных средств на счету.
     * @return сумма средств на счету
     * */
    public double getValue() {
        return value;
    }

    /**
     * Устанавливает новое значение на счету.
     * @param value - новое значение на счету
     * */
    public void setValue(double value) {
        this.value = value;
    }
    /**
     * Добавляет средства на счет.
     * @param value - добавляемые средства
     */
    public void addValue(double value) {
        this.value += value;
    }
    /**
     * Снимает средства со счета.
     * @param value - снимаемые средства
     * */
    public void takeValue(double value) {
        this.value -= value;
    }

    /**
     * Правило сравнения объектов класса Account.
     * @param o - объект, с котором выполняется сравнение.
     * @return логический результат сравнения
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        return requisites != null ? requisites.equals(account.requisites) : account.requisites == null;
    }

    /**
     * Возвращает значение хеш-функции для класса Account.
     * @return хеш-ключ
     * */
    @Override
    public int hashCode() {
        return requisites != null ? requisites.hashCode() : 0;
    }
}
