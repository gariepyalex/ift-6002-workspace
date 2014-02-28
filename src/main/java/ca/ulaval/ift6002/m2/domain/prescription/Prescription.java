package ca.ulaval.ift6002.m2.domain.prescription;

import java.util.Date;
import java.util.Objects;

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

        if (!(obj instanceof Prescription)) {
            return false;
        }

        Prescription other = (Prescription) obj;

        return Objects.equals(practitioner, other.practitioner) && Objects.equals(date, other.date)
                && Objects.equals(renewals, other.renewals) && Objects.equals(drug, other.drug);
    }

}
