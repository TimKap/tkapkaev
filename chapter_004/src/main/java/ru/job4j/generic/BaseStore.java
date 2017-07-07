package ru.job4j.generic;
import java.util.Iterator;

/**
 * Class BaseStroe описывает унивирсальное хранилище данных.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 07.07.2017
 * @param <E> - тип объекта, помещаемого в хранилище
 */
public abstract class BaseStore<E extends Base> implements Store<E> {

    /** Список элементов хрангилища.*/
   private SimpleArray<E> list;
   /**
    * Конструктор класса BaseStore.
    * @param size - объем данных
    * */
   public BaseStore(int size) {
       list = new SimpleArray<E>(size);
   }

    /**
     * Добавление элемента.
     * @param o - добавляемый объект
     * */
    public void add(E o) {
        list.add(o);
    }


    /**
     * Заменяет один объект другим.
     * @param id - ID заменяемого объекта
     * @param src - заменяющий объект
     * @return заменяемый объект
     */
    public boolean update(String id, E src) {
        Iterator<E> itertor = list.iterator();
        while (itertor.hasNext()) {
            E element = itertor.next();
            if (element.getId().equals(id)) {
                return list.update(element, src);
            }
        }
        return false;
    }

    /**
     * Удаляет объект из хранилища.
     * @param o - удаляемый объект
     * @return удаленный объект
     * */
   public boolean remove(E o) {

       return list.delete(o);
   }
   /**
    * Возвращает весь список хранимых объектов.
    * @return  список объектов
    * */
   public SimpleArray<E> getList() {
       return list;
   }




}
