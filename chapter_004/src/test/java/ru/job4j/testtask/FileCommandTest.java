package ru.job4j.testtask;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import ru.job4j.testtask.command.Command;
import ru.job4j.testtask.command.FileCommand;
import ru.job4j.testtask.commandinterpretator.BookManagerCommand;

/**
 * Class FileCommandTest содержит тесты для FileCommand.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 25.07.2017
 */
public class FileCommandTest {
    /**
     * Тест для метода openStream.
     */
    @Test
    public void whenOpenStreamThenGetCommandStream() {
        boolean expected = true;
        FileCommand fileCommand = FileCommand.openStream("C:/projects/tkapkaev/chapter_004/src/test/java/ru/job4j/testtask/ordersTest.xml", BookManagerCommand.COMMANDS_LIST);
        boolean result = false;
        if (fileCommand != null) {
            result = true;
        }
        assertThat(result, is(expected));
    }

    /**
     * Тест для метода getCommand.
     * */
    @Test
    public void whenGetCommandThenGetCommandFromTheFile() {

        FileCommand fileCommand = FileCommand.openStream("C:/projects/tkapkaev/chapter_004/src/test/java/ru/job4j/testtask/ordersTest.xml", BookManagerCommand.COMMANDS_LIST);

        /*Команда 1 */
        String expectedName = "AddOrder";
        String expectedId = "1";
        String expectedBook = "book-1";
        String expectedOperation = "SELL";
        String expectedPrice = "100.50";
        String expectedVolume = "81";

        Command command = fileCommand.getCommand();
        assertThat(command.getName(), is(expectedName));
        assertThat(command.getAttributes().get("orderId"), is(expectedId));
        assertThat(command.getAttributes().get("book"), is(expectedBook));
        assertThat(command.getAttributes().get("operation"), is(expectedOperation));
        assertThat(command.getAttributes().get("price"), is(expectedPrice));
        assertThat(command.getAttributes().get("volume"), is(expectedVolume));

        /*Команда 2 */
        expectedName = "AddOrder";
        expectedId = "10";
        expectedBook = "book-1";
        expectedOperation = "BUY";
        expectedPrice = "99.80";
        expectedVolume = "64";

        command = fileCommand.getCommand();
        assertThat(command.getName(), is(expectedName));
        assertThat(command.getAttributes().get("orderId"), is(expectedId));
        assertThat(command.getAttributes().get("book"), is(expectedBook));
        assertThat(command.getAttributes().get("operation"), is(expectedOperation));
        assertThat(command.getAttributes().get("price"), is(expectedPrice));
        assertThat(command.getAttributes().get("volume"), is(expectedVolume));

        /*Команда 3 */
        expectedName = "AddOrder";
        expectedId = "24";
        expectedBook = "book-1";
        expectedOperation = "SELL";
        expectedPrice = "100.50";
        expectedVolume = "39";

        command = fileCommand.getCommand();
        assertThat(command.getName(), is(expectedName));
        assertThat(command.getAttributes().get("orderId"), is(expectedId));
        assertThat(command.getAttributes().get("book"), is(expectedBook));
        assertThat(command.getAttributes().get("operation"), is(expectedOperation));
        assertThat(command.getAttributes().get("price"), is(expectedPrice));
        assertThat(command.getAttributes().get("volume"), is(expectedVolume));

        /*Команда 4 */
        expectedName = "DeleteOrder";
        expectedId = "1";
        expectedBook = "book-1";

        command = fileCommand.getCommand();
        assertThat(command.getName(), is(expectedName));
        assertThat(command.getAttributes().get("orderId"), is(expectedId));
        assertThat(command.getAttributes().get("book"), is(expectedBook));

        /* Команда 5 */
        Command expectedCommand = null;
        command = fileCommand.getCommand();
        assertThat(command, is(expectedCommand));

    }
}
