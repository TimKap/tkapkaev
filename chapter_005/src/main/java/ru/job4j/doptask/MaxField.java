package ru.job4j.doptask;

import ru.job4j.doptask.field.Field;
import ru.job4j.doptask.field.FieldUnionContainer;
import ru.job4j.doptask.field.Point;
import ru.job4j.doptask.space.Cell;
import ru.job4j.doptask.space.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;



/**
 * Class MaxField поиска поля, которое закрашено максимальным числом единиц, прилегающих друг к другу.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 03.09.2017
 *
 *
 * Описание решения: Задается пространство с расположенным на нем значением нулей и единиц.
 *                   Пространство разбивается на четыре подмножества, каждое из которых обрабатыватся в своем потоке.
 *                   Резудьтатом обработки является специальный контейнер полей.
 *                   Данный контейнер содержит множество полей, которые образуют единицы в переданном в обработку подмножества.
 *                   После обработки заданного пространства получается несколько таких контейнеров.
 *                   Далее полученные контейнеры объединяются в один общий контейнеер полей.
 *                   На заключительном этапе среди полей полученного контейнера
 *                   выполняется поиск поля с максимальным числом "ядер" (ядро - точка пространства, которая соответствует единице).
 */
public class MaxField {
    /**
     * Класс FieldUnionContainer описывает задачу построения контейнера полей, заполненных значениями 1.
     * @author Timur Kapkaev (timur.kap@yandex.ru)
     * @version $Id$
     * @since 03.09.2017
     * */


     static class CreateFieldUnionContainer implements Callable<FieldUnionContainer> {

         /** Пространство на котором строится контейнер полей.*/
         private final Space space;
        /**
         * Конструктор SearchTas.
         * @param space - пространство на котором выполняется построение контейнера полей.
         * */
        CreateFieldUnionContainer(Space space) {
            this.space = space;
        }

        @Override
        public FieldUnionContainer call() {
            FieldUnionContainer fieldContainer = new FieldUnionContainer();
            Cell[][] cells = space.getSpace();
            for (int i = 0; i < space.getSizeY(); i++) {
                for (int j = 0; j < space.getSizeX(); j++) {
                    if (cells[i][j].getValue() == 1) {
                        fieldContainer.unionJoin(new Field(new Point[]{cells[i][j].getCoordinate()}));
                    }
                }
            }
            return fieldContainer;
        }
    }

    /**
     * Определяет множество, содержащее максимальное число соприкасающихся клеток значения которых соответствуют единице.
     * @param space - заданное пространство значений
     * @return множество, содержащее максимальное число соприкасающихся клеток значения которых соответствует единице
     * @throws InterruptedException -
     * @throws ExecutionException -
     * */
    public int searchMaximumField(Space space) throws InterruptedException, ExecutionException {
        int sizeX = space.getSizeX();
        int sizeY = space.getSizeY();
        int halfSizeX = ((sizeX + 1) / 2);
        int halfSizeY = ((sizeY + 1) / 2);
        ExecutorService threadPool;
        List<Future<FieldUnionContainer>> tasks = new ArrayList<>();

         if ((space.getSizeX() < 2) || (space.getSizeY() < 2)) {
             threadPool = Executors.newSingleThreadExecutor();

            tasks.add(threadPool.submit(new CreateFieldUnionContainer(space)));

         } else {
             threadPool = Executors.newFixedThreadPool(4);
             Future<FieldUnionContainer> task = threadPool.submit(new CreateFieldUnionContainer(space.getSubSpace(new Point(0, 0), new Point(halfSizeX - 1, halfSizeY - 1))));
             tasks.add(task);
             task = threadPool.submit(new CreateFieldUnionContainer(space.getSubSpace(new Point(0, halfSizeY), new Point(halfSizeX - 1, sizeY - 1))));
             tasks.add(task);
             task = threadPool.submit(new CreateFieldUnionContainer(space.getSubSpace(new Point(halfSizeX, 0), new Point(sizeX - 1, halfSizeY - 1))));
             tasks.add(task);
             task = threadPool.submit(new CreateFieldUnionContainer(space.getSubSpace(new Point(halfSizeX, halfSizeY), new Point(sizeX - 1, sizeY - 1))));
             tasks.add(task);
         }

         List<FieldUnionContainer> containers = new ArrayList<>();

         for (Future<FieldUnionContainer> task : tasks) {
             containers.add(task.get());
         }
         FieldUnionContainer mergedContainer = FieldUnionContainer.combine(containers);

         int max = 0;
         for (Field field : mergedContainer.getFieldContainer()) {
             if (max < field.getFieldsCore().size()) {
                 max = field.getFieldsCore().size();
             }
         }
         return max;
    }
}
