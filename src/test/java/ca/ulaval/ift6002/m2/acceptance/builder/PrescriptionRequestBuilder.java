package ca.ulaval.ift6002.m2.acceptance.builder;

import java.util.Date;

import ca.ulaval.ift6002.m2.application.requests.PrescriptionRequest;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
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
        return practitionner("Practitionner").withRecentDate().withRenewals();
    }

    public PrescriptionRequestBuilder practitionner(String practitionner) {
        this.practitionner = practitionner;
        return this;
    }

    public PrescriptionRequestBuilder date(String date) {
        this.date = date;
        return this;
    }

    public PrescriptionRequestBuilder withRecentDate() {
        Date today = new Date();
        String dateAsString = new DateFormatter().dateToString(today);

        return date(dateAsString);
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

    public PrescriptionRequestBuilder withRenewals() {
        return renewals(1);
    }

    public PrescriptionRequestBuilder fromPrescription(Prescription prescription) {
        this.date = prescription.getDate().toString();
        this.din = prescription.getDrug().getDin().toString();
        this.name = prescription.getDrug().getBrandName();
        this.renewals = prescription.getRenewals();
        return this;
    }

    public PrescriptionRequest build() {
        PrescriptionRequest prescriptionRequest = new PrescriptionRequest(practitionner, date, renewals, din, name);

        return prescriptionRequest;
    }
}
