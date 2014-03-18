package ca.ulaval.ift6002.m2.domain.prescription;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;

public class PrescriptionTest {

    private static final String PRACTIONNER_NAME = "practionner name";
    private static final Date DATE = new Date();
    private static final int FIVE_RENEWALS = 5;
    private static final int ZERO_RENEWALS = 0;
    private static final Drug DRUG = new Drug(new Din("din"), "brandname", "descriptor");

    private static final Pharmacy PHARMACY = new Pharmacy("pharmacy description");

    private static final Consumption CONSUMPTION_WITH_COUNT_ONE = new Consumption(DATE, PHARMACY, 1);
    private static final Consumption CONSUMPTION_WITH_COUNT_TWO = new Consumption(DATE, PHARMACY, 2);

    private Prescription prescription;

    @Test(expected = NotEnoughRenewalsException.class)
    public void givenZeroRenewalsWhenAddConsumptionShouldThrowException() {
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

    @Test
    public void givenPrescriptionWithNoRemainingRenewalsShouldBeObsolete() {
        buildPrescriptionWithNoRenewals();
        boolean isObsolete = prescription.isObsolete();
        assertTrue(isObsolete);
    }

    @Test
    public void givenPrescriptionWithOneOldConsumptionShouldBeObsolete() {
        buildPrescriptionWithFiveRenewals();
        Consumption oldConsumption = consumptionOfSevenMonthsAgo();
        prescription.addConsumption(oldConsumption);

        boolean isObsolete = prescription.isObsolete();

        assertTrue(isObsolete);
    }

    @Test
    public void givenPrescriptionWithOneRecentConsumptionShouldNotBeObsolete() {
        buildPrescriptionWithFiveRenewals();
        Consumption recentConsumption = consumptionOfOneMonthAgo();
        prescription.addConsumption(recentConsumption);

        boolean isObsolete = prescription.isObsolete();

        assertFalse(isObsolete);
    }

    private void buildPrescriptionWithFiveRenewals() {
        prescription = new Prescription(new Practitioner(PRACTIONNER_NAME), DATE, FIVE_RENEWALS, DRUG);
    }

    private void buildPrescriptionWithNoRenewals() {
        prescription = new Prescription(new Practitioner(PRACTIONNER_NAME), DATE, ZERO_RENEWALS, DRUG);
    }

    private Consumption consumptionOfSevenMonthsAgo() {
        Date sevenMonthsAgo = mock(Date.class);
        willReturn(false).given(sevenMonthsAgo).after(any(Date.class));

        return new Consumption(sevenMonthsAgo, PHARMACY, 1);
    }

    private Consumption consumptionOfOneMonthAgo() {
        Date oneMonthsAgo = mock(Date.class);
        willReturn(true).given(oneMonthsAgo).after(any(Date.class));

        return new Consumption(oneMonthsAgo, PHARMACY, 1);
    }

}
