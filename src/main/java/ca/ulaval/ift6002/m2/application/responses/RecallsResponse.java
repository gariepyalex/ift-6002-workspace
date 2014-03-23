package ca.ulaval.ift6002.m2.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "recalls")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecallsResponse {

    @XmlElement(name = "nam")
    public final String healthInsuranceNumber;

    protected RecallsResponse() {
        // Protected constructor for jaxb
        this.healthInsuranceNumber = "";
    }

    public RecallsResponse(String healthInsuranceNumber) {
        this.healthInsuranceNumber = healthInsuranceNumber;
    }

}
