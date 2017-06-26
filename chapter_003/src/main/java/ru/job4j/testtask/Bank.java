package ru.job4j.testtask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class Bank описывает работу банка.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 24.06.2017
 */
public class Bank {
    /** Клиенты банка. */
    private Map<User, List<Account>> clients;
    /**
     * Конструктор класса Bank.
     * */
    public Bank() {
        this.clients = new HashMap<User, List<Account>>();
    }


    /**
     * Возвращает клиентов банка.
     * @return клиенты банка
     * */
    public Map<User, List<Account>> getClients() {
        return clients;
    }

    /**
     * Добавление пользователя.
     * @param user - пользователь
     * @return состояние выполнения операции
     * */
    public boolean addUser(User user) {
        return (clients.put(user, new ArrayList<Account>()) != null);
    }

    /**
     * Удаление пользователя.
     * @param user - пользователь
     * @return пользователя, вслучае успешного удаления, иначе null/
     * */
    public boolean deleteUser(User user) {
        return (clients.remove(user) != null);
    }

    /**
     * Возвращает список счетов пользователя.
     * @param user - пользователь
     * @return счета пользователя
     * */
    public List<Account> getUserAccounts(User user) {
        List<Account> accounts = clients.get(user);
        if (accounts == null) {
            accounts = new ArrayList<Account>();
        }
        return accounts;
    }

    /**
     * Возвращает счет пользователя по его реквизиту.
     * @param user - пользователь
     * @param requisite - реквизит
     * @return счет пользователя
     * */
    public Account getUserAccountByRequisite(User user, String requisite) {
        List<Account> accounts = getUserAccounts(user);
        int i = accounts.indexOf(new Account(requisite));
        if (i >= 0) {
            return accounts.get(i);
        } else {
            return null;
        }
    }

    /**
     * Добавление счета пользователю.
     * @param user - пользователь.
     * @param account - счет пользователя
     * @return состояние выполнения лперации
     * */
    public boolean addAccountToUser(User user, Account account) {
        List<Account> accounts = getUserAccounts(user);
        boolean isOperationSuccessful = false;
        if (!accounts.contains(account)) {
            accounts.add(account);
            isOperationSuccessful = true;
        }
        return isOperationSuccessful;
    }

    /**
     * Удаление счета пользователя.
     * @param user - пользователь.
     * @param requisite - счет пользователя
     * @return удаленный счет
     * */
    public Account deleteAccountFromUser(User user, String requisite) {
        List<Account> accounts = getUserAccounts(user);
        Account deletedAccount = getUserAccountByRequisite(user, requisite);
        if (accounts.remove(deletedAccount)) {
            return deletedAccount;
        } else {
            return null;
        }
    }

    /**
     * Перевод средств со счета одного пользователя на счет другого пользователя.
     * @param srcUser - пользователь, со счета которого выполняется перевод
     * @param srcRequisite - счет пользователя источника
     * @param dstUser - пользователь, на счет которого выполняется перевод средств
     * @param dstRequisite - счет пользователя потребителя
     * @param amount - сумма для перевода средсьв.
     * @return успешность выполнения операции перевода
     */
    public boolean transferMoney(User srcUser, String srcRequisite, User dstUser, String dstRequisite, double amount) {

        /* Вернуть счета клиентов */
        Account srcAccount = getUserAccountByRequisite(srcUser, srcRequisite);
        Account dstAccount = getUserAccountByRequisite(dstUser, dstRequisite);

        boolean isOperationSuccessful = false;

        if ((srcAccount != null) && (dstAccount != null)) {
            /*Проверить состояние счета*/
            if (srcAccount.getValue() >= amount) {
                srcAccount.takeValue(amount);
                dstAccount.addValue(amount);
                isOperationSuccessful = true;
            }
        }
        return isOperationSuccessful;
    }

}
