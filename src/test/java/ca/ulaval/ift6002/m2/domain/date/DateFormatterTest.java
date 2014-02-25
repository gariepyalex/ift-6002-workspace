package ca.ulaval.ift6002.m2.domain.date;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class DateFormatterTest {

    private static final int ANNEE = 2014;
    private static final int DECEMBER = 12;
    private static final String INVALID_DATE_FORMAT = "2002-12-1";
    private static final String VALID_DATE_FORMAT = "2014-01-01T12:00:00";

    private static final Date VALIDDATE = new GregorianCalendar(ANNEE, Calendar.JANUARY, 01, DECEMBER, 00, 00)
            .getTime();

    private DateFormatter dateFormatter;

    @Before
    public void setUp() {
        dateFormatter = new DateFormatter();
    }

    @Test(expected = DateException.class)
    public void givenFormatterWhenParsingInvalidDateShouldThrowException() {
        dateFormatter.parse(INVALID_DATE_FORMAT);
    }

    @Test
    public void givenFormatterWhenParsingValidDateShouldReturnDate() {
        Date parsedDate = dateFormatter.parse(VALID_DATE_FORMAT);

        assertEquals(VALIDDATE, parsedDate);
    }

    @Test
    public void givenFormatterWhenConvertToStringShouldReturnDateWithSameFormat() {
        String dateConverted = dateFormatter.dateToString(VALIDDATE);

        assertEquals(VALID_DATE_FORMAT, dateConverted);
    }

    @Test(expected = DateException.class)
    public void givenFormatterWhenConvertToStringWithInvalidFormatShouldReturnException() {
        dateFormatter.parse(INVALID_DATE_FORMAT);
    }
}
