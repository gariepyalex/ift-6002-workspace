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

    public final String din;

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

    public PrescriptionResponse(String name, String practionner, String date, Integer remainingRenewals,
            Integer autorizedRenewals, String din, ConsumptionResponse[] consumptionResponses) {
        this.name = name;
        this.practitioner = practionner;
        this.date = date;
        this.remainingRenewals = remainingRenewals;
        this.autorizedRenewals = autorizedRenewals;
        this.din = din;
        this.consumptionResponses = consumptionResponses;
    }

}
