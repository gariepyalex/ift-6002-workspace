package projectH.domain.date;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class DateFormatterTest {

	private final static String INVALID_DATE_FORMAT = "2002-12-1";
	private final static String VALID_DATE_FORMAT = "2014-01-01T12:00:00";

	private static Date VALID_DATE = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 00, 00).getTime();

	private DateFormatter dateFormatter;

	@Before
	public void setup() {
		dateFormatter = new DateFormatter();
	}

	@Test(expected = DateException.class)
	public void givenFormatterWhenParsingInvalidDateShouldThrowException() {
		dateFormatter.parse(INVALID_DATE_FORMAT);
	}

	@Test
	public void givenFormatterWhenParsingValidDateShouldReturnDate() {
		Date parsedDate = dateFormatter.parse(VALID_DATE_FORMAT);

		assertEquals(VALID_DATE, parsedDate);
	}

	@Test
	public void givenFormatterWhenConvertToStringShouldReturnDateWithSameFormat() {
		String dateConverted = dateFormatter.dateToString(VALID_DATE);

		assertEquals(VALID_DATE_FORMAT, dateConverted);
	}

	@Test(expected = DateException.class)
	public void givenFormatterWhenConvertToStringWithInvalidFormatShouldReturnException() {
		dateFormatter.parse(INVALID_DATE_FORMAT);
	}
}
