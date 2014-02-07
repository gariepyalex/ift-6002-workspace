package projectH.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import projectH.domain.instrument.Instrument;
import projectH.domain.instrument.InstrumentStatus;

@XmlRootElement(name = "instrument")
@XmlAccessorType(XmlAccessType.FIELD)
public class InstrumentDTO {

	public String status;
	public String serial = "";
	public String typecode = "";

	public InstrumentDTO() {
	}

	public Instrument toInstrument() {
		return new Instrument(typecode, InstrumentStatus.valueOf(status), serial);
	}

}
