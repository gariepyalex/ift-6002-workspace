package projectH.domain.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	private SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

	public Date parse(String dateString) {
		try {
			return formatter.parse(dateString);
		} catch (ParseException e) {
			throw new DateException("Invalid date format");
		}
	}

	public String getString(Date date) {
		return formatter.format(date.getTime());
	}

}
