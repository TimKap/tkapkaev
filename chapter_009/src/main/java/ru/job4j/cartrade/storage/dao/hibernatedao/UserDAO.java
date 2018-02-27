package ru.job4j.cartrade.storage.dao.hibernatedao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.job4j.cartrade.model.user.User;
import ru.job4j.cartrade.storage.dao.IUserDAO;

import java.util.List;

/**
 * Class UserDAO описывает hibernate реализацию IUserDAO.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 23.01.2018
 * */
public class UserDAO implements IUserDAO {

    /** hibernate сессия. */
    private final Session session;

    /**
     * Конструктор класса UserDAO.
     * @param session - hibernate сессия.
     * */
    public UserDAO(Session session) {
        this.session = session;
    }
    /**
     * Возвращает пользхователя из хранилища по id.
     * @param id - id пользователя
     * @return пользователь (null, если пользователь не найден)
     * */
    @Override
    public User get(long id) {
//        List<User> users = session.createQuery(String.format("FROM User u WHERE u.id =  %d", id)).list();
//        return users.size() != 0 ? users.get(0) : null;
        return session.get(User.class, id);
    }
    /**
     * Возвращает всх пользователей из хранилища.
     * @return список всех пользователей, находящихся в хранилище
     * */
    @Override
    public List<User> getAll() {
        List<User> users = session.createQuery("FROM User").list();
        return users;
    }

    /**
     * Добавляет пользователя в хранилище.
     * @param user - добаляемый пользователь.
     * @return добавленный пользователь
     * */
    @Override
    public User persist(User user) {
        session.save(user);
        return user;
    }
    /**
     * Обновляет пользователя, находящуюся в хранилище.
     * @param modifiedUser - обновленный пользователь
     * @return обновленная сущность
     * */
    @Override
    public User update(User modifiedUser) {
        session.update(modifiedUser);
        return modifiedUser;
    }
    /**
     * Удаляет из хранилища заданного пользователя.
     * @param user - удаляемый пользователь.
     * @return удаленная сущность
     * */
    @Override
    public User remove(User user) {
        session.remove(user);
        return user;
    }

    /**
     * Возвращает пользователя с заданным именем и паролем.
     * @param name - имя пользователя.
     * @param password - пароль пользователя
     * */
    @Override
    public User credential(String name, String password) {
        Query query = session.createQuery("FROM User u WHERE  (u.name = :name) AND (u.password = :password)");
        query.setParameter("name", name);
        query.setParameter("password", password);
        List<User> users = query.list();
        return users.isEmpty() ? null : users.get(0);
    }
}
