package ru.job4j.conditionsynchronization.producerconsumer;

/**
 * Class ProducrConsumer демонстрирует задачу производитель-потребитель.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 24.08.2017
 */
public class ProducerConsumer {

    /**
     * Демонстрация задачи производитель - потребитель.
     * */
    public void demonsrate() {
        BlockingQueue<Integer> sharedResource = new BlockingQueue<>();
        Thread producer = new Thread(new ProducerTask(sharedResource));
        Thread consumer1 = new Thread(new ConsumerTask(sharedResource));
        Thread consumer2 = new Thread(new ConsumerTask(sharedResource));

        producer.start();
        consumer1.start();
        consumer2.start();

        try {
            producer.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Точка входа.
     * @param args - аргументы командной строки.
     * */
    public static void main(String[] args) {
        ProducerConsumer prodeucerconsumer = new ProducerConsumer();

        prodeucerconsumer.demonsrate();
    }
}
