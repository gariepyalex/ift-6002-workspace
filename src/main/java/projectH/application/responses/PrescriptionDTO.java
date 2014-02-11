package projectH.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import projectH.domain.drug.Din;
import projectH.domain.drug.DrugRepository;
import projectH.domain.prescription.Prescription;

@XmlRootElement(name = "prescription")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrescriptionDTO {

	public String intervenant;
	public String date;
	public Integer renouvellements;
	public String din;
	public String nom;

	public PrescriptionDTO() {
	}

	public Prescription toPrescription(DrugRepository drugRepository) {
		return new Prescription(intervenant, date, renouvellements, new Din(din), nom, drugRepository);
	}
}