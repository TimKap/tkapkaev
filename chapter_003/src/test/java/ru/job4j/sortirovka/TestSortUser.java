package ru.job4j.sortirovka;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Class TestSortUser содержит тесты для SortUser.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 23.06.2017
 */
public class TestSortUser {

    /**
     * Тест метода sort.
     * */
    @Test
    public void whenSortListOfUserByAscendAgeTheGetSortedTree() {
        List<User> data = new ArrayList<User>();
        data.addAll(Arrays.asList(new User("Tom", 3), new User("Kate", 1), new User("Ted", 10)));

        User[] expected = new User[] {data.get(1), data.get(0), data.get(2)};

        SortUser sortUser = new SortUser();
        User[] result = new User[3];
        sortUser.sort(data).toArray(result);
        assertThat(result, is(expected));
    }

}
