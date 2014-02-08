package projectH.domain.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	private SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

	public Date createDate(String dateString) {
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			throw new DateException("Invalid date format");
		}
		return date;
	}

	public String getDateString(Date date) {
		return formatter.format(date.getTime());
	}

}
