package projectH.domain.date;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class DateFormatterTest {

	private final String INVALID_DATE = "2002-12-1";
	private final String VALID_DATE = "2002-12-12T12:01:00";
	private DateFormatter dateFormatter;

	@Before
	public void givenAFactoryTest() {
		dateFormatter = new DateFormatter();
	}

	@Test(expected = DateException.class)
	public void throwsDateExceptionIfDateIsInvalid() {
		dateFormatter.parse(INVALID_DATE);
	}

	@Test
	public void methodGetStringShouldReturnDateInStringWithRightFormat() {
		Date date = dateFormatter.parse(VALID_DATE);
		String dateStringReturned = dateFormatter.getString(date);
		assertEquals(VALID_DATE, dateStringReturned);
	}
}
