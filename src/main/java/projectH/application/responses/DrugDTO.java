package projectH.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import projectH.domain.drug.Drug;

@XmlRootElement(name = "drug")
@XmlAccessorType(XmlAccessType.FIELD)
public class DrugDTO {

	public final String din;
	public final String brandName;
	public final String description;

	protected DrugDTO() {
		this.din = "";
		this.brandName = "";
		this.description = "";
	}

	public DrugDTO(Drug drug) {
		this.din = drug.getDin();
		this.brandName = drug.getBrandName();
		this.description = drug.getDescriptor();
	}
}
