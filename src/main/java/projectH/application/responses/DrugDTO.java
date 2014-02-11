package projectH.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "drug")
@XmlAccessorType(XmlAccessType.FIELD)
public class DrugDTO {

	public final String din;
	@XmlElement(name = "nom")
	public final String brandName;
	public final String description;

	protected DrugDTO() {
		this.din = "";
		this.brandName = "";
		this.description = "";
	}

	public DrugDTO(String din, String brandName, String description) {
		this.din = din;
		this.brandName = brandName;
		this.description = description;
	}
}
