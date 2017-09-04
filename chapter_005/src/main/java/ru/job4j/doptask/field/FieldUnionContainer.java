package ru.job4j.doptask.field;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * FieldUnionContainer описывает контейнер полей.
 * Для этого контейнера определена специальная операция
 * вставки поля в контейнер. После добавления поля, если есть возможность
 * в контейнере выполняется слияние соприкасающихся полей.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 03.09.2017
 */
public class FieldUnionContainer {

    /** Контейнер полей.*/
    private final Set<Field> fieldContainer = new HashSet();

    /**
     * Добавляет в контейнер новое поле.
     * При добавлении нового поля контейнер проверяет соприкосновение добавляемого поля с остальными полями контейнера.
     * Если такие поля находятся, то контейнер автоматически объединяет их в одно поле, вместе с добавляемым полем.
     * Предполагается, что добавляются группированные поля, поэтому несколько соприкасающихся группированных полей будут образовывать одно
     * общее группированное поле
     * @param field - добавляемое поле
     * */
    public void unionJoin(Field field) {
        /* список  полей, с которыми добавляемое поле пересекается */
        Set<Field> intersections = new HashSet<>();

        Field  fieldsUnion = new Field();
        fieldsUnion.joinField(field);
        /* поиск и объединение полей, которые пересекаются с field*/
        for (Field fieldFromContainer : fieldContainer) {
            if (field.isIntersection(fieldFromContainer)) {
                fieldsUnion.joinField(fieldFromContainer);
                intersections.add(fieldFromContainer);
            }
        }

        /* удалиь объединенные поля из контейнера*/
        fieldContainer.removeAll(intersections);

       /* добавить объединенное поле в контейнер*/
        fieldContainer.add(fieldsUnion);
    }
    /**
     * Формирование контейнера соприкасающихся полей из нескольких контейнеров.
     * @param containers - контейнеры полей
     * @return объединенный контейнер полей.
     * */
    public static FieldUnionContainer combine(List<FieldUnionContainer> containers) {
        FieldUnionContainer unionContainer = new FieldUnionContainer();
        for (FieldUnionContainer container : containers) {
            for (Field field : container.getFieldContainer()) {
                unionContainer.unionJoin(field);
            }
        }
        return unionContainer;
    }

    /**
     * Возвращает контейнер с полями.
     * @return контейнер с полями
     * */
    public Set<Field> getFieldContainer() {
        return fieldContainer;
    }

}
