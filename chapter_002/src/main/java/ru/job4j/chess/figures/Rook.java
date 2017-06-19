package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImposibleMoveException;

/**
 * Class Rook описывает фигуру ладьи.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 15.05.2017
 */
public class Rook extends Figure {
    /**
     * Конструктор класса Rook.
     * @param position - расположение слона на шахматной доск
     * */
    public Rook(Cell position) {
        super(position);
    }

    /**
     * Ход ладьей.
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
        /* Проверка хода по вертикали или горизонтали*/
        if (!position.isFormsHorizontalLine(dist) & !position.isFormsVerticalLine(dist)) {
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
        return new Rook(dist);
    }

}
