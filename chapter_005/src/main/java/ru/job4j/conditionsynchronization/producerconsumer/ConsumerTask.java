package ru.job4j.conditionsynchronization.producerconsumer;

/**
 * Class ConsumerTask описывает задачу поребителя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 24.08.2017
 */
public class ConsumerTask implements Runnable {

    /** Потребляемый продукт. */
    private final BlockingQueue<Integer> consumptionProduct;

    /**
     * Конструктор класса ConsumerTask.
     * @param consumptionProduct - потребляемый продукт
     * */
    public ConsumerTask(BlockingQueue<Integer> consumptionProduct) {
        this.consumptionProduct = consumptionProduct;
    }

    /**
     * Задача потребителя.
     * */
    @Override
    public void run() {
        while (true) {
            Integer number = consumptionProduct.dequeue();
            //String format = String.format();
            System.out.printf("Поток:  %s потребил ресурс: %d\r\n", Thread.currentThread().getName(), number);
        }
    }
}
