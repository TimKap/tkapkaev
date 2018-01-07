package ru.job4j.testtask.model.storage.dao.implementations;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import ru.job4j.testtask.model.entities.MusicStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Class MusicStyleDateBaseTest содержит тесты для MusicStyleDataBase.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 22.12.2017
 * */

public class MusicStyleDataBaseTest {

    /** database connection pool. */
    private final BasicDataSource connectionPool;

    {
        BasicDataSource pool = null;
        try {
            pool = new TestDataBasePoolConnection().connectionPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connectionPool = pool;
    }

    /**
     * Test for add and get methods.
     * @throws Exception -
     * */
    @Test
    public void whenAddMusicStyleThenGetSameMusicType() throws Exception {
        /* prepare database */
        MusicStyleDataBase musicStyles = new MusicStyleDataBase(connectionPool);
        musicStyles.removeAll();

        MusicStyle musicStyle = new MusicStyle("ROCK");

        /* execute test*/
        musicStyles.add(musicStyle);

        /*check test*/
        MusicStyle result = musicStyles.get(String.valueOf(musicStyle.getId()));

        assertThat(result, is(musicStyle));
        assertThat(result.getStyle(), is(musicStyle.getStyle()));

    }

    /**
     * Test for update methods.
     * @throws Exception -
     * */
    @Test
    public void whenUpdateMusicStyleThenGetUpdatedStyle() throws Exception {
        /* prepare database */
        MusicStyleDataBase musicStyles = new MusicStyleDataBase(connectionPool);
        musicStyles.removeAll();

        MusicStyle musicStyle = new MusicStyle("ROCK");
        musicStyles.add(musicStyle);

        /*execute test*/
        musicStyle.setStyle("CLASSIC");
        musicStyles.update(musicStyle);

        /*check test*/
        MusicStyle result = musicStyles.get(String.valueOf(musicStyle.getId()));

        assertThat(result, is(musicStyle));
        assertThat(result.getStyle(), is(musicStyle.getStyle()));

    }

    /**
     * Test for remove method.
     * @throws Exception -
     * */
    @Test
    public void whenRemoveMusicStyleThenGetEmptyDatabase() throws Exception {
        /* prepare database*/
        MusicStyleDataBase musicTypes = new MusicStyleDataBase(connectionPool);
        musicTypes.removeAll();
        MusicStyle musicStyle = new MusicStyle("ROCK");

        musicTypes.add(musicStyle);

        /* execute test */
        musicTypes.remove(musicStyle);

        /* check result */
        MusicStyle result = musicTypes.get(String.valueOf(musicStyle.getId()));

        assertThat(result == null, is(true));

    }

    /**
     * Test for getAllElements.
     * @throws Exception -
     * */
    @Test
    public void whenGetAllElementsThenGetListOfMusicStyles() throws Exception {
        /* prepare database*/
        MusicStyleDataBase musicStyles = new MusicStyleDataBase(connectionPool);
        musicStyles.removeAll();
        List<MusicStyle> expected = new ArrayList<>();
        expected.add(new MusicStyle("ROCK"));
        expected.add(new MusicStyle("CLASSIC"));
        musicStyles.add(expected.get(0));
        musicStyles.add(expected.get(1));

        /* execute test*/
        List<MusicStyle> result = musicStyles.getAllElements();
        assertThat(result.containsAll(expected), is(true));
    }


}