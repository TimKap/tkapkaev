package ru.job4j.testtask;

/**
 * Class PlayerBehaviour описывает поведение игрока.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 05.09.2017
 */
public class PlayerBehaviour implements Runnable {

    /** игрок. */
    private final Player player;

    /**
     * Конструктор класса PlayerBehaviour.
     * @param player - игрок
     * */
    public PlayerBehaviour(Player player) {
        this.player = player;
    }


    /**
     * Задача, реализующая поведение игрока.
     * */
    @Override
    public void run() {
        player.initStartPosition();
        while (true) {

            try {
                player.moveRight();
                Thread.sleep(2000);
                player.moveDown();
                Thread.sleep(2000);
                player.moveLeft();
                Thread.sleep(2000);
                player.moveUp();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
