package ru.job4j.testtask;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Class TestBank содержит тесты для класса Bank.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 24.06.2017
 */
public class TestBank {

    /** Тест для метода addUser.*/
    @Test
    public void whenAddUserThenGetUserInTheBank() {
        Bank bank = new Bank();
        User user = new User("Tom", 12345);
        bank.addUser(user);
        assertThat(true, is(bank.getClients().containsKey(user)));
    }

    /**
     * Тест для метода deleteUser.
     * */
    @Test
    public void whenDeleteUSerThenGetNoOneUserInBank() {
        Bank bank = new Bank();
        User user = new User("Tom", 12345);
        bank.addUser(user);
        bank.deleteUser(user);
        assertThat(false, is(bank.getClients().containsKey(user)));

    }
    /**
     * Тест для метода addAccountToUser.
     * */
    @Test
    public void whenAddAccountToUserThenGetUSerWithAccount() {
        Bank bank = new Bank();
        User user = new User("Tom", 12345);
        bank.addUser(user);
        Account account =  new Account("789");
        bank.addAccountToUser(user, account);
        List<Account> accounts = bank.getClients().get(user);
        assertThat(true, is(accounts.contains(account)));

    }

    /**
     * Тест для метода вудуеуAccountToUser.
     * */
    @Test
    public void whenDeleteUserAccountThenGetEmptyAccountList() {
        Bank bank = new Bank();
        User user = new User("Tom", 12345);
        bank.addUser(user);
        Account account =  new Account("789");
        bank.addAccountToUser(user, account);
        bank.deleteAccountFromUser(user, "789");
        List<Account> accounts = bank.getClients().get(user);
        assertThat(false, is(accounts.contains(account)));

    }

    /**
     * Тест для метода getUserAccounts.
     * */
    @Test
    public void whenGetUserAccountsThenGetAccountsList() {
        Bank bank = new Bank();
        User user = new User("Tom", 12345);
        bank.addUser(user);
        List<Account> data = new ArrayList<Account>();
        data.addAll(Arrays.asList(new Account("111"), new Account("111"), new Account("222")));

        for (Account account:data) {
            bank.addAccountToUser(user, account);
        }
        List<Account> result = bank.getUserAccounts(user);
        assertThat(result.get(0), is(data.get(1)));
        assertThat(result.get(1), is(data.get(2)));

    }
    /**
     *  Тест для метода getUserAccountByRequisite.
     */
    @Test
    public void whenRequestAccountByRequisitThenGetAccount() {
        Bank bank = new Bank();
        User user = new User("Tom", 12345);
        bank.addUser(user);
        List<Account> data = new ArrayList<Account>();
        data.addAll(Arrays.asList(new Account("111"), new Account("111"), new Account("222")));
        for (Account account:data) {
            bank.addAccountToUser(user, account);
        }
        Account expected = data.get(2);
        Account result = bank.getUserAccountByRequisite(user, "222");

        assertThat(result, is(expected));

    }
    /**
     * Тест для метода transferMoney.
     * */
    @Test
    public void whenTransferMoneyThenOneUserHasMoreMoneuThenOther() {
        Bank bank = new Bank();
        /*Создать клиентов*/
        User user1 = new User("Tom", 12345);
        User user2 = new User("Rem", 11111);
        /*Добавить клиентов в банк*/
        bank.addUser(user1);
        bank.addUser(user2);
        /* Создать счета для клиентов */
        Account accountUser1 = new Account("5555", 100000d);
        Account accountUser2 = new Account("2222", 50000d);
        /* Добавить счета клиентам */
        bank.addAccountToUser(user1, accountUser1);
        bank.addAccountToUser(user2, accountUser2);

        /* Перевести средства со счета user1 на счет user2 */
        bank.transferMoney(user1, "5555", user2, "2222", 40000d);

        double resultValue1 = bank.getUserAccounts(user1).get(0).getValue();
        double resultValue2 = bank.getUserAccounts(user2).get(0).getValue();

        double expectedValue1 = 60000d;
        double expectedValue2 = 90000d;

        assertThat(resultValue1, closeTo(expectedValue1, 0.01));
        assertThat(resultValue2, closeTo(expectedValue2, 0.01));
    }
}
