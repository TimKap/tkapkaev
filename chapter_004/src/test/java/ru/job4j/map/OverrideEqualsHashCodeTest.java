package ru.job4j.map;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class OverrideEqualsTest содержит тест для OverrideEquals.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 14.07.2017
 */
public class OverrideEqualsHashCodeTest {

    /**
     * Тест для equals.
     * */
    @Test
    public void whenEqualsOverrideThenGetTrueComparison() {

        OverrideEqualsHashCode x = new OverrideEqualsHashCode(1, (float) 3.4, "VseOk");
        OverrideEqualsHashCode y = new OverrideEqualsHashCode(1, (float) 3.4, "VseOk");
        OverrideEqualsHashCode z = new OverrideEqualsHashCode(1, (float) 3.4, "VseOk");

        /*Рефлексия X = X*/
        assertThat(x.equals(x), is(true));

        /*Симметричность X=Y <=> Y = X*/
        assertThat(x.equals(y), is(true));
        assertThat(y.equals(x), is(true));

        /*Транзитивность X = Y, Y = Z  ->  X = Z */
        assertThat(y.equals(z), is(true));
        assertThat(x.equals(z), is(true));

        /*null*/
        assertThat(x.equals(null), is(false));

        /*неравные объекты*/
        OverrideEqualsHashCode notX = new OverrideEqualsHashCode(1, (float) 3.4, "notOk");
        assertThat(x.equals(notX), is(false));

    }
    /**
     * Тест для hashCode.
     * */
    @Test
    public void whenHashCodeThenExecuteHashFunction() {
        OverrideEqualsHashCode x = new OverrideEqualsHashCode(1, (float) 3.4, "VseOk");
        OverrideEqualsHashCode y = new OverrideEqualsHashCode(1, (float) 3.4, "VseOk");
        assertThat(x.hashCode() == y.hashCode(), is(true));
    }
}
