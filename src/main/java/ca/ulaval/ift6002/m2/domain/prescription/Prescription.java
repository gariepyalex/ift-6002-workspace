package ca.ulaval.ift6002.m2.domain.prescription;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ca.ulaval.ift6002.m2.domain.drug.Drug;

public class Prescription {

    private static final int SIX_MONTHS_AGO = -6;

    private final Practitioner practitioner;
    private final Date date;
    private final int renewals;
    private final Drug drug;
    private final List<Consumption> consumptions;

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

    public List<Consumption> getConsumptions() {
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

    public boolean isObsolete() {
        // TODO à plugger avec la story Détection des interactions
        return !hasRemainingRenewals() || !isLastComsumptionConsumedInPastSixMonths();
    }

    private boolean hasRemainingRenewals() {
        return remainingRenewals() > 0;
    }

    private boolean isLastComsumptionConsumedInPastSixMonths() {
        try {
            Date dateOfLastConsumption = lastComsumption().getDate();
            Date sixMonthsAgo = sixMonthsAgo();

            return dateOfLastConsumption.after(sixMonthsAgo);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private Consumption lastComsumption() {
        if (consumptions.isEmpty()) {
            throw new NoSuchElementException("The prescription has no consumptions yet.");
        }

        return consumptions.get(consumptions.size() - 1);
    }

    private Date sixMonthsAgo() {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.add(Calendar.MONTH, SIX_MONTHS_AGO);
        return now.getTime();
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
