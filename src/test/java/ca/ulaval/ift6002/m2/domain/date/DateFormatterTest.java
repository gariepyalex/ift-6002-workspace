package ca.ulaval.ift6002.m2.domain.date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class DateFormatterTest {

    private static final String INVALID_DATE_FORMAT = "2002-12-1";
    private static final String VALID_DATE_FORMAT = "2014-01-01T12:30:30";

    private static final int MONTH = Calendar.JANUARY;
    private static final int DAY = 01;
    private static final int YEAR = 2014;

    private static final int HOUR = 12;
    private static final int MINUTE = 30;
    private static final int SECOND = 30;

    private static final Date VALID_DATE = new GregorianCalendar(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND).getTime();

    private DateFormatter dateFormatter;

    @Before
    public void setUp() {
        dateFormatter = new DateFormatter();
    }

    @Test(expected = DateException.class)
    public void givenInvalidDateWhenParsingShouldThrowException() {
        dateFormatter.parse(INVALID_DATE_FORMAT);
    }

    @Test
    public void givenValidDateWhenParsingShouldReturnDate() {
        Date parsedDate = dateFormatter.parse(VALID_DATE_FORMAT);

        assertEquals(VALID_DATE, parsedDate);
    }

    @Test
    public void givenValidDateWhenConvertToStringShouldReturnDateWithSameFormat() {
        String dateConverted = dateFormatter.dateToString(VALID_DATE);

        assertEquals(VALID_DATE_FORMAT, dateConverted);
    }

    @Test(expected = DateException.class)
    public void givenInvalidFormatWhenConvertToStringShouldReturnException() {
        dateFormatter.parse(INVALID_DATE_FORMAT);
    }

    @Test
    public void givenFormatterWhenIsValidWithValidFormatShouldReturnTrue() {
        boolean isDateFormatValid = DateFormatter.isValid(VALID_DATE_FORMAT);
        assertTrue(isDateFormatValid);
    }

    @Test
    public void givenFormatterWhenIsValidWithInvalidFormatShouldReturnFalse() {
        boolean isDateFormatValid = DateFormatter.isValid(INVALID_DATE_FORMAT);
        assertFalse(isDateFormatValid);
    }
}
