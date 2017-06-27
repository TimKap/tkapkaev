package ru.job4j.doptask;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class Directory описывает справочник подрпазделений.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $Id$
 * @since 26.06.2017
 */
public class Directory {
    /**Список подразделений.*/
    private List<String> departments = new ArrayList<String>();

    /**
     * Конструктор класса Directory.
     * @param departments - список подразделений
     * */
    public Directory(List<String> departments) {
        this.departments = departments;
    }

    /**
     * Возвразает список подразделений.
     * @return подразделения
     * */
    public List<String> getDepartments() {
       return departments;
    }
    /**
     * Добавляет подразделение в справочник.
     * @param department - подразделение
     * */
    public void addDepartment(String department) {
        departments.add(department);
    }

    /**
     * Сортировка подразделений по возрастанию.
     */
    public void ascendSort() {
        departments.sort(Comparator.naturalOrder());
        recoverStructure();
    }
    /**
     * Сортировка подразделений по убыванию.
     */
    public void descendSort() {
        departments.sort((strA, strB) -> {
            /* Модифицированный алгоритм сортировки строк в обратном порядке.
               Модификация: если выявлено, что одна строка является подстрокой другой строки, то более короткая строка должна предшествовать более длинной.*/
            int len1 = strA.length();
            int len2 = strB.length();
            int lim = Math.min(len1, len2);
            char[] v1 = strA.toCharArray();
            char[] v2 = strB.toCharArray();

            int k = 0;
            while (k < lim) {
                char c1 = v1[k];
                char c2 = v2[k];
                if (c1 != c2) {
                    /* Разные подразделения. Сортируем по убыванию*/
                    return c2 - c1;
                }
                k++;
            }
            /*Одно подразделение является подуровнем другого подразделения.
            * Верхнее подразделение должно предшествовать более глубокуму подразделению.
            * */
            return len1 - len2;
        });
        recoverStructure();
    }

    /**
     * Добавление в список кода верхнеуровнего подразделения.
     * */
    private void recoverStructure() {
        Pattern pattern = Pattern.compile("(.*)\\\\");
        Matcher match;
        /*Вставка пустой строки в список*/
        departments.add(0, "");
        for (int i = 1; i < departments.size(); i++) {
            /* Возвращаем надуровень текущего подразделения*/
            match = pattern.matcher(departments.get(i));
            if (match.find()) {
                String subDepartment = match.group(1);
                String previousDepartment = departments.get(i - 1);
                /*Проверяем содержит ли предыдущее подразделение в списке надуровень.*/
                if (previousDepartment.indexOf(subDepartment) != 0) {
                    /*Предыдущий элемент не содержит надуровень. Добавляем надуровень.*/
                    departments.add(i, match.group(1));
                    /*Возвращаем каретку назад для проверки необходимости добавления надуровня над уже добавленным.*/
                    i--;
                }
            }
        }
        departments.remove(0);
    }

}
