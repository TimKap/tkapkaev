package ru.job4j.chess;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.exceptions.ImposibleMoveException;
import ru.job4j.chess.exceptions.FigureNotFoundException;

/**
 * Class Board описывает шахматную доску.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 14.05.2017
 */
public class Board {
    /** Фигуры, размещенные на шахматной доске.*/
    private Figure[] figures;
    /**
     * Конструктор класса Board.
     * @param figures - размещенные на доске.
     * */
    public Board(Figure[] figures) {
        this.figures = figures;
    }


    /**
     * Возвращает массив фигур (для Теста).
     * @return - фигуры на доске
     * */
    public Figure[] getFigures() {
        return figures;
    }


    /**
     * Перемещает фигуру.
     * @param source - клетка из которой необходимо переместить фигуру
     * @param dist - клетка в которую необходимо переместить фигуру
     * @throws FigureNotFoundException - не найдена фигура, которой выполняется ход
     * @throws ImposibleMoveException - фигура не в состоянии выполнить указанное перемещение
     * @throws OccupiedWayException - не пути фигуры встречаются другие фигуры
     * */
    public void move(Cell source, Cell dist) throws FigureNotFoundException, ImposibleMoveException, OccupiedWayException {
        Figure figure = null;
        Cell[] trajectory;

        /* Определение фигуры, которая занимает ячейку*/
        for (Figure checkingFigure:figures) {
            figure = checkingFigure.checkCell(source);
            if (figure != null) {
                break;
            }
        }
        if (figure == null) {
            throw new FigureNotFoundException();
        }

        /* Создание траектории перемещения фигуры */
        trajectory = figure.way(dist);

        /* Проверка занятости траектории другими фигурыами */
        for (Cell cell:trajectory) {
            for (Figure checkingFigure:figures) {
                if (checkingFigure.checkCell(cell) != null) {
                    throw new OccupiedWayException();
                }
            }
        }
        /* Перемещаем фигуру на новое место*/
        for (int i = 0; i < figures.length; i++) {
            if (figures[i] == figure) {
                figures[i] = figure.clone(dist);
            }
        }

    }

}
