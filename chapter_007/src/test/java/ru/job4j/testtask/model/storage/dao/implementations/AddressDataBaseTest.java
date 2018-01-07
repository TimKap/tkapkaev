package ru.job4j.testtask.model.storage.dao.implementations;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import ru.job4j.testtask.model.entities.Address;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
* Class AddressDataBaseTest содержит тесты для класса AddressDataBase.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 22.12.2017
* */
public class AddressDataBaseTest {

    /**
     * Пул соединений с тестируемой базой данных.
     * */
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
     * Тест для метода add и get.
     * @throws Exception -
     * */
    @Test
    public void whenAddAddressThenGetSameAddress() throws Exception {
        /* подготовка базы данных.*/
        AddressDataBase addresses = new AddressDataBase(connectionPool);
        addresses.removeAll();

        Address expected = new Address("Russia", "Moscow", "Teatralnaya");

        /* выполнение теста */
        addresses.add(expected);
        Address result = addresses.get(String.valueOf(expected.getId()));

        /* проверка результата */
        assertThat(result, is(expected));
        assertThat(result.getId(), is(expected.getId()));
        assertThat(result.getCountry(), is(expected.getCountry()));
        assertThat(result.getCity(), is(expected.getCity()));
        assertThat(result.getStreet(), is(expected.getStreet()));
    }

    /**
     *  Тест для метода remove.
     *  @throws Exception -
     * */
    @Test
    public void whenRemoveAddressThenGetEmptyDatabase() throws Exception {
        /* подготовка базы данных*/
        AddressDataBase addresses = new AddressDataBase(connectionPool);
        addresses.removeAll();
        Address address = new Address("Russia", "Moscow", "Teatralnaya");

        addresses.add(address);

        /* выполнение теста */
        addresses.remove(address);
        /* check result */
        Address result = addresses.get(String.valueOf(address.getId()));

        assertThat(result == null, is(true));

    }

    /**
     * Test for update method.
     * @throws Exception -
     * */
    @Test
    public void whenUpdateAddressThenGetUpdatedAddresses() throws Exception {
        /* подготовка базы данных*/
        AddressDataBase addresses = new AddressDataBase(connectionPool);
        addresses.removeAll();
        Address address = new Address("Russia", "Moscow", "Teatralnaya");
        addresses.add(address);

        /*выполнение теста */
        Address updatedAddress = new Address(address.getId(), "Switzerland", "Lusanne", "De. Valmont");
        addresses.update(updatedAddress);
        address = addresses.get(String.valueOf(updatedAddress.getId()));

        assertThat(address.getId(), is(updatedAddress.getId()));
        assertThat(address.getCountry(), is(updatedAddress.getCountry()));
        assertThat(address.getCity(), is(updatedAddress.getCity()));
        assertThat(address.getStreet(), is(updatedAddress.getStreet()));
    }

    /**
     * Test for getAllElements.
     * @throws Exception -
     * */
    @Test
    public void whenGetAllElementsThenGetListOfAddresses() throws Exception {

        /* prepare database*/
        AddressDataBase addresses = new AddressDataBase(connectionPool);
        addresses.removeAll();
        List<Address> expected = new ArrayList<>();
        expected.add(new Address("Russia", "Moscow", "Teatralnaya"));
        addresses.add(expected.get(0));

        /* execute test*/
        List<Address> result = addresses.getAllElements();
        assertThat(result, is(expected));
    }

}