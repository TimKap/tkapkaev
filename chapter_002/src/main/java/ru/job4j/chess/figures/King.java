package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImposibleMoveException;

/**
 * Class King описывает фигуру короля.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 17.05.2017
 */
public class King extends Figure {
    /** Конструктор класса King.
     * @param position - расположение слона на шахматной доск
     * */
    public King(Cell position) {
        super(position);
    }

    /**
     * Ход королевой.
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
        /* Величина хода не превышает одной клетки*/
        if ((Math.abs(position.dX(dist)) > 1) || (Math.abs(position.dY(dist)) > 1)) {
            throw exception;
        }

        /* Траектория перемещения фигуры */
        return new Cell[] {dist};
    }
    /**
     * Замена фигуры.
     * @param dist - положение новой фигуры.
     * @return фигура с новым положением
     * */
    public Figure clone(Cell dist) {
        return new King(dist);
    }
}
