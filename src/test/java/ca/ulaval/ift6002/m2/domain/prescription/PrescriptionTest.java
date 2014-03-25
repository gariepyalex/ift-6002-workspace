package ca.ulaval.ift6002.m2.domain.prescription;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;

public class PrescriptionTest {

    private static final int FIVE_RENEWALS = 5;
    private static final int ZERO_RENEWALS = 0;
    private static final int FOUR_REMAINING_RENEWALS = 4;
    private static final int THREE_REMAINING_RENEWALS = 3;

    private static final Consumption CONSUMPTION = mock(Consumption.class);

    private static final Collection<Consumption> CONSUMPTIONS = Arrays.asList(CONSUMPTION);

    private Prescription prescription;

    @Test(expected = NotEnoughRenewalsException.class)
    public void givenZeroRenewalsWhenAddConsumptionShouldThrowException() {
        setUpConsumptionWithCountOne();
        setupPrescriptionWithNoRenewals();

        prescription.addConsumption(CONSUMPTION);
    }

    @Test
    public void givenFiveRenewalsWhenHavingConsumptionWithCountOneShouldHaveFourRemainingRenewals() {
        setUpConsumptionWithCountOne();
        setupPrescriptionWithFiveRenewals();

        assertEquals(FOUR_REMAINING_RENEWALS, prescription.remainingRenewals());
    }

    @Test
    public void givenFiveRenewalsWhenHavingConsumptionWithCountTwoShouldHaveThreeRemainingRenewals() {
        setUpConsumptionWithCountTwo();
        setupPrescriptionWithFiveRenewals();

        assertEquals(THREE_REMAINING_RENEWALS, prescription.remainingRenewals());
    }

    @Test
    public void givenPrescriptionWithNoRemainingRenewalsShouldBeObsolete() {
        setupPrescriptionWithNoRenewals();
        boolean isObsolete = prescription.isObsolete();
        assertTrue(isObsolete);
    }

    @Test
    public void givenPrescriptionWithOneOldConsumptionShouldBeObsolete() {
        setUpConsumptionOfSevenMonthsAgo();
        setupPrescriptionWithFiveRenewals();

        boolean isObsolete = prescription.isObsolete();

        assertTrue(isObsolete);
    }

    @Test
    public void givenPrescriptionWithOneRecentConsumptionShouldNotBeObsolete() {
        setUpConsumptionOfOneMonthAgo();
        setupPrescriptionWithFiveRenewals();

        boolean isObsolete = prescription.isObsolete();

        assertFalse(isObsolete);
    }

    private void setupPrescription() {
        willReturn(CONSUMPTIONS).given(prescription).getConsumptions();
        willReturn(CONSUMPTION).given(prescription).getLastConsumption();
        willReturn(false).given(prescription).isConsumptionsEmpty();
    }

    private void setupPrescriptionWithFiveRenewals() {
        prescription = mock(Prescription.class, CALLS_REAL_METHODS);
        setupPrescription();
        willReturn(FIVE_RENEWALS).given(prescription).getRenewals();
    }

    private void setupPrescriptionWithNoRenewals() {
        prescription = mock(Prescription.class, CALLS_REAL_METHODS);
        setupPrescription();
        willReturn(ZERO_RENEWALS).given(prescription).getRenewals();
    }

    private void setUpConsumptionOfSevenMonthsAgo() {
        Date sevenMonthsAgo = mock(Date.class);
        willReturn(false).given(sevenMonthsAgo).after(any(Date.class));

        willReturn(sevenMonthsAgo).given(CONSUMPTION).getDate();
    }

    private void setUpConsumptionOfOneMonthAgo() {
        Date oneMonthsAgo = mock(Date.class);
        willReturn(true).given(oneMonthsAgo).after(any(Date.class));

        willReturn(oneMonthsAgo).given(CONSUMPTION).getDate();
    }

    private void setUpConsumptionWithCountOne() {
        willReturn(1).given(CONSUMPTION).getCount();
    }

    private void setUpConsumptionWithCountTwo() {
        willReturn(2).given(CONSUMPTION).getCount();
    }
}
