package ca.ulaval.ift6002.m2.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "prescription")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrescriptionResponse {

    @XmlElement(name = "intervenant")
    public final String practitioner;

    @XmlElement(name = "nom")
    public final String name;

    public final String date;

    @XmlElement(name = "renouvellements_restants")
    public final Integer remainingRenewals;

    @XmlElement(name = "renouvellements_autorises")
    public final Integer autorizedRenewals;

    @XmlElement(name = "consommations")
    public final ConsumptionResponse[] consumptions;

    public final String din;

    protected PrescriptionResponse() {
        this.practitioner = "";
        this.date = "";
        this.remainingRenewals = 0;
        this.autorizedRenewals = 0;
        this.name = "";
        this.consumptions = null;
        this.din = "";
    }

    public PrescriptionResponse(String practitioner, String name, String date, Integer remainingRenewals,
            Integer autorizedRenewals) {
        this(practitioner, name, date, remainingRenewals, autorizedRenewals, null, null);
    }

    public PrescriptionResponse(String practitioner, String name, String date, Integer remainingRenewals,
            Integer autorizedRenewals, ConsumptionResponse[] consumptions, String din) {
        this.practitioner = practitioner;
        this.name = name;
        this.date = date;
        this.remainingRenewals = remainingRenewals;
        this.autorizedRenewals = autorizedRenewals;
        this.consumptions = consumptions;
        this.din = din;
    }
}
