package ru.jib4j.testtask;


/**
 * Class VacancyMonitorTest содержит демонстрацию переодического обновления вакансий.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 23.10.2017

 */
public class VacancyMonitorTest {
    /**
     * Демонстрация переодического обновления базы данных вакансий.
     * @throws Exception - ошибка теста
     * */
    public void demonstration() throws Exception {
        VacancyMonitor vc = VacancyMonitor.defaultVacancyMonitor();
        vc.periodicVacancyUpdate();
        boolean test = true;
        while (test) {
            test = true;
        }
    }
}
