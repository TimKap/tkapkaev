package ru.job4j.chess.figures;
import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImposibleMoveException;

/**
 * Class Bishop описывает шахматную фигуру слона.
 * @author Timur Kapkaev (timur.kap@yandedx.ru)
 * @version $Id$
 * @since 14.05.2017
 */
public class Bishop extends Figure {
    /**
     * Конструктор класса Bishop.
     * @param position - расположение слона на шахматной доск
     * */
    public Bishop(Cell position) {
         super(position);
    }

    /**
     * Ход слоном.
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
        /* Проверка образования диагонали ячейкой назначения*/
        if (!position.isFormsDiagonal(dist)) {
                throw exception;
            }

        /* Траектория перемещения фигуры */
        return position.trajectoryLine(dist);
    }
    /**
     * Замена фигуры.
     * @param dist - положение новой фигуры.
     * @return фигура с новым положением
     * */
    public Figure clone(Cell dist) {
        return new Bishop(dist);
    }

}