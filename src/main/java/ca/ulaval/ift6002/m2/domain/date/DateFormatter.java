package ca.ulaval.ift6002.m2.domain.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    public Date parse(String date) {
        try {
            return FORMATTER.parse(date);
        } catch (ParseException e) {
            throw new DateException("Dates should be in this format: " + DATE_FORMAT);
        }
    }

    public String dateToString(Date date) {
        return FORMATTER.format(date.getTime());
    }

    public static boolean isValid(String dateFormat) {
        try {
            FORMATTER.parse(dateFormat);

            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
