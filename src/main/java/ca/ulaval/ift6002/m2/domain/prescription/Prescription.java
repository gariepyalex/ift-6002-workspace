package ca.ulaval.ift6002.m2.domain.prescription;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import ca.ulaval.ift6002.m2.domain.drug.Drug;

public abstract class Prescription {

    private static final int SIX_MONTHS_AGO = -6;

    public boolean hasNumber(int number) {
        return number == getNumber();
    }

    public void addConsumption(Consumption consumption) {
        if (!hasEnoughRenewalsFor(consumption)) {
            throw new NotEnoughRenewalsException("Consumption request: " + consumption.getCount()
                    + ", but remaining renewals: " + remainingRenewals());
        }

        addConsumptionInPrescription(consumption);
    }

    private boolean hasEnoughRenewalsFor(Consumption consumption) {
        return remainingRenewals() >= consumption.getCount();
    }

    public int remainingRenewals() {
        return getRenewals() - consumptionsCount();
    }

    private int consumptionsCount() {
        int count = 0;

        for (Consumption consumption : getConsumptions()) {
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
            Date dateOfLastConsumption = lastConsumption().getDate();
            Date sixMonthsAgo = sixMonthsAgo();

            return dateOfLastConsumption.after(sixMonthsAgo);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private Consumption lastConsumption() {
        if (!hasConsumptions()) {
            throw new NoSuchElementException("The prescription has no consumptions yet.");
        }
        return getLastConsumption();
    }

    private Date sixMonthsAgo() {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.add(Calendar.MONTH, SIX_MONTHS_AGO);
        return now.getTime();
    }

    public abstract int getNumber();

    public abstract Practitioner getPractioner();

    public abstract Date getDate();

    public abstract int getRenewals();

    public abstract Drug getDrug();

    protected abstract List<Consumption> getConsumptions();

    protected abstract void addConsumptionInPrescription(Consumption consumption);

    protected abstract Consumption getLastConsumption();

    protected abstract boolean hasConsumptions();
}
