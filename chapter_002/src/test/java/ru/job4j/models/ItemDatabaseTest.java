package ru.job4j.models;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class ItemDatabaseTest содержит тесты для ItemDatabase.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 18.09.2017
 */
public class ItemDatabaseTest {

    /**
     * Тест add-get.
     * @throws Exception при нарушении создания объекта ItemDatabase
     * */
    @Test
    public void whenAddRecordToDatabaseThenGetRecord() throws Exception {
        ItemDatabase database = ItemDatabase.newInstance();
        database.clean();
        database.add(new Item("test_name", "test_description"));
        Item result = database.findByID("1");
        assertThat(result.getId(), is("1"));
        assertThat(result.getName(), is("test_name"));
        assertThat(result.getDescription(), is("test_description"));
    }

    /**
     * Тест findAll.
     * @throws Exception при нарушении создания объекта ItemDatabase
     * */
    @Test
    public void whenFindAllThenGetAllRecords() throws Exception {
        ItemDatabase database = ItemDatabase.newInstance();
        database.clean();
        List<Item> expected = new ArrayList<>(Arrays.asList(new Item("test_name_1", "test_description"), new Item("test_name_2", "test_description"), new Item("test_name_3", "test_description")));
        database.add(expected.get(0));
        database.add(expected.get(1));
        database.add(expected.get(2));

        expected.get(0).setId("1");
        expected.get(1).setId("2");
        expected.get(2).setId("3");

        List<Item> result = database.findAll();
        assertThat(result, is(expected));

    }

    /**
     * Тест delete.
     * @throws Exception при нарушении создания объекта ItemDatabase
     * */
    @Test
    public void whenDeleteThenGetDatabaseWithoutOneRecord() throws Exception {
        ItemDatabase database = ItemDatabase.newInstance();
        database.clean();
        List<Item> value = new ArrayList<>(Arrays.asList(new Item("test_name_1", "test_description"), new Item("test_name_2", "test_description"), new Item("test_name_3", "test_description")));
        database.add(value.get(0));
        database.add(value.get(1));
        database.add(value.get(2));

        value.get(0).setId("1");
        value.get(1).setId("2");
        value.get(2).setId("3");


        database.delete(value.get(1));

        List<Item> result = database.findAll();
        assertThat(result.get(0), is(value.get(0)));
        assertThat(result.get(1), is(value.get(2)));
    }

    /**
     * Тест для update.
     * @throws Exception при нарушении создания объекта ItemDatabas
     * */
    @Test
    public void whenUpdateThenGetModifiedRecord() throws Exception {
        ItemDatabase database = ItemDatabase.newInstance();
        database.clean();
        database.add(new Item("test_name", "test_description"));


        Item modifiedItem = new Item("test_name_modified", "test_description_modified");
        modifiedItem.setId("1");
        database.update(modifiedItem);

        Item result = database.findByID("1");
        assertThat(result.getId(), is("1"));
        assertThat(result.getName(), is("test_name_modified"));
        assertThat(result.getDescription(), is("test_description_modified"));
    }

    /**
     * Тест для findByName.
     * @throws Exception при нарушении создания объекта ItemDatabas
     * */
    @Test
    public void whenFindByNameThenGetAllItemsWithSearchedName() throws Exception {
        ItemDatabase database = ItemDatabase.newInstance();
        database.clean();
        database.add(new Item("test_name", "test_description"));
        List<Item> result = database.findByName("test_name");
        assertThat(result.get(0).getId(), is("1"));
        assertThat(result.get(0).getName(), is("test_name"));
        assertThat(result.get(0).getDescription(), is("test_description"));

    }
}
