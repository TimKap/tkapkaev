package ru.job4j.testtask;

import ru.job4j.testtask.board.Board;
import ru.job4j.testtask.board.Coordinate;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Class BomberMan описывает игру.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 05.09.2017
 */
public class BomberMan {

    /** игровое пространство. */
    private final Board board;

    /** монстры, присутствующие в игре. */
    private final List<Monster> monsters = new ArrayList<>();
    /** игрок. */
    private final Player player;

    /**
     * Конструктор игры BomberMan.
     * @param size - размер игрового поля.
     * @param sizeLock - количество заблокированных ячеек
     * @param monstersNumber - количество монстров
     * */
    public BomberMan(int size, int sizeLock, int monstersNumber) {
        board = Board.newBoard(size, sizeLock);
        for (int i = 0; i < monstersNumber; i++) {
            monsters.add(new Monster(board));
        }
        player = new Player(board);
    }

    /** Запуск игры.
     * @param size - размер игрового поля.
     * @param sizeLock - количество заблокированных ячеек
     * @param monstersNumber - коичество монстров
     * */
    public static void startGame(int size, int sizeLock, int monstersNumber) {
        BomberMan bomberMan = new BomberMan(size, sizeLock, monstersNumber);

        Executor unitBehaviour = Executors.newCachedThreadPool();
        for (Monster monster : bomberMan.monsters) {
           unitBehaviour.execute(new MonsterBehaviour(monster));
        }
        unitBehaviour.execute(new PlayerBehaviour(bomberMan.player));

        new Thread(()-> {
            while (true) {
                String[][] str = new String[size][size];

                List<Coordinate> coordinates = new ArrayList<>();
                for (Monster monster : bomberMan.monsters) {
                    coordinates.add(monster.currentCoordinate());
                }
                int k = 0;
                for (Coordinate coordinate : coordinates) {
                    str[coordinate.getY()][coordinate.getX()] = String.format(" M%d ", k++);
                }
                str[bomberMan.player.currentCoordinate().getY()][bomberMan.player.currentCoordinate().getX()] = " P ";
                for (Coordinate coordinate : bomberMan.board.getBlockedCells()) {
                    str[coordinate.getY()][coordinate.getX()] = " X ";
                }
                for (int i = size - 1; i >= 0; i--) {
                    for (int j = 0; j < size; j++) {
                        System.out.print(str[i][j] + "  ");
                    }
                    System.out.println();
                }
                System.out.println("********************");
                System.out.println();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
