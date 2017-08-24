package ru.job4j.conditionsynchronization.producerconsumer;

/**
 * Class ProducerTask описывает задачу произовдителя.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 24.08.2017
 */
public class ProducerTask implements Runnable {
    /** Формируемый рескрс.  */
    private final BlockingQueue<Integer> product;

    /**
     * Конструктор класса ProductTask.
     * @param product - сформированные задачей элементы
     * */
    public ProducerTask(BlockingQueue<Integer> product) {
        this.product = product;
    }

    /**
     * Задача формирования элементов.
     * */
    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            System.out.println("Ресурс: " + i + " готов");
            product.enqueue(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
