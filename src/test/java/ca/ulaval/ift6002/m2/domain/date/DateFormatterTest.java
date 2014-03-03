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

    private static final Date VALID_DATE = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 30, 30).getTime();

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
        boolean returnValue = DateFormatter.isValid(VALID_DATE_FORMAT);
        assertTrue(returnValue);
    }

    @Test
    public void givenFormatterWhenIsValidWithInvalidFormatShouldReturnFalse() {
        boolean returnValue = DateFormatter.isValid(INVALID_DATE_FORMAT);
        assertFalse(returnValue);
    }
}
