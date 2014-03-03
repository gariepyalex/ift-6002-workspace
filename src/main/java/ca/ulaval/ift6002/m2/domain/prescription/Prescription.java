package ca.ulaval.ift6002.m2.domain.prescription;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ca.ulaval.ift6002.m2.domain.drug.Drug;

public class Prescription {

    private final Practitioner practitioner;
    private final Date date;
    private final int renewals;
    private final Drug drug;

    public Prescription(Practitioner practitioner, Date date, int renewals, Drug drug) {
        this.practitioner = practitioner;
        this.date = date;
        this.renewals = renewals;
        this.drug = drug;
    }

    public Practitioner getPractioner() {
        return practitioner;
    }

    public Date getDate() {
        return date;
    }

    public int getRenewals() {
        return renewals;
    }

    public Drug getDrug() {
        return drug;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
