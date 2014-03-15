package ca.ulaval.ift6002.m2.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "drug")
@XmlAccessorType(XmlAccessType.FIELD)
public class DrugResponse {

    public final String din;

    @XmlElement(name = "nom")
    public final String brandName;

    public final String description;

    protected DrugResponse() {
        // Protected constructor for jaxb
        this.din = "";
        this.brandName = "";
        this.description = "";
    }

    public DrugResponse(String din, String brandName, String description) {
        this.din = din;
        this.brandName = brandName;
        this.description = description;
    }
}
