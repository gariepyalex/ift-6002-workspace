package ca.ulaval.ift6002.m2.domain.prescription;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ca.ulaval.ift6002.m2.domain.drug.Drug;

public class Prescription {

    private final Practitioner practitioner;
    private final Date date;
    private final int renewals;
    private final Drug drug;
    private final Collection<Consumption> consumptions;

    public Prescription(Practitioner practitioner, Date date, int renewals, Drug drug) {
        this.practitioner = practitioner;
        this.date = date;
        this.renewals = renewals;
        this.drug = drug;
        this.consumptions = new ArrayList<>();
    }

    public boolean hasNumber(int number) {
        return number == 1; // TODO: en attendant de mettre un membre privé
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

    public Collection<Consumption> getConsumptions() {
        return consumptions;
    }

    public void addConsumption(Consumption consumption) {
        if (!hasEnoughRenewalsFor(consumption)) {
            throw new NotEnoughRenewalsException("Consumption request: " + consumption.getCount()
                    + ", but remaining renewals: " + remainingRenewals());
        }

        consumptions.add(consumption);
    }

    private boolean hasEnoughRenewalsFor(Consumption consumption) {
        return remainingRenewals() > consumption.getCount();
    }

    public int remainingRenewals() {
        return renewals - consumptionsCount();
    }

    private int consumptionsCount() {
        int count = 0;

        for (Consumption consumption : consumptions) {
            count += consumption.getCount();
        }

        return count;
    }

    public int numberOfConsumptions() {
        // TODO Not used yet... I left it there temporary
        return consumptions.size();
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
