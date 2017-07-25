package ru.job4j.testtask;

import ru.job4j.testtask.command.Command;
import ru.job4j.testtask.command.FileCommand;
import ru.job4j.testtask.command.ICommandStream;
import ru.job4j.testtask.commandinterpretator.BookManagerCommand;
import ru.job4j.testtask.commandinterpretator.ICommandInterpretator;
import ru.job4j.testtask.orderbook.OrderBook;

import java.util.ArrayList;
import java.util.List;

/**
 * Class OrderBookManager описывает менеджер по работе с биржевым стаканами.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 23.07.2017
 */
public class OrderBookManager {

    /** Список биржевый стаканов. */
    private List<OrderBook> books;

    /** Интерпретатор команд биржевого стакана.*/
    private ICommandInterpretator interpretator;

    /**
     * Конструктор менеджера биржевых стаканов.
     * */
    public OrderBookManager() {
        books = new ArrayList<>();
        interpretator = new BookManagerCommand(books);
    }

    /**
     * Заполнить биржевые стакана заявками из файла.
     * @param fileName - файла
     * */
    public void processingFile(String fileName) {
        ICommandStream commandStream = FileCommand.openStream(fileName, BookManagerCommand.COMMANDS_LIST);
        Command command = commandStream.getCommand();
        while (command != null) {
            interpretator.execute(command);
            command = commandStream.getCommand();
        }
        commandStream.close();
    }
    /**
     * Вывод биржевых стаканов на консоль.
     * */
    public void printBooks() {
        for (OrderBook book:books) {
            System.out.printf("Order book : %s\n", book.getName());
            System.out.println("   BID         ASK");
            for (String record:book.representation()) {
                System.out.println(record);
            }
        }
    }
    /**
     * Возвращает биржевые стаканы.
     * @return биржевые стаканы
     * */
    public List<OrderBook> getOrderBooks() {
        return books;
    }

}
