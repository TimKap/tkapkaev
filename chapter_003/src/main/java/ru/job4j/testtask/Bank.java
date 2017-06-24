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
     * Добавление пользователя.
     * @param user - пользователь
     * */
    public void addUser(User user) {
        if (!clients.containsKey(user)) {
            clients.put(user, new ArrayList<Account>());
        } else {
            System.out.println("This client exist in database already");
        }

    }

    /**
     * Удаление пользователя.
     * @param user - пользователь
     * */
    public void deleteUser(User user) {
        if (clients.containsKey(user)) {
            clients.remove(user);
        } else {
            System.out.println("There is no client in database");
        }
    }

    /**
     * Добавление счета пользователю.
     * @param user - пользователь.
     * @param account - счет пользователя
     * */
    public void addAccountToUser(User user, Account account) {
        if (clients.containsKey(user)) {
            List<Account> accounts = clients.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            } else {
                System.out.println("This client has this account already");
            }

        } else {
            System.out.println("There is no client in database");
        }
    }

    /**
     * Удаление счета пользователя.
     * @param user - пользователь.
     * @param account - счет пользователя
     * */
    public void deleteAccountFromUser(User user, Account account) {
        if (clients.containsKey(user)) {
            List<Account> accounts = clients.get(user);
            if (accounts.contains(account)) {
                accounts.remove(account);
            } else {
                System.out.println("This client doesn't have this account");
            }

        } else {
            System.out.println("There is no client in database");
        }
    }

    /**
     * Возвращает список счетов пользователя.
     * @param user - пользователь
     * @return счета пользователя
     * */
    public List<Account> getUserAccounts(User user) {
        if (clients.containsKey(user)) {
            return clients.get(user);
        } else {
            System.out.println("There is no client in database");
            return null;
        }
    }
    /**
     * Перевод средств со счета одного пользователя на счет другого пользователя.
     * @param srcUser - пользователь, со счета которого выполняется перевод
     * @param srcAccount - счет пользователя источника
     * @param dstUser - пользователь, на счет которого выполняется перевод средств
     * @param dstAccount - счет пользователя потребителя
     * @param amount - сумма для перевода средсьв.
     * @return успешность выполнения операции перевода
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        /* Проверка наличия пользователей в базе*/
        if (!clients.containsKey(srcUser) || !clients.containsKey(dstUser)) {
            System.out.println("Clients doesn't found in database");
            return false;
        } else {
            List<Account> srcAccounts = getUserAccounts(srcUser);
            List<Account> dstAccounts = getUserAccounts(dstUser);
            /* Проверка наличия счетов в базе */
            if (!srcAccounts.contains(srcAccount) || !dstAccounts.contains(dstAccount)) {
                System.out.println("Doesn't found account of user");
                return false;
            } else {
                Account currentSrcAccount = null;
                Account currentDstAccount = null;

                /* Получение текущих значений счетов из базы */
                for (Account account:srcAccounts) {
                    if (account.equals(srcAccount)) {
                        currentSrcAccount = account;
                        break;
                    }
                }
                for (Account account:dstAccounts) {
                    if (account.equals(dstAccount)) {
                        currentDstAccount = account;
                        break;
                    }
                }

                /**/
                if (currentSrcAccount.getValue() > amount) {
                    currentSrcAccount.takeValue(amount);
                    currentDstAccount.addValue(amount);

                    /* Обновление значений средств на запрашиваемых счетах */
                    srcAccount.setValue(currentSrcAccount.getValue());
                    dstAccount.setValue(currentDstAccount.getValue());

                    return true;
                } else {
                    /* Обновление значений средств на запрашиваемых счетах */
                    srcAccount.setValue(currentSrcAccount.getValue());
                    dstAccount.setValue(currentDstAccount.getValue());
                    System.out.println("Too little money on an account");
                    return false;
                }

            }

        }
    }

    /**
     * Возвращает клиентов банка.
     * @return клиенты банка
     * */
    public Map<User, List<Account>> getClients() {
        return clients;
    }
}
