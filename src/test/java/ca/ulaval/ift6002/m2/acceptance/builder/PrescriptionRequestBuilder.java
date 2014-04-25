package ca.ulaval.ift6002.m2.acceptance.builder;

import ca.ulaval.ift6002.m2.application.requests.PrescriptionRequest;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public class PrescriptionRequestBuilder {

    private String practitionner;
    private String date;
    private String din;
    private String name;
    private Integer renewals;

    public PrescriptionRequestBuilder() {
        this.din = "";
        this.name = "";
        fillMeaninglessData();
    }

    public PrescriptionRequestBuilder fillMeaninglessData() {
        return practitionner("Practitionner").renewals(1).date("2014-01-12T00:08:06");
    }

    public PrescriptionRequestBuilder practitionner(String practitionner) {
        this.practitionner = practitionner;
        return this;
    }

    public PrescriptionRequestBuilder date(String date) {
        this.date = date;
        return this;
    }

    public PrescriptionRequestBuilder din(String din) {
        this.din = din;
        return this;
    }

    public PrescriptionRequestBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PrescriptionRequestBuilder renewals(Integer renewals) {
        this.renewals = renewals;
        return this;
    }

    public PrescriptionRequestBuilder prescription(Prescription prescription) {
        this.date = prescription.getDate().toString();
        this.din = prescription.getDrug().getDin().toString();
        this.name = prescription.getDrug().getBrandName();
        this.renewals = prescription.getRenewals();
        return this;
    }

    public PrescriptionRequest build() {
        return new PrescriptionRequest(practitionner, date, renewals, din, name);
    }
}
