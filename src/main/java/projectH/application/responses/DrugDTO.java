package projectH.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import projectH.domain.drug.Drug;

@XmlRootElement(name = "drug")
@XmlAccessorType(XmlAccessType.FIELD)
public class DrugDTO {

	private String din;
	private String brandName;
	private String description;

	protected DrugDTO() {
	}

	public DrugDTO(Drug drug) {
		this.din = drug.getDin();
		this.brandName = drug.getBrandName();
		this.description = drug.getDescriptor();
	}

	public String getDin() {
		return din;
	}

	public String getName() {
		return brandName;
	}

	public String getDescription() {
		return description;
	}
}
