package ru.job4j.start;


/**
 * Class StubInput описывает поведение пользолвателя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 06.04.2017
 */
public class StubInput implements Input {
    /**Поведение пользователя.*/
    private String[] answers;
    /** Порядковый номер действия пользователя.*/
    private int position;

    /**
     * Конструктор класса StubInput.
     * @param answers - поведение пользователя.
     * */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Ввод данных в приложение.
     * @param question - вопрос
     * @return - ответ на вопрос
     */
    public String ask(String question) {
        return answers[position++];
    }
}
