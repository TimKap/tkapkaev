package ru.job4j.testtask;

import org.junit.Test;


import ru.job4j.testtask.board.Board;
import ru.job4j.testtask.board.Coordinate;

/**
 * Class содержит демоснтрацию первого этапа тестового задания.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 02.09.2017
 */
public class DemonstrateFirstStageTest {
    /**
     * Демонстрирует первый этап тестового задания.
     */
    @Test
    public void demonstrateFirstStage() {
        Board board = new Board(2);
        Thread th = new Thread() {
            @Override
            public  void run() {
                Monster monster = new Monster(board);
                while (true) {
                    try {
                        Coordinate coordinate = monster.autoAction();
                        System.out.printf("Thread: %s X: %d Y: %d\n\r", Thread.currentThread().getName(), coordinate.getX(), coordinate.getY());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }

                }
            }

        };

        Thread th2 = new Thread() {
            @Override
            public  void run() {
                Monster monster = new Monster(board);
                while (true) {
                    try {
                        Coordinate coordinate = monster.autoAction();
                        System.out.printf("Thread: %s X: %d Y: %d\n\r", Thread.currentThread().getName(), coordinate.getX(), coordinate.getY());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }

                }
            }

        };

        th.start();
        th2.start();

        try {
            th.join(5000);
            th2.join(5000);
            th.interrupt();
            th2.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
