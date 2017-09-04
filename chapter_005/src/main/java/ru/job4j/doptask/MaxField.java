package ru.job4j.doptask;

import ru.job4j.doptask.field.Field;
import ru.job4j.doptask.field.FieldUnionContainer;
import ru.job4j.doptask.field.Point;
import ru.job4j.doptask.space.Cell;
import ru.job4j.doptask.space.Space;



/**
 * Class MaxField поиска поля, которое закрашено максимальным числом единиц, прилегающих друг к другу.
 *
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 03.09.2017
 */
public class MaxField {


    /**
     * Определяет множество, содержащее максимальное число соприкасающихся клеток значения которых соответствуют единице.
     *
     * @param space - заданное пространство значений
     * @return множество, содержащее максимальное число соприкасающихся клеток значения которых соответствует единице
     */
    public int searchMaximumField(Space space) {

        FieldUnionContainer fieldContainer = new FieldUnionContainer();
        Cell[][] cells = space.getSpace();
        for (int i = 0; i < space.getSizeY(); i++) {
            for (int j = 0; j < space.getSizeX(); j++) {
                if (cells[i][j].getValue() == 1) {
                    fieldContainer.unionJoin(new Field(new Point[]{cells[i][j].getCoordinate()}));
                }
            }
        }

        int max = 0;
        for (Field field : fieldContainer.getFieldContainer()) {
            if (max < field.getFieldsCore().size()) {
                max = field.getFieldsCore().size();
            }
        }
        return max;
    }
}
