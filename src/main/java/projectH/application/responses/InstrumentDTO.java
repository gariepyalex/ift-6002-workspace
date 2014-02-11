package projectH.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "instrument")
@XmlAccessorType(XmlAccessType.FIELD)
public class InstrumentDTO {

	@XmlElement(name = "statut")
	public final String status;

	@XmlElement(name = "noserie")
	public final String serial;

	@XmlElement(name = "typecode")
	public final String typecode;

	protected InstrumentDTO() {
		this.status = "";
		this.serial = "";
		this.typecode = "";
	}

	public InstrumentDTO(String typecode, String status, String serial) {
		this.status = status;
		this.serial = serial;
		this.typecode = typecode;
	}

}
