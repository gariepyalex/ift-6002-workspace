package ca.ulaval.ift6002.m2.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "prescription")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrescriptionDTO {

    @XmlElement(name = "intervenant")
    public final String practitioner;

    public final String date;

    @XmlElement(name = "renouvellements")
    public final Integer renewals;

    public final String din;

    @XmlElement(name = "nom")
    public final String name;

    public PrescriptionDTO(String practitioner, String date, Integer renewals, String din, String name) {
        this.practitioner = practitioner;
        this.date = date;
        this.renewals = renewals;
        this.din = din;
        this.name = name;
    }

    protected PrescriptionDTO() {
        // Protected constructor for jaxb
        this.practitioner = "";
        this.date = "";
        this.renewals = 0;
        this.din = "";
        this.name = "";
    }

}
