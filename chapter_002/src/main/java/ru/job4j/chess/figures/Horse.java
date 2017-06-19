package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImposibleMoveException;

/**
 * Class Horse описывает фигуру коня.
 * \@author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 15.05.2017
 */
public class Horse extends Figure {
    /**
     * Конструктор класса Horse.
     * @param position - расположение слона на шахматной доск
     * */
    public Horse(Cell position) {
        super(position);
    }

    /**
     * Ход конем.
     * @param dist - ячейка, в которую осуществляется ход
     * @return траектория перемещения фигуры, составленная из ячеек
     * @throws ImposibleMoveException - фигура не в состоянии выполнить указанное перемещение
     * */
    public Cell[] way(Cell dist) throws ImposibleMoveException {

        /* Текущее расположение фигуры */
        Cell position = getPosition();

        ImposibleMoveException exception = new ImposibleMoveException();

        /* Проверка корректности заданной координаты*/
        if (!dist.validCoordinate()) {
            throw exception;
        }
        /* Проверка хода конем */
        Cell tmpCell = null;
        boolean match = false;
        for (int i = 0; i < 4; i++) {
            /*Вращаем вектор вокруг ячйки, которую занимает конь */
            /* Поворотный вектор указывает на возможное положение коня на следующем ходе*/
            tmpCell = position.rotate(2, 1, i);
            if (tmpCell.eq(dist)) {
                match = true;
                break;
            }
            tmpCell = position.rotate(2, -1, i);
            if (tmpCell.eq(dist)) {
                match = true;
                break;
            }
        }
        if (!match) {
            throw new ImposibleMoveException();
        }
        return new Cell[] {tmpCell};
    }
    /**
     * Замена фигуры.
     * @param dist - положение новой фигуры.
     * @return фигура с новым положением
     * */
    public Figure clone(Cell dist) {
        return new Horse(dist);
    }

}
