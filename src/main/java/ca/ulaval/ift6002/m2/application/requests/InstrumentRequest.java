package ca.ulaval.ift6002.m2.application.requests;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "instrument")
@XmlAccessorType(XmlAccessType.FIELD)
public class InstrumentRequest {

    @XmlElement(name = "statut")
    public final String status;

    @XmlElement(name = "noserie")
    public final String serial;

    @XmlElement(name = "typecode")
    public final String typecode;

    protected InstrumentRequest() {
        // Protected constructor for jaxb
        this.status = "";
        this.serial = "";
        this.typecode = "";
    }

    public InstrumentRequest(String typecode, String status, String serial) {
        this.status = status;
        this.serial = serial;
        this.typecode = typecode;
    }
}
