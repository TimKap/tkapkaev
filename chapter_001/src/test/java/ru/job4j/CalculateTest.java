package ru.job4j;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
*Тест для класса Calculate.
* @author Timur KApkaev (timur.kap@yandex.ru)
* @since 09.03.2017
* @version $Id$
*/

public class CalculateTest {
	/**
	*Тест вывода на консоль "Hello World".
	*/
	@Test
	public void whenAddOneToOneThenTwo() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Calculate.main(null);
		assertThat(out.toString(), is("Hello World\r\n"));
		}
}