package ca.ulaval.ift6002.m2.acceptance.builder;

import ca.ulaval.ift6002.m2.application.requests.PrescriptionRequest;

public class PrescriptionBuilder {
    private String din;
    private String name;
    private Integer renewals;

    public PrescriptionBuilder() {
        this.din = "";
        this.name = "";
        this.renewals = 1;
    }

    public PrescriptionBuilder din(String din) {
        this.din = din;
        return this;
    }

    public PrescriptionBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PrescriptionBuilder renewals(Integer renewals) {
        this.renewals = renewals;
        return this;
    }

    public PrescriptionRequest buildRequest() {
        return new PrescriptionRequest("Practitionner", "2014-01-12T00:08:06", renewals, din, name);
    }
}
