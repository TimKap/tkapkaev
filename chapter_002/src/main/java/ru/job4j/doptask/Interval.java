package ru.job4j.doptask;
import java.time.LocalTime;


/**
 * Class Interval описывает интервал.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 12.05.2017
 */
public class Interval {
    /** Нижняя граница интервала.*/
    private LocalTime leftBoundary;
    /** Верхняя граница интервала.*/
    private LocalTime rightBoundary;
    /** Количество пересечений с другими интервалами.*/
    private int interference;

    /**
     * Конструктор для класса Interval.
     * @param leftBoundary - нижняя граница интервала
     * @param rightBoundary - верхнеяя граница интервал
     * */
    public Interval(LocalTime leftBoundary, LocalTime rightBoundary) {
        this.leftBoundary = leftBoundary;
        this.rightBoundary = rightBoundary;
    }
    /**
     * Конструктор для класса Interval без параметров.
     * */
    private Interval() {

    }

    /**
     * Размещает на общей оси точки, соответтсвующие верхним и нижним границам интервалов.
     * @param intervals - интервалы
     * @return массив с точками
     */
    private static Point[] createPoints(Interval[] intervals) {
        Point[] points = new Point[intervals.length * 2];
        int i = 0;
        for (Interval interval:intervals) {
            points[i++] = new Point(interval.leftBoundary, PointType.BEGIN);
            points[i++] = new Point(interval.rightBoundary, PointType.END);
        }
        return points;
    }

    /**
     * Метод возвращает участки временной оси, на которых образуются пересечения интервалов.
     * Каждый интервал представляет собой время посещения банка гостем и задан в виде нижней и верхней границы.
     * На вход аглгоритма поступает массив с верхними и нижними границами интервалов, упорядоченных по времени.
     * Участок временной оси, на которой образуется пересечение интервалов, ограничен одной из нижней и верхней границы интервала.
     * Полжим А - нижняя граница интервала, а B - верхняя граница интервала.
     * Зажданный массив можно представить, например, в виде следующей последовательности точек A1A2B2A3A4B4B1B3, где номер указывает интервал.
     * Тогда участок времени на котром образуется пересечение можно задать следующим образом.
     * Начало такого участка определяется переходом B -> A. При этом если обнаруживается последовательность из нескольких А, то нижняя граница участка будет смещена.
     * Конец такого участка определяется при обнаружении перехода A -> B.
     * Такой способ определения границ обеспечивает максимальное число пересечений интервалов, на заданном отрезке времени.
     * Для кажого обнаруженного участка записывается число пересечений. Число пересечений увеличивается, если встречается нижняя граница интервала и уменьшается, если обнаруживается верхняя граница интервала.
     * Пример:
     * Задано множество интервалов:
     *  A1-----------------------B1
     *     A2----B2
     *               A3---------------B3
     *                  A4--B4
     *  ---------------------------------------------------->t, время
     *  Ось с расположенныи на ней границами будет предсталять собой следующий массив:
     *  A1A2B2A3A4B4B1B3
     *  Участки на которых образуется максимальное число пересечений представляют собой интервалы:
     *  A2B2(1), A4B4(2)
     * @param points - заданное множество границ интервалов
     * @return  интервалы времени с максимальным числом пересечений
     * */
    private static Interval[] searchInterfrences(Point[] points) {
        /*Флаг непрерывности последоватиельности нижних границ*/
        boolean fl = false;
        /*Счетчик пересечений границ*/
        int interferences = 0;
        /* Интервал с наибольшим числом пересечений */
        Interval interval = null;
        /* Массив с найденными интервалами */
        Interval[] intervals = new Interval[points.length / 2];
        /* Счетчик интервалов */
        int i = 0;

        for (Point point : points) {
            if (point.getType() == PointType.BEGIN) {
                /*Обнаружена нижняя граница интервала*/
                interferences++;
                if (!fl) {
                    /* Обнаружен переход B -> A. Создается новый участок*/
                    interval = new Interval();
                    fl = true;
                }
                /* Обнаружена последовательность типа ААА, двигаем границу для заданного интервала*/
                interval.leftBoundary = point.getValue();
            } else {
                /*Обнаружена верхняя граница. Завершить создание интервала*/
                interferences--;
                if (fl) {
                    interval.rightBoundary = point.getValue();
                    interval.interference = interferences + 1;
                    intervals[i++] = interval;
                    fl = false;
                }
            }
        }
        Interval[] buf = new Interval[i];
        for (i = 0; i < buf.length; i++) {
            buf[i] = intervals[i];
        }
        return buf;
    }
    /**
     * Возвращает нижнюю границу интервала.
     * @return нижняя граница интервала
     * */
    public LocalTime getLefBoundary() {
        return leftBoundary;
    }

    /**
     * Возвращает нверхнюю границу интервала.
     * @return верхняя граница интервала
     * */
    public LocalTime getRightBoundary() {
        return rightBoundary;
    }
    /**
     * Возвращает интервалы на которых множество интервалов образует пиковое число пересечений.
     * @param intervals - заданное множество интервалов
     * @return интервалы на которых наблюдается пиковое число пересечений заданного множества интерваловю
     * */

    public static Interval[] maxInterferences(Interval[] intervals) {
        Point[] edges;
        edges = createPoints(intervals);
        Point.sortPointAscending(edges);
        Interval[] intervalsWithInterference;
        /* Поиск участков временной оси с наибольшим числом пересечений интервалов*/
        intervalsWithInterference = searchInterfrences(edges);
        int max = -1;
        int counter = 0;
        /* Определение участков временной оси с наибольшим числом пересечений */
        for (Interval interval : intervalsWithInterference) {
            if (max < interval.interference) {
                max = interval.interference;
                counter = 1;
            } else {
                if (max == interval.interference) {
                    counter++;
                }
            }
        }

        Interval[] result = new Interval[counter];
        int i = 0;
        for (Interval interval : intervalsWithInterference) {
            if (max == interval.interference) {
                result[i++] = interval;
            }
        }
        return result;
    }
    /**
     *
     */
    public void printInteval() {
        System.out.println("[ " + leftBoundary.toString() + " ; " + rightBoundary.toString() + "]");
    }
}

/**
 * Перечисление содержит типы точек.
 * */
enum PointType {
    /** Точка начала интервала. Точка конца интервала.*/
    BEGIN, END;
}
/**
 * Class Point описывает точку.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 12.05.2017
 */
class Point {
    /** Время, которому соответствует точка.*/
    private LocalTime  value;
    /** Тип точки.*/
    private PointType type;

    /** Конструктор для класса Point.
     * @param value - значение, которому соответствует точка
     * @param type - тип точки
     */
    Point(LocalTime value, PointType type) {
        this.value = value;
        this.type = type;
    }

    /**
     * Метод возвращает время, которому сооответствует точка.
     * @return время, соответствующее точке
     * */
    LocalTime getValue() {
        return value;
    }

    /**
     * Метод возвращает тип точки.
     * @return тип точки
     * */
    PointType getType() {
        return type;
    }

    /**
     * Сортировка точек по возрастанию времени.
     * @param points - массив с точками
     * */
    static void sortPointAscending(Point[] points) {
        boolean fl = true;
        Point buf;
        int k = 0;
        while (fl) {
            fl = false;
            for (int i = 0; i < points.length - 1 - k; i++) {
                if (points[i].getValue().compareTo(points[i + 1].getValue()) > 0) {
                    buf = points[i];
                    points[i] = points[i + 1];
                    points[i + 1] = buf;
                    fl = true;
                }
            }
            k++;
        }
    }
}

