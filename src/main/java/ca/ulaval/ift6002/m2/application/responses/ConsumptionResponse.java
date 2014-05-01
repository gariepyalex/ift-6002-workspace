package ca.ulaval.ift6002.m2.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "consumption")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConsumptionResponse {

    public final String date;

    @XmlElement(name = "pharmacie")
    public final String pharmacy;

    @XmlElement(name = "consommations")
    public final Integer consumptions;

    protected ConsumptionResponse() {
        // Protected constructor for jaxb
        this.date = "";
        this.pharmacy = "";
        this.consumptions = 0;
    }

    public ConsumptionResponse(String date, String pharmacy, Integer consumptions) {
        this.date = date;
        this.pharmacy = pharmacy;
        this.consumptions = consumptions;
    }

}
