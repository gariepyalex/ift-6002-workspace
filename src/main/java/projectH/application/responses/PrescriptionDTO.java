package projectH.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "prescription")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrescriptionDTO {

	public final String intervenant;
	public final String date;
	public final Integer renouvellements;
	public final String din;
	public final String nom;

	public PrescriptionDTO(String practitioner, String date, Integer renewals, String din, String name) {
		this.intervenant = practitioner;
		this.date = date;
		this.renouvellements = renewals;
		this.din = din;
		this.nom = name;
	}

}