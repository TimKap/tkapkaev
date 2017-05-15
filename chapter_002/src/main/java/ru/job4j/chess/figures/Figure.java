package ru.job4j.chess.figures;
import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImposibleMoveException;

/**
 * Class Figure описывает шахматную фигуру.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 14.05.2017
 */
public abstract class Figure {
    /** Ячейка, которую занимает фигура.*/
    private final Cell position;
    /**
     * Конструктор класса Figure.
     * @param position - расположение фигуры
     * */
    public Figure(Cell position) {
        this.position = position;
    }

    /***
     * Возвращает ячейку, которуяю занимает фигура.
     * @return занимаемая ячейка.
     */
    public Cell getPosition() {
        return position;
    }


    /**
     * Возвращает траекторию движения фигуры.
     * @param dist - ячейка, в которую следует переместить фигуру
     * @return траектория перемещения фигуры, составленная из ячеек
     * @throws ImposibleMoveException - фигура не в состоянии выполнить указанное перемещение
     * */
    public abstract Cell[] way(Cell dist) throws ImposibleMoveException;

    /**
     * Возвращает фигуру, если указанная фигура занимает проверяемую ячейку.
     * @param dist - проверяемая ячейка.
     * @return фигура.
     * */
    public Figure checkCell(Cell dist) {

        if ((position.getX() == dist.getX()) && (position.getY() == dist.getY())) {
            return this;
        } else {
            return null;
        }
    }

    /**
     * Замена фигуры.
     * @param dist - положение новой фигуры.
     * @return фигура с новым положением
     * */
    public abstract Figure clone(Cell dist);
}
