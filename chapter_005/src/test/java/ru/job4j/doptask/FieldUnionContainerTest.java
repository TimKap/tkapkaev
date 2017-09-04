package ru.job4j.doptask;

import org.junit.Test;
import ru.job4j.doptask.field.Field;
import ru.job4j.doptask.field.FieldUnionContainer;
import ru.job4j.doptask.field.Point;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class FieldUnionContainerTest содержит тесты дял класса FieldUnionContainer.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 03.09.2017
 */
public class FieldUnionContainerTest {

    /**
     * Тест для addWithUnion.
     * */
    @Test
    public void whenAddWithUnionThenGetModifiedContainer() {
       FieldUnionContainer container =  new FieldUnionContainer();
       /* Тест разбивается на две фазы проверки */

       /* Фаза 1: Добавить два непересекающихся поля.*/
        Field field1 = new Field(new Point[]{new Point(0, 1)});
        Field field2 = new Field(new Point[]{new Point(2, 1)});

        container.unionJoin(field1);
        container.unionJoin(field2);

        assertThat(container.getFieldContainer().containsAll(Arrays.asList(field1, field2)), is(true));
        /* Фаза 2: последовательно добавим 3 соприкасающихся поля, которые в итоге сольюся  в одно */
        Field field3 = new Field(new Point[]{new Point(0, 0)});
        Field field4 = new Field(new Point[]{new Point(1, 0)});
        Field field5 = new Field(new Point[]{new Point(2, 0)});

        container.unionJoin(field3);
        field1.joinField(field3);
        assertThat(Arrays.asList(field2, field1).containsAll(container.getFieldContainer()), is(true));

        container.unionJoin(field4);
        field1.joinField(field4);
        assertThat(Arrays.asList(field2, field1).containsAll(container.getFieldContainer()), is(true));

        container.unionJoin(field5);
        field1.joinField(field5);
        field1.joinField(field2);
        assertThat(Arrays.asList(field1).containsAll(container.getFieldContainer()), is(true));
    }

    /**
     * Тест для combine.
     */

    @Test
    public void whenCombineSomeFieldUnionContainersThenGetOneFieldUnionContainer() {

        Field field1 = new Field(new Point[]{new Point(0, 1)});
        Field field2 = new Field(new Point[]{new Point(2, 1)});
        Field field3 = new Field(new Point[]{new Point(0, 0)});
        Field field4 = new Field(new Point[]{new Point(1, 0)});
        Field field5 = new Field(new Point[]{new Point(2, 0)});

        FieldUnionContainer expectedFieldContainer = new FieldUnionContainer();
        expectedFieldContainer.unionJoin(field1);
        expectedFieldContainer.unionJoin(field2);
        expectedFieldContainer.unionJoin(field3);
        expectedFieldContainer.unionJoin(field4);
        expectedFieldContainer.unionJoin(field5);


        FieldUnionContainer unionContainer1 = new FieldUnionContainer();
        FieldUnionContainer unionContainer2 = new FieldUnionContainer();
        FieldUnionContainer unionContainer3 = new FieldUnionContainer();
        FieldUnionContainer unionContainer4 = new FieldUnionContainer();
        FieldUnionContainer unionContainer5 = new FieldUnionContainer();
        unionContainer1.unionJoin(field1);
        unionContainer2.unionJoin(field2);
        unionContainer3.unionJoin(field3);
        unionContainer4.unionJoin(field4);
        unionContainer5.unionJoin(field5);

        List<FieldUnionContainer> containerList = new ArrayList<>(Arrays.asList(unionContainer1, unionContainer2, unionContainer3, unionContainer4, unionContainer5));


        FieldUnionContainer result = FieldUnionContainer.combine(containerList);

        assertThat(expectedFieldContainer.getFieldContainer().equals(result.getFieldContainer()), is(true));


    }
}
