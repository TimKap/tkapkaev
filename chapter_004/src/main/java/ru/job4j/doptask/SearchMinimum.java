package ru.job4j.doptask;

import java.util.ArrayList;

/**
 * Class SearchMinimum обеспечивает поиск минимальных элементов.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 25.07.2017
 */
public class SearchMinimum {
   /**
    * Поиск первых m минимальных элементов.
    * @param container - контейнер с входными данными
    * @param m - число первых минимальных элементов
    * @return контейнер минимальных элементов
    * */
   public MinContainer search(ArrayList<Integer> container, int m) {
       MinContainer minContainer = new MinContainer(container.get(0), 0);

       for (int i = 1; i < container.size(); i++) {
           int number = container.get(i);

           /* Добавить индекс минимального элемента */
           if (minContainer.getMin() == number) {
               if (minContainer.getMinIndexes().size() < m) {
                   minContainer.addIndex(i);
               }
           }

           /* Поиск минимального элемента */
           if (minContainer.getMin() > number) {
               minContainer.clearContainer();
               minContainer.addIndex(i);
               minContainer.setMin(number);
           }
       }
       return minContainer;
   }


}
