package ru.job4j.testtask.commandinterpretator;

import ru.job4j.testtask.Price;
import ru.job4j.testtask.command.Command;
import ru.job4j.testtask.order.Ask;
import ru.job4j.testtask.order.Bid;
import ru.job4j.testtask.order.Order;
import ru.job4j.testtask.orderbook.OrderBook;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Class BookManagerCommandInterpretator.
 */
public class BookManagerCommand implements ICommandInterpretator {

    /**
     * Список биржевых стаканов.
     */
    private List<OrderBook> books;

    /**
     * Список команд.
     */
    public static final List<String> COMMANDS_LIST = Arrays.asList("AddOrder", "DeleteOrder");

    /**
     * Конструктор BookManagerCommandInterpretator.
     * @param books - список биржевых стаканов
     */
    public BookManagerCommand(List<OrderBook> books) {
        this.books = books;
    }

    /**
     * Исполнить команду.
     * @param command - команда
     * */
    public void execute(Command command) {
        if (command.getName().equals(COMMANDS_LIST.get(0))) {
            addOrderToBooks(command.getAttributes());
        } else {
            if (command.getName().equals(COMMANDS_LIST.get(1))) {
                deleteOrderFromBooks(command.getAttributes());
            }
        }
    }

    /***
     * Команда двставки заявки с биржевой стакан.
     * @param attributes - список атрибутов команды
     */
    private void addOrderToBooks(HashMap<String, String> attributes) {
        OrderBook orderBook = getBookByName(attributes.get("book"));
        if (orderBook == null) {
            orderBook = addBook(attributes.get("book"));
        }
        String operation = attributes.get("operation");
        String id = attributes.get("orderId");
        Price price = new Price(attributes.get("price"));
        int volume = Integer.valueOf(attributes.get("volume"));

        Order order = null;
        if (operation.equals("SELL")) {
            order = new Ask(id, price, volume);
        } else {
            if (operation.equals("BUY")) {
                order = new Bid(id, price, volume);
            }
        }
        orderBook.add(order);
    }

    /**
     * Удаляет заявку из бирржевого стакана.
     * @param attributes - атрибуты команды
     * */
    private void deleteOrderFromBooks(HashMap<String, String> attributes) {
        OrderBook orderBook = getBookByName(attributes.get("book"));
        if (orderBook != null) {
            orderBook.remove(attributes.get("orderId"));
        }
    }

    /**
     * Ворзвращает биржевой стакана по имени.
     * @param bookName - имя биржэевого стакана.
     * @return биржевой стакан.
     */
    private OrderBook getBookByName(String bookName) {
        OrderBook orderBook = null;
        for (OrderBook book : books) {
            if (book.getName().equals(bookName)) {
                orderBook = book;
                break;
            }
        }
        return orderBook;
    }

    /**
     * Добавление нового биржевого стакана.
     * @param name - имя биржевого стакана
     * @return биржевой стакан
     */
    private OrderBook addBook(String name) {
        OrderBook orderBook = new OrderBook(name);
        books.add(orderBook);
        return orderBook;
    }

}
