package ca.ulaval.ift6002.m2.domain.prescription;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;

public class PrescriptionTest {

    private static final int FIVE_RENEWALS = 5;
    private static final int ZERO_RENEWALS = 0;
    private static final int FOUR_REMAINING_RENEWALS = 4;
    private static final int THREE_REMAINING_RENEWALS = 3;
    private static final int PRESCRIPTION_NUMBER = 1;
    private static final int OTHER_PRESCRIPTION_NUMBER = 2;

    private static final Consumption CONSUMPTION = mock(Consumption.class);

    private static final Collection<Consumption> CONSUMPTIONS = Arrays.asList(CONSUMPTION);

    private Prescription prescription;

    @Test
    public void givenPrescriptionWithNumberWhenHasNumberShouldReturnTrue() {
        setupPrescription();
        boolean hasNumber = prescription.hasNumber(PRESCRIPTION_NUMBER);
        assertTrue(hasNumber);
    }

    @Test
    public void givenPrescriptionWithNumberWhenHasAnotherNumberShouldReturnFalse() {
        setupPrescription();
        boolean hasNumber = prescription.hasNumber(OTHER_PRESCRIPTION_NUMBER);
        assertFalse(hasNumber);
    }

    @Test(expected = NotEnoughRenewalsException.class)
    public void givenZeroRenewalsWhenAddConsumptionShouldThrowException() {
        setUpConsumptionWithCountOne();
        setupPrescriptionWithNoRenewals();

        prescription.addConsumption(CONSUMPTION);
    }

    @Test
    public void givenFiveRenewalsShouldAddConsumption() {
        setUpConsumptionWithCountOne();
        setupPrescriptionWithFiveRenewals();
        willDoNothing().given(prescription).addConsumptionInPrescription(CONSUMPTION);

        prescription.addConsumption(CONSUMPTION);

        verify(prescription).addConsumptionInPrescription(CONSUMPTION);
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
    public void givenFiveRenewalsWhenHavingConsumptionWithCountFiveShouldHaveZeroRemainingRenewal() {
        setUpConsumptionWithCountFive();
        setupPrescriptionWithFiveRenewals();

        assertEquals(ZERO_RENEWALS, prescription.remainingRenewals());
    }

    @Test
    public void givenPrescriptionWithNoRemainingRenewalsAndOldConsumptionShouldBeObsolete() {
        setupPrescriptionWithNoRenewals();
        setUpConsumptionOfSevenMonthsAgo();

        boolean isObsolete = prescription.isObsolete();

        assertTrue(isObsolete);
    }

    @Test
    public void givenPrescriptionWithNoConsumptionsWhenVerifyingIsObsoleteShouldShouldBeObsolete() {
        setUpConsumptionOfSevenMonthsAgo();
        setupPrescriptionWithNoConsumptionsAndNoRenewals();

        boolean isObsolete = prescription.isObsolete();

        assertTrue(isObsolete);
    }

    @Test
    public void givenPrescriptionWithOneOldConsumptionAndRenewalsShouldNotBeObsolete() {
        setUpConsumptionOfSevenMonthsAgo();
        setupPrescriptionWithFiveRenewals();

        boolean isObsolete = prescription.isObsolete();

        assertFalse(isObsolete);
    }

    @Test
    public void givenPrescriptionWithOneRecentConsumptionAndNoRenewalsShouldNotBeObsolete() {
        setUpConsumptionOfOneMonthAgo();
        setupPrescriptionWithNoRenewals();

        boolean isObsolete = prescription.isObsolete();

        assertFalse(isObsolete);
    }

    @Test
    public void givenPrescriptionWithOneRecentConsumptionAndRenewalsShouldNotBeObsolete() {
        setUpConsumptionOfSevenMonthsAgo();
        setupPrescriptionWithFiveRenewals();

        boolean isObsolete = prescription.isObsolete();

        assertFalse(isObsolete);
    }

    private void setupPrescription() {
        prescription = mock(Prescription.class, CALLS_REAL_METHODS);

        willReturn(CONSUMPTIONS).given(prescription).getConsumptions();
        willReturn(true).given(prescription).hasConsumptions();
        willReturn(CONSUMPTION).given(prescription).getLastConsumption();
        willReturn(PRESCRIPTION_NUMBER).given(prescription).getNumber();
    }

    private void setupPrescriptionWithNoConsumptionsAndNoRenewals() {
        setupPrescriptionWithNoRenewals();
        willReturn(false).given(prescription).hasConsumptions();
    }

    private void setupPrescriptionWithFiveRenewals() {
        setupPrescription();
        willReturn(FIVE_RENEWALS).given(prescription).getRenewals();
    }

    private void setupPrescriptionWithNoRenewals() {
        setupPrescription();
        willReturn(ZERO_RENEWALS).given(prescription).getRenewals();
    }

    private void setUpConsumptionOfSevenMonthsAgo() {
        Date sevenMonthsAgo = mock(Date.class);
        willReturn(false).given(sevenMonthsAgo).after(any(Date.class));

        willReturn(sevenMonthsAgo).given(CONSUMPTION).getDate();
    }

    private void setUpConsumptionOfOneMonthAgo() {
        Date oneMonthAgo = mock(Date.class);
        willReturn(true).given(oneMonthAgo).after(any(Date.class));

        willReturn(oneMonthAgo).given(CONSUMPTION).getDate();
    }

    private void setUpConsumptionWithCountOne() {
        willReturn(1).given(CONSUMPTION).getCount();
    }

    private void setUpConsumptionWithCountTwo() {
        willReturn(2).given(CONSUMPTION).getCount();
    }

    private void setUpConsumptionWithCountFive() {
        willReturn(5).given(CONSUMPTION).getCount();
    }
}
