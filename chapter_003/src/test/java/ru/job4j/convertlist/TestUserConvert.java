package ru.job4j.convertlist;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class TestUserConvert содержит тесты дял класса UserConvert.
 * @author TimurKapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 22.06.2017
 */
public class TestUserConvert {

    /**
     * Тест для метода process.
     * */
    @Test
    public void whenConvertListOfUsersThenGetTableOfUsersWithKeyById() {

        List<User> data = new ArrayList<User>();
        Collections.addAll(data, new User(1500, "Vasja", "Kirov"), new User(3000, "Olga", "Orel"));

        HashMap<Integer, User> expectedMap = new HashMap<Integer, User>();
        expectedMap.put(1500, data.get(0));
        expectedMap.put(3000, data.get(1));

        UserConvert userConvert = new UserConvert();
        HashMap<Integer, User> resultMap = userConvert.process(data);

        assertThat(resultMap, is(expectedMap));

    }
}
