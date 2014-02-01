package projectH.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import projectH.domain.prescription.Prescription;

@XmlRootElement(name = "prescription")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrescriptionDTO {

	private int practioner;
	private String date;
	private int renewals;
	private String din;
	private String name;

	protected PrescriptionDTO() {
	}

	public PrescriptionDTO(Prescription prescription) {
		this.practioner = prescription.getPractioner();
		this.date = prescription.getDate();
		this.renewals = prescription.getRenewals();
		this.din = prescription.getDin();
		this.name = prescription.getDrugName();
	}

	public int getPractioner() {
		return practioner;
	}

	public String getDate() {
		return date;
	}

	public int getRenewals() {
		return renewals;
	}

	public String getDin() {
		return din;
	}

	public String getName() {
		return name;
	}

}