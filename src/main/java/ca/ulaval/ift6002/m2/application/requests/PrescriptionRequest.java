package ca.ulaval.ift6002.m2.application.requests;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "prescription")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrescriptionRequest {

    @XmlElement(name = "intervenant")
    public final String practitioner;

    @XmlElement(name = "date")
    public final String date;

    @XmlElement(name = "renouvellements")
    public final Integer renewals;

    @XmlElement(name = "din")
    public final String din;

    @XmlElement(name = "nom")
    public final String name;

    protected PrescriptionRequest() {
        // Protected constructor for jaxb
        this.practitioner = "";
        this.date = "";
        this.renewals = 0;
        this.din = "";
        this.name = "";
    }

    public PrescriptionRequest(String practitioner, String date, Integer renewals, String din, String name) {
        this.practitioner = practitioner;
        this.date = date;
        this.renewals = renewals;
        this.din = din;
        this.name = name;
    }

}
