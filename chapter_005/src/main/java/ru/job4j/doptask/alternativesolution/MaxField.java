package ru.job4j.doptask.alternativesolution;


/**
 * Class MaxField осуществляет поиск поля с максимальным числом соприкасающихся клеток хначения которых равно 1.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 08.09.2017
 */
public class MaxField {

    /** пространство поиска. */
    private final int[][] space;

    /** размер пространства по горизонтали. */
    private final int sizeX;

    /** размер пространства по вертикали. */
    private final int sizeY;

    /**
     * Конструктор класса MaxField.
     * @param space - пространство поиска.
     * */
    public MaxField(int[][] space) {
        this.space = space;
        sizeY = space.length;
        sizeX = space[0].length;
    }
    /**
     * Определение максимального числа соприкасающихся друг с другом клеток, значения которых соответствует 1.
     * @return Определение максимального числа соприкасающихся друг с другом клеток, значения которых соответствует 1
     */
    public int searchMaximumField() {
        int max = 0;
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                int buf = searchContiguousCells(j, i);
                if (buf > max) {
                    max = buf;
                }
            }
        }
        recover();
        return max;
    }

    /**
     * Восстанавливает матрицу.
     * */
    void recover() {
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                if (space[i][j] == -1) {
                    space[i][j] = 1;
                }
            }
        }
    }

    /**
     * Возвращает  количество соприкасающихся единичных ячеек, начиная с указанной ячейкин.
     * (помечает пройденные ячейки значением -1)
     * @param x - координата ячейки по горизонтали
     * @param y - координата ячейки по вертикали
     * @return количество соприкасающихся единичных ячеек
     * */
    public int searchContiguousCells(int x, int y) {
        if ((x < 0 || x >= sizeX) || (y < 0 || y >= sizeY) || space[y][x] == 0 || space[y][x] == -1) {
            return 0;
        }

        /* помечаем ячейку как пройденную */
        space[y][x] = -1;

        int result = 1;

        /* проверка прилежащих ячеек */
        /* нижняя чейка */
        result +=  searchContiguousCells(x, y - 1);
        /* левая чейка */
        result += searchContiguousCells(x - 1, y);
        /* верхняя ячейка */
        result += searchContiguousCells(x, y + 1);
        /* правая ячейка */
        result += searchContiguousCells(x + 1, y);
        return result;
    }
}
