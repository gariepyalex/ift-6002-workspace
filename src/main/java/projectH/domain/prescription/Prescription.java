package projectH.domain.prescription;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Prescription {
	private int practitioner;
	private Calendar date;
	private int renewals;
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	public Prescription(int practitioner, Calendar date, int renewals, String medecineName)
			throws InvalidPrescriptionException {
		if (renewals < 0)
			throw new InvalidPrescriptionException("The number of renewals must be greater or equals than zero");
		this.practitioner = practitioner;
		this.date = date;
		this.renewals = renewals;
	}

	public int getPractioner() {
		return practitioner;
	}

	public String getDate() {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
		return df.format(date.getTime());
	}

	public int getRenewals() {
		return renewals;
	}

}
