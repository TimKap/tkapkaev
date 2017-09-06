package ru.job4j.testtask;

import org.junit.Test;
/**
 * Class DemonstrateSecondStageTest демонстрирует второй этап игры BomberMan.
 * @author Timur Kapkaev (timur.kap@ayndex.ru)
 * @version $Id$
 * @since 06.09.2017
 */
public class DemonstrateSecondStageTest {
    /**
     * Демонстрация игры.
     * @throws InterruptedException - прерывание потока, находящегося в состоянии ожидания
     * */
    @Test
    public void demonstrateSecondStage() throws InterruptedException {
        BomberMan.startGame(3, 3, 3);
            Thread.sleep(10000);
    }
}
