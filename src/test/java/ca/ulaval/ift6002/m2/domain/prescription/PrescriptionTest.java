package ca.ulaval.ift6002.m2.domain.prescription;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;

public class PrescriptionTest {

    private static final String PRACTIONNER_NAME = "practionner name";
    private static final Date DATE = new Date();
    private static final int RENEWALS = 5;
    private static final int ZERO_RENEWALS = 0;
    private static final Drug DRUG = new Drug(new Din("din"), "brandname", "descriptor");

    private static final Pharmacy PHARMACY = new Pharmacy("pharmacy description");

    private static final Consumption CONSUMPTION_WITH_COUNT_ONE = new Consumption(DATE, PHARMACY, 1);
    private static final Consumption CONSUMPTION_WITH_COUNT_TWO = new Consumption(DATE, PHARMACY, 2);

    private Prescription prescription;

    public void buildPrescriptionWithFiveRenewals() {
        prescription = new Prescription(new Practitioner(PRACTIONNER_NAME), DATE, RENEWALS, DRUG);
    }

    public void buildPrescriptionWithNoRenewals() {
        prescription = new Prescription(new Practitioner(PRACTIONNER_NAME), DATE, ZERO_RENEWALS, DRUG);
    }

    @Test(expected = NotEnoughRenewalsException.class)
    public void givenZeroRenewalsWhenAddConsumptionShouldThrowNotEnoughRenewalsException() {
        buildPrescriptionWithNoRenewals();
        prescription.addConsumption(CONSUMPTION_WITH_COUNT_ONE);
    }

    @Test
    public void givenFiveRenewalsWhenAddConsumptionWithCountOneShouldHaveFourRemainingRenewals() {
        buildPrescriptionWithFiveRenewals();
        prescription.addConsumption(CONSUMPTION_WITH_COUNT_ONE);

        assertEquals(4, prescription.remainingRenewals());
    }

    @Test
    public void givenFiveRenewalsWhenAddConsumptionWithCountTwoShouldHaveThreeRemainingRenewals() {
        buildPrescriptionWithFiveRenewals();
        prescription.addConsumption(CONSUMPTION_WITH_COUNT_TWO);

        assertEquals(3, prescription.remainingRenewals());
    }

}
