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

    // TODO Not use field ...
    public final String din;
    // TODO Not use field ...
    @XmlElement(name = "consommations")
    public final ConsumptionResponse[] consumptionResponses;

    protected PrescriptionResponse() {
        this.practitioner = "";
        this.date = "";
        this.remainingRenewals = 0;
        this.autorizedRenewals = 0;
        this.din = "";
        this.name = "";
        this.consumptionResponses = null;
    }

    public PrescriptionResponse(String name, String practitioner, String date, Integer remainingRenewals,
            Integer autorizedRenewals, String din, ConsumptionResponse[] consumptionResponses) {
        this.name = name;
        this.practitioner = practitioner;
        this.date = date;
        this.remainingRenewals = remainingRenewals;
        this.autorizedRenewals = autorizedRenewals;
        this.din = din;
        this.consumptionResponses = consumptionResponses;
    }

    public PrescriptionResponse(String name, String practitioner, String date, Integer remainingRenewals,
            Integer autorizedRenewals, String din) {
        this(name, practitioner, date, remainingRenewals, autorizedRenewals, din, null);
    }

    public PrescriptionResponse(String name, String practitioner, String date, Integer remainingRenewals,
            Integer autorizedRenewals) {
        this(name, practitioner, date, remainingRenewals, autorizedRenewals, null, null);
    }

}
