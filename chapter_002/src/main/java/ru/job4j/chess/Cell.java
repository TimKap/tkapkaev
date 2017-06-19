package ru.job4j.chess;
/**
 * Class Cell описывает шахматную ячейку.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 14.05.2017
 */
public class Cell {
    /** Координата ячейки по горизонтали.*/
    private final int x;
    /** Координата ячейки по вертикали.*/
    private final int y;

    /** Размер шахматной доски.*/
    private static final int BOARDSIZE = 8;

    /**
     * Конструктор класса Cell.
     * @param x - координата ячейки по горизонтали
     * @param y - координата ячейки по вретикали
     * */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Возвращает горизонтальную координату ячейки.
     * @return горизонтальная координата ячейки
     * */
    public int getX() {
        return x;
    }

    /**
     * Возвращает вертикальную координату ячейки.
     * @return вертикальная координата ячейки
     * */
    public int getY() {
        return y;
    }

    /**
     * Приращение по когризонтальной оси.
     * @param dist - ячейка назначения
     * @return приращение по гризонтальной сои
     * */
    public int dX(Cell dist) {
        return dist.x - x;
    }

    /**
     * Приращение по вертикальной оси.
     * @param dist - ячейка назначения
     * @return приращение по гвертикальной сои
     * */
    public int dY(Cell dist) {
        return dist.y - y;
    }

    /**
     * Возвращает траекторию до ячейки назначения.
     * @param dist - ячейка назначения
     * @return массив ячеек, представляющий траекторию перемещения до ячейки назначения.
     * */
    public Cell[] trajectoryLine(Cell dist) {

        /* Определяется приращения координат */
        int dX = dX(dist);
        int dY = dY(dist);
        /* Определяется число создаваемых ячеек */
        int size = Math.max(Math.abs(dX), Math.abs(dY));
        /* Определяется направление движения */
        dX = sign(dX);
        dY = sign(dY);
        int counter  = 0;
        Cell[] trajectory = new Cell[size];
        for (int i = x + dX,  j = y + dY; counter < size; i += dX, j += dY) {
            trajectory[counter++] = new Cell(i, j);
        }
        return trajectory;
    }

    /**
     * Возвращает ячейку, которая получается поворотом относительно текущей ячейки на угол кратный 90 градусов.
     * Входными значениями являются координаты поворотного вектора в начальном положении.
     * Поворотный вектор соединяет текущую ячейку и ячеку которукю следует повернуть.
     * @param rotX - текущая вертикальная координата поворотного вектора
     * @param rotY - текущая вертикальная координата поворотного вектора
     * @param k - число попоротов против хода часовой стрелки
     * @return ячейка на которую указывает
     * */
    public Cell rotate(int rotX, int rotY, int k) {
        int x = rotX;
        int y = rotY;
        int buf;
        for (int i = 0; i < k; i++) {
            buf = x;
            x = -y;
            y = buf;
        }
        return new Cell(this.x + x, this.y + y);
    }

    /**
     * Проверка равенства ячеек.
     * @param cell - ячейка с которой выполняется сравнение
     * @return логическое значение результата сравнения
     * */
    public boolean eq(Cell cell) {
        return (cell.x == x) && (cell.y == y);
    }


    /**
     * Проверка возможности образования исходной ячейки и ячеки назначения прямой с углом наклона 45 градусов.
     * @param dist - ячейка назначения
     * @return логическое значение.
     * */
    public boolean isFormsDiagonal(Cell dist) {
        int b = y - x;
        boolean result = true;
        /* Образуют правую диагональ? */
        if (dist.getY() != (dist.getX() + b)) {
            b = y + x;
            /* Образуют правую диагональ */
            if (dist.getY() != (-dist.getX() + b)) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Проверка возможности образования исходной ячейки и ячеки назначения вертикальной линии.
     * @param dist - ячейка назначения
     * @return логическое значение.
     * */
    public boolean isFormsVerticalLine(Cell dist) {
        boolean result = false;
        /* Образуют вертикальнгую линию ?*/
        if (dX(dist) == 0) {
            result = true;
        }
        return result;
    }

    /**
     * Проверка возможности образования исходной ячейки и ячеки назначения горизонтальной линии.
     * @param dist - ячейка назначения
     * @return логическое значение.
     * */
    public boolean isFormsHorizontalLine(Cell dist) {
        boolean result = false;
        /* Образуют вертикальнгую линию ?*/
        if (dY(dist) == 0) {
            result = true;
        }
        return result;
    }

    /***
     * Возвращает знак для целого числа.
     * @param a - число
     * @return знак целого числа
     */
    public static int sign(int a) {
        if (a > 0) {
            return 1;
        } else {
            if (a < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }
    /**
     * Проверка корректности заданных координат ячейки.
     * @return корректность заданных координат ячейки
     * */
    public boolean validCoordinate() {
        if ((x >= BOARDSIZE) || (x < 0)) {
            return false;
        }
        if ((y >= BOARDSIZE) || (y < 0)) {
            return false;
        }
        return true;
    }


}
