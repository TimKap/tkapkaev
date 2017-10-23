package ru.jib4j.testtask.webextractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import ru.jib4j.testtask.vacancy.Vacancy;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.exparity.hamcrest.date.DateMatchers.within;


/**
 * Class VacancySQLRUTest содержит тесты для VacancySQLRU.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 20.10.2017
 */
public class VacancySQLRUTest {

    /**
     *  Тест для extract.
     *  @throws IOException -
     * */
    @Test
    public void whenExtractThenGetVacanciesFromWEBPage() throws IOException {
        VacancySQLRU extractor = new VacancySQLRU();
        Document doc = Jsoup.parse(new File("../chapter_006/test_html_sqlru.html"), null);

        List<Vacancy> result = extractor.extract(doc);
        assertThat(result.size(), is(50));

        Calendar date = Calendar.getInstance();
        date.set(2017, 9, 12, 11, 16, 0);
        Vacancy vacancy = new Vacancy("Инженер-писатель по обеспечению информационной безопасности в телекоммуникационных система",  "ElenaN",  date.getTime());
        assertThat(result.get(0), is(vacancy));

        assertThat(date.getTime(), within(500, TimeUnit.MILLISECONDS, date.getTime()));
    }
}
