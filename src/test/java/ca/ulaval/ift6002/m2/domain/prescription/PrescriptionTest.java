package ca.ulaval.ift6002.m2.domain.prescription;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

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
        setupPrescriptionWithNumber();
        boolean hasNumber = prescription.hasNumber(PRESCRIPTION_NUMBER);
        assertTrue(hasNumber);
    }

    @Test
    public void givenPrescriptionWithNumberWhenHasAnotherNumberShouldReturnFalse() {
        setupPrescriptionWithNumber();
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
    public void givenPrescriptionWithNoRemainingRenewalsShouldBeObsolete() {
        setupPrescriptionWithNoRenewals();
        boolean isObsolete = prescription.isObsolete();
        assertTrue(isObsolete);
    }

    @Test
    public void givenPrescriptionWithOneOldConsumptionShouldBeObsolete() {
        setUpConsumptionOfSevenMonthsAgo();
        setupPrescriptionWithFiveRenewals();
        willReturn(false).given(prescription).isConsumptionsEmpty();

        boolean isObsolete = prescription.isObsolete();

        assertTrue(isObsolete);
    }

    @Test
    public void givenPrescriptionWithOneRecentConsumptionShouldNotBeObsolete() {
        setUpConsumptionOfOneMonthAgo();
        setupPrescriptionWithFiveRenewals();
        willReturn(false).given(prescription).isConsumptionsEmpty();

        boolean isObsolete = prescription.isObsolete();

        assertFalse(isObsolete);
    }

    @Test
    public void givenPrescriptionWithNoConsumptionShouldBeObsolete() {
        setupPrescriptionWithFiveRenewals();
        willReturn(true).given(prescription).isConsumptionsEmpty();

        boolean isObsolete = prescription.isObsolete();

        assertTrue(isObsolete);
    }

    private void setupPrescription() {
        willReturn(CONSUMPTIONS).given(prescription).getConsumptions();
        willReturn(CONSUMPTION).given(prescription).getLastConsumption();
    }

    private void setupPrescriptionWithFiveRenewals() {
        prescription = mock(Prescription.class, CALLS_REAL_METHODS);
        setupPrescription();
        willReturn(FIVE_RENEWALS).given(prescription).getRenewals();
    }

    private void setupPrescriptionWithNumber() {
        prescription = mock(Prescription.class, CALLS_REAL_METHODS);
        willReturn(PRESCRIPTION_NUMBER).given(prescription).getNumber();
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
