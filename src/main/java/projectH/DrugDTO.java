package projectH;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "drug")
@XmlAccessorType(XmlAccessType.FIELD)
public class DrugDTO {

	private String name;
	private String description;

	protected DrugDTO() {
	}

	public DrugDTO(Drug drug) {
		this.name = drug.getName();
		this.description = drug.getDescriptor();
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
