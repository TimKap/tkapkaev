package ru.job4j.testtask;

/**
 * Class MonsterBehaviour описывает поведение монстра.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 05.09.2017
 */
public class MonsterBehaviour implements Runnable {

    /** монстер. */
    private final Monster monster;

    /**
     * Конструктор класса MonsterBehaviour.
     * @param monster - монстер
     * */
    public MonsterBehaviour(Monster monster) {
        this.monster = monster;
    }

    /**
     * Задача, реализующая поведение монстра.
     * */
    @Override
    public void run() {
        monster.initStartPosition();
        try {

            while (true) {
                monster.autoAction();
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
