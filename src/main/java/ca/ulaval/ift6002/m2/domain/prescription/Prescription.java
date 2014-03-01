package ca.ulaval.ift6002.m2.domain.prescription;

import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

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
        return Objects.hash(practitioner, date, renewals, drug);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Prescription) {
            Prescription other = (Prescription) obj;
            return new EqualsBuilder().append(practitioner, other.practitioner).append(date, other.date)
                    .append(renewals, other.renewals).append(drug, other.drug).isEquals();
        } else {
            return false;
        }
    }
}
