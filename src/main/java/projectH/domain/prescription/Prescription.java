package projectH.domain.prescription;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Prescription {
	private int practitioner;
	private Calendar date;
	private int renewals;
	private String din;
	private String medecineName;

	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	public Prescription(int practitioner, Calendar date, int renewals, String din, String medecineName)
			throws InvalidPrescriptionException {
		if (renewals < 0)
			throw new InvalidPrescriptionException("The number of renewals must be greater or equals than zero");
		if (!din.isEmpty() && !medecineName.isEmpty())
			throw new InvalidPrescriptionException("You cannot set din and medecine name at the same time");
		if (din.isEmpty() && medecineName.isEmpty())
			throw new InvalidPrescriptionException("A din or medecine name must be set");

		this.practitioner = practitioner;
		this.date = date;
		this.renewals = renewals;
		this.din = din;
		this.medecineName = medecineName;
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

	public String getDin() {
		return din;
	}

	public String getMedecineName() {
		return medecineName;
	}

}
