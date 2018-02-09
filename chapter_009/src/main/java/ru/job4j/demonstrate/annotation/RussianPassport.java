package ru.job4j.demonstrate.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * Class RussianPassport описывает паспорт гражданина РФ.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 09/02/2018
 * */
@Component("russianPassport")
public class RussianPassport implements IPassport {
    /** информация в паспорте.*/
    private String information;
    /**
     * Конструктор класса RussianPassport.
     * @param information - информация в паспорте
     * */
    public RussianPassport(@Value("I'm Russian Federation citizen")String information) {
        this.information = information;
    }
    /**
     *  Возвращает информацию о паспорте.
     *  @return информация о паспорте.
     * */
    @Override
    public String getInformation() {
        return information;
    }

}
