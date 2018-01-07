package ru.job4j.testtask.model.storage.dao.implementations;

import org.apache.commons.dbcp.BasicDataSource;
import ru.job4j.testtask.model.entities.MusicStyle;
import ru.job4j.testtask.model.storage.dao.exception.OperationException;
import ru.job4j.testtask.model.storage.dao.interfaces.IMusicStyleDAO;
import ru.job4j.testtask.model.storage.orm.ORMException;
import ru.job4j.testtask.model.storage.orm.SimplifyORM;
import ru.job4j.testtask.model.storage.transaction.Transaction;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;


/**
 * Class MusicStyleDataBase описывает хранилище стилей музыки в базе данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 13.12.2017
 * */
public class MusicStyleDataBase extends EntityDataBase implements IMusicStyleDAO {
    /**
     * Конструктор MusicStyleDataBase.
     * @param connectionPool - пул соединений
     * */
    MusicStyleDataBase(BasicDataSource connectionPool) {
        super(connectionPool);
    }

    /**
     * Конструктор MusicDataBase.
     * @param connection - соединение с базой данных
     * */
   MusicStyleDataBase(Connection connection) {
        super(connection);
    }

    /**
     * Возвращает список всех музыкальных стилей.
     * @return ссписок музыкальных стилей
     * @throws OperationException - ошибка выполнения операции
     * */
    @Override
    public List<MusicStyle> getAllElements() throws OperationException {
        ArrayList<MusicStyle> musicStyles = new ArrayList<>();
        try (Connection con = getConnection()) {
             Transaction<Collection<MusicStyle>> getAllMusicStyles = new Transaction<Collection<MusicStyle>>(con) {
                 @Override
                 public Collection<MusicStyle> runStatements() throws SQLException {
                     try {
                         SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                         Set<Integer> musicStylesId = simplifyORM.getAllElementsId(MusicStyle.class);
                         HashMap<String, Set> matches = new HashMap<>();
                         matches.put("id", musicStylesId);
                         return simplifyORM.get(matches, MusicStyle.class);
                     } catch (ORMException e) {
                         SQLException sqe = new SQLException();
                         sqe.initCause(e);
                         throw sqe;
                     }
                 }
             };
             getAllMusicStyles.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
             musicStyles.addAll(getAllMusicStyles.execute());
            return musicStyles;
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }
    }

    /**
     * Возвращает музыкальный стиль по ключу.
     * @param key - ключ (ключ - название музыкального стиля))
     * @return музыкальный стиль (null, если музыкальный стиль не найден)
     * @throws OperationException - ошибка оп ерации возвращения адреса
     * */
    @Override
    public MusicStyle get(String key) throws OperationException {
        try (Connection con = getConnection()) {
            Transaction<MusicStyle> getMusicStyle = new Transaction<MusicStyle>(con) {
                @Override
                public MusicStyle runStatements() throws SQLException {
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        HashSet<Integer> musicStylesId = new HashSet<>();
                        musicStylesId.add(Integer.valueOf(key));
                        HashMap<String, Set> matches = new HashMap<>();
                        matches.put("id", musicStylesId);
                        Iterator<MusicStyle> iterator =  simplifyORM.get(matches, MusicStyle.class).iterator();
                        return iterator.hasNext() ? iterator.next() : null;
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };
            getMusicStyle.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            return getMusicStyle.execute();
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }
    }

    /**
     *  Добавляет музыкальный стиль в базу данных.
     *  @param element - добавляемый музыкальный стиль
     *  @return добавленный музыкальный стиль
     *  @throws OperationException - ошибка выполнения операции
     * */
    @Override
    public MusicStyle add(MusicStyle element) throws OperationException {
        try (Connection con = getConnection()) {
            Transaction addMusicStyle = new Transaction(con) {
                @Override
                public Object runStatements() throws SQLException {
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        ArrayList<MusicStyle> musicStyles = new ArrayList<>();
                        musicStyles.add(element);
                        simplifyORM.insert(musicStyles, MusicStyle.class);
                        return null;
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };
            addMusicStyle.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            addMusicStyle.execute();
            return element;
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }
    }

    /**
     *  Удаляет музыкальный стиль из базы данных.
     *  @param element - удаляемый музыкальный стиль
     *  @return удаленный музыкальный стиль
     *  @throws OperationException - ошибка выполнения операции
     * */
    @Override
    public MusicStyle remove(MusicStyle element) throws OperationException {
        try (Connection con = getConnection()) {
            Transaction removeMusicStyle = new Transaction(con) {
                @Override
                public Object runStatements() throws SQLException {
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        ArrayList<MusicStyle> musicStyles = new ArrayList<>();
                        musicStyles.add(element);
                        simplifyORM.remove(musicStyles, MusicStyle.class);
                        return null;
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };
            removeMusicStyle.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            removeMusicStyle.execute();
            return  element;
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }
    }

    /**
     * Update music style with specifies id.
     * @param modification модифицированный стиль музыки.
     * @return модифицированный стиль музыки.
     * @throws OperationException - ошибка выполнения операции
     * */
    @Override
    public MusicStyle update(MusicStyle modification) throws OperationException {
        try (Connection con = getConnection()) {
            Transaction updateMusicStyle = new Transaction(con) {
                @Override
                public Object runStatements() throws SQLException {
                    try {
                        SimplifyORM simplifyORM = new SimplifyORM(getTransactionConnection());
                        ArrayList<MusicStyle> musicStyles = new ArrayList<>();
                        musicStyles.add(modification);
                        simplifyORM.update(musicStyles, MusicStyle.class);
                        return null;
                    } catch (ORMException e) {
                        SQLException sqe = new SQLException();
                        sqe.initCause(e);
                        throw sqe;
                    }
                }
            };
            updateMusicStyle.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
            updateMusicStyle.execute();
            return modification;
        } catch (SQLException e) {
            OperationException ex = new OperationException();
            ex.initCause(e);
            throw ex;
        }
    }

    /**
     * Clear table with music styles.
     * @throws SQLException - ошибка операции
     * */
    void removeAll() throws SQLException {
        try (Connection con = getConnection();
             Statement st = con.createStatement()) {
            st.execute("DELETE FROM musicstyles");
        }
    }
}
