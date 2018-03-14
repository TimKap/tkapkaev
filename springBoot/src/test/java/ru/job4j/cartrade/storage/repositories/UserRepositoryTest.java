package ru.job4j.cartrade.storage.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.job4j.cartrade.controller.main.CartradeTestData;
import ru.job4j.cartrade.model.user.User;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class USerRepositoryTest содержит тесты для USerRepository.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 14.03.2018
 * */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    /** userRepository. */
    @Autowired
    private UserRepository userRepository;

    /**
     * Тест для save и findOne.
     * */
    @Test
    public void whenSaveUserThenFindOneSameUser() {
        User user = CartradeTestData.prepareUser();
        user.setId(0L);
        userRepository.save(user);
        User result = userRepository.findOne(user.getId());
        assertThat(result, is(user));
    }


    /**
     * Тест для findByName.
     * */
    @Test
    public void whenFindByNameThenGetTom() {
        User tom = CartradeTestData.prepareUser();
        tom.setId(0L);
        userRepository.save(tom);

        User result = userRepository.findByName("Tom");
        assertThat(result, is(tom));
    }

    /**
     * Тест для findByNameAndPassword.
     * */
    @Test
    public void whenFindByNameAndPasswordThenGetTom() {
        User tom = CartradeTestData.prepareUser();
        tom.setId(0L);
        userRepository.save(tom);

        User result = userRepository.findByNameAndPassword("Tom", "tom");
        assertThat(result, is(tom));
    }


    /**
     * Тест для findAll.
     * */
    @Test
    public void whenFindAllThenGetTwoElements() {
        User tom1 = CartradeTestData.prepareUser();
        User tom2 = CartradeTestData.prepareUser();
        tom1.setId(0L);
        tom2.setId(0L);
        userRepository.save(tom1);
        userRepository.save(tom2);

        Iterator<User> iterator = userRepository.findAll().iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        assertThat(result, is(2));

    }

}