package projectH.domain.prescription;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import projectH.domain.drug.Drug;
import projectH.domain.drug.DrugRepository;

public class Prescription {
	private String practitioner;
	private Date date;
	private Integer renewals;
	private Drug drug;

	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	public Prescription(String practitioner, String dateString, Integer renewals, String din, String drugName,
			DrugRepository drugRepository) throws InvalidPrescriptionException {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			throw new InvalidPrescriptionException("Invalid date format");
		}

		initializePrescription(practitioner, date, renewals, din, drugName, drugRepository);
	}

	public Prescription(String practitioner, Date date, Integer renewals, String din, String drugName,
			DrugRepository drugRepository) throws InvalidPrescriptionException {
		initializePrescription(practitioner, date, renewals, din, drugName, drugRepository);
	}

	private void initializePrescription(String practitioner, Date date, Integer renewals, String din, String drugName,
			DrugRepository drugRepository) throws InvalidPrescriptionException {
		if (renewals == null || renewals < 0)
			throw new InvalidPrescriptionException("The number of renewals must be greater than or equals to zero");
		if (din.trim().isEmpty() && drugName.trim().isEmpty())
			throw new InvalidPrescriptionException("A din or drug name must be set");
		if (!din.isEmpty() && !drugName.isEmpty())
			throw new InvalidPrescriptionException("You cannot set din and drug name at the same time");
		if (!din.trim().isEmpty() && !drugRepository.isAValidDin(din))
			throw new InvalidPrescriptionException("The entered dim is invalid");

		this.practitioner = practitioner;
		this.date = date;
		this.renewals = renewals;
		this.drug = new Drug(din, drugName, "");
	}

	public String getPractioner() {
		return practitioner;
	}

	public String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		return formatter.format(date.getTime());
	}

	public Integer getRenewals() {
		return renewals;
	}

	public String getDin() {
		return drug.getDin();
	}

	public String getDrugName() {
		return drug.getBrandName();
	}

	public Drug getDrug() {
		return drug;
	}

}
