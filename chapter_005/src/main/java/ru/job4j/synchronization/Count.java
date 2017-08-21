package ru.job4j.synchronization;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class Count описывает потокобезопасный класс счетчика.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 21.08.2017
 */
@ThreadSafe
public class Count {
    /** счетчик. */
    @GuardedBy(value = "this")
    private int counter;

    /**
     * Инкремент счетчика.
     * @return состояние счетчика.
     * */
    public synchronized int increment() {
        return ++counter;
    }
}
