package projectH.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import projectH.domain.instrument.Instrument;
import projectH.domain.instrument.Instrument.Status;

@XmlRootElement(name = "instrument")
@XmlAccessorType(XmlAccessType.FIELD)
public class InstrumentDTO {

	private Status status;
	private String serial;
	private String typecode;

	protected InstrumentDTO() {
	}

	public InstrumentDTO(Instrument instrument) {
		this.status = instrument.getStatus();
		this.serial = instrument.getSerial();
		this.typecode = instrument.getTypecode();
	}

	public Status getStatus() {
		return status;
	}

	public String getSerial() {
		return serial;
	}

	public String getTypecode() {
		return typecode;
	}

}
