package ru.job4j.hiddensynchronization;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class NonBlockingCacheTest содержит тесты для NonBlockingCache.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 29.08.2017
 */
public class NonBlockingCacheTest {

    /** сообщение .*/
    private String message = "";
    /**
     * Тест для add.
     * */
    @Test
    public void whenAddTwoElementsInDifferentThreadsThenGetTwoElementsInCache() {
         NonBlockingCache cache = new NonBlockingCache();
         Model tom = new Model("0", "Tom");
         Model ann = new Model("1", "Ann");

        Thread thTom = new Thread(() -> cache.add(tom));
        Thread thAnn = new Thread(() -> cache.add(ann));

        thTom.start();
        thAnn.start();

        try {
            thAnn.join();
            thTom.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        assertThat(cache.getContainer().get("0"), is(tom));
        assertThat(cache.getContainer().get("1"), is(ann));
    }

    /**
     * Тест для delete.
     * */
    @Test
    public void whenDeleteTwoElementsInDifferentThreadsThenGetEmptyCache() {
        NonBlockingCache cache = new NonBlockingCache();
        Model tom = new Model("0", "Tom");
        Model ann = new Model("1", "Ann");

        cache.add(tom);
        cache.add(ann);

        Thread thTom = new Thread(() -> cache.delete("0"));
        Thread thAnn = new Thread(() -> cache.delete("1"));

        thTom.start();
        thAnn.start();

        try {
            thAnn.join();
            thTom.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        assertThat(cache.getContainer().isEmpty(), is(true));
    }

    /**
     * Тест для delete.
     * */
    @Test
    public void whenUpdateElementThenGetCacheWithUpdatedElement() {
        NonBlockingCache cache = new NonBlockingCache();

        Model tom = new Model("0", "Tom");
        cache.add(tom);

        /* Genry заменит Tom*/
        Model genry = new Model("0", "Genry");
        Model bob = new Model("0", "Bob");


        Thread thGenry = new Thread(() -> {
            try {
                cache.update(genry);
            } catch (OptimisticException ex) {
                message = "catch exception Genry";
                System.out.println(message);
            }
        });

        Thread thBob = new Thread(() -> {
            try {
                cache.update(bob);
            } catch (OptimisticException ex) {
                message = "catch exception Bob";
                System.out.println(message);
            }
        });


        thGenry.start();
        thBob.start();

        try {
            thGenry.join();
            thBob.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (message.equals("catch exception Bob")) {
            assertThat(cache.getContainer().get("0"), is(genry));
        } else {
            if (message.equals("catch exception Genry")) {
                assertThat(cache.getContainer().get("0"), is(bob));
            } else {
                int counter = 0;
                if (cache.getContainer().contains(genry)) {
                    counter++;
                }
                if (cache.getContainer().contains(bob)) {
                    counter++;
                }
                assertThat(counter, is(1));

            }
        }

    }


}
