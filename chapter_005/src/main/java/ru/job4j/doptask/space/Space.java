package ru.job4j.doptask.space;

import ru.job4j.doptask.field.Point;

/**
 * Class Space описывает пространство .
 */
public class Space {
    /** Размер пространства по координате X.*/
    private final int sizeX;

    /** Размер пространства по координате Y.*/
    private final int sizeY;

    /** Пространство. */
    private final Cell[][] space;

    /**
     * Конструктор класса Space.
     * @param sizeX - расмер пространства по координате X;
     * @param sizeY - размер пространства по координате Y;
     * */
    private Space(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        space = new Cell[this.sizeY][this.sizeX];
    }

    /**
     * Конструктор класса Space.
     * @param sizeX - расмер пространства по координате X;
     * @param sizeY - размер пространства по координате Y;
     * @param space - пространство
     * */
    public Space(int sizeX, int sizeY, Cell[][] space) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.space = space;

    }

    /**
     * Возвращает ячейки пространства.
     * @return ячейки пространства
     * */
    public Cell[][] getSpace() {
        return space;
    }

    /**
     * Возвращает размер пространства по координате X.
     * @return размер пространства по координате X
     * */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * Возвращает размер пространства по координате Y.
     * @return размер пространства по координате Y
     * */
    public int getSizeY() {
        return sizeY;
    }

    /**
     * Фабричный метод пространства.
     * @param sizeX - расмер пространства по координате X;
     * @param sizeY - размер пространства по координате Y;
     * @return новое пространство с заполненными ячейками
     * */
    public static final Space newRandomSpace(int sizeX, int sizeY) {
        Space space = new Space(sizeX, sizeY);
        space.fillSpace();
        return space;
    }


    /**
     * Заполняет пространство ячеками с рандомным значением.
     * */
    public void fillSpace() {
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                space[i][j] = new Cell(new Point(j, i), generateValue());
            }
        }
    }

    /**
     * Генератор случайного значения чейки.
     * @return случайное значение (0 или 1)
     * */
    private int generateValue() {
        return (int) Math.round(Math.random());
    }

    /**
     * Возвращает подмножество текущего пространства.
     * @param startPoint - нижняя точка пространства
     * @param endPoint - верхняя точка пространства.
     * Точки должны лежать на правой диагонали.
     * @return подмножество текущего пространства
     * */
    public Space getSubSpace(Point startPoint, Point endPoint) {
        int subspaceSizeX = endPoint.getX() - startPoint.getX() + 1;
        int subspaceSizeY = endPoint.getY() - startPoint.getY() + 1;
        Space subspace = new Space(subspaceSizeX, subspaceSizeY);

        for (int i = 0; i < subspaceSizeY; i++) {
            for (int j = 0; j < subspaceSizeX; j++) {
                subspace.space[i][j] = space[i + startPoint.getY()][j + startPoint.getX()];
            }
        }
        return subspace;
    }


}
