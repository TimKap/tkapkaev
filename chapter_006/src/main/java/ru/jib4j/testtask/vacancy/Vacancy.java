package ru.jib4j.testtask.vacancy;

import java.util.Date;

/**
 * Class Vacancy описываетвакансию.
 * @author Timur Kapakev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 15.10.2017
 */
public class Vacancy {
    /** назавние вакансии. */
    private final String title;
    /** автор вакансии. */
    private final String author;
    /** дата обновления вакансии. */
    private final Date date;
    /**
     * Конструктор класса Vacancy.
     * @param title - заголовок вакансии
     * @param author - автор вакансии
     * @param date - дата последнего обновления
     * */
    public Vacancy(String title, String author, Date date) {
        this.title = title;
        this.author = author;
        this.date = date;
    }

    /**
     * Вовзращает заголовок вакансии.
     * @return заголовок вакансии
     * */
    public String getTitle() {
        return title;
    }
    /**
     * Вовзращает автора вакансии.
     * @return автор вакансии.
     * */
    public String getAuthor() {
        return author;
    }
    /**
     * Возвращает даьу обновления вакансии.
     * @return дата обновления вакансии
     * */
    public Date getDate() {
        return date;
    }

    /**
     * Задает правило сравнения вакансий.
     * @param o - вакансия с которой выполняется сравнение
     * @return true, если вакансии равны
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vacancy vacancy = (Vacancy) o;

        if (title != null ? !title.equals(vacancy.title) : vacancy.title != null) {
            return false;
        }
        return author != null ? author.equals(vacancy.author) : vacancy.author == null;
    }

    /**
     * Хеш-функция для объекта вакансии.
     * @return  хеш-код
     * */
    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}
