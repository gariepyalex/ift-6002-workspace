package ca.ulaval.ift6002.m2.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "prescription")
@XmlAccessorType(XmlAccessType.FIELD)
public class DetailedPrescriptionResponse extends PrescriptionResponse {

    @XmlElement(name = "consommations")
    public final ConsumptionResponse[] consumptions;

    public final String din;

    protected DetailedPrescriptionResponse() {
        super();
        this.consumptions = null;
        this.din = "";
    }

    public DetailedPrescriptionResponse(String practitioner, String name, String date, Integer remainingRenewals,
            Integer autorizedRenewals, ConsumptionResponse[] consumptions, String din) {
        super(practitioner, name, date, remainingRenewals, autorizedRenewals);
        this.consumptions = consumptions;
        this.din = din;
    }
}
