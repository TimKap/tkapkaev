package ru.job4j.testtask.persistent.respositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.testtask.model.Account;

/**
 * Interface AccountRepository описывает интерфейс репозитория аккаунтов.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 15.03.2018
 * */
@Transactional
public interface AccountRepository extends CrudRepository<Account, String> {

    /**
     * Возвращает аккаунт по login.
     * @param login - login
     * @return аккаунгт
     * */
    Account findBylogin(String login);
}
