package ca.ulaval.ift6002.m2.domain.prescription;


public class PrescriptionTest {

    // private static final String PRACTIONNER_NAME = "practionner name";
    // private static final Date DATE = new Date();
    // private static final int FIVE_RENEWALS = 5;
    // private static final int ZERO_RENEWALS = 0;
    //
    // private static final Pharmacy PHARMACY = new
    // Pharmacy("pharmacy description");
    //
    // private static final Consumption CONSUMPTION_WITH_COUNT_ONE = new
    // Consumption(DATE, PHARMACY, 1);
    // private static final Consumption CONSUMPTION_WITH_COUNT_TWO = new
    // Consumption(DATE, PHARMACY, 2);
    //
    // private static final Drug DRUG = mock(Drug.class);
    //
    // private Prescription prescription;
    //
    // @Test(expected = NotEnoughRenewalsException.class)
    // public void givenZeroRenewalsWhenAddConsumptionShouldThrowException() {
    // setupPrescriptionWithNoRenewals();
    // prescription.addConsumption(CONSUMPTION_WITH_COUNT_ONE);
    // }
    //
    // @Test
    // public void
    // givenFiveRenewalsWhenHavingConsumptionWithCountOneShouldHaveFourRemainingRenewals()
    // {
    // setupPrescriptionWithFiveRenewals();
    // willReturn(Arrays.asList(CONSUMPTION_WITH_COUNT_ONE)).given(prescription).getConsumptions();
    //
    // assertEquals(4, prescription.remainingRenewals());
    // }
    //
    // @Test
    // public void
    // givenFiveRenewalsWhenHavingConsumptionWithCountTwoShouldHaveThreeRemainingRenewals()
    // {
    // setupPrescriptionWithFiveRenewals();
    // willReturn(Arrays.asList(CONSUMPTION_WITH_COUNT_TWO)).given(prescription).getConsumptions();
    //
    // assertEquals(3, prescription.remainingRenewals());
    // }
    //
    // @Test
    // public void givenPrescriptionWithNoRemainingRenewalsShouldBeObsolete() {
    // setupPrescriptionWithNoRenewals();
    // boolean isObsolete = prescription.isObsolete();
    // assertTrue(isObsolete);
    // }
    //
    // @Test
    // public void givenPrescriptionWithOneOldConsumptionShouldBeObsolete() {
    // setupPrescriptionWithFiveRenewals();
    // Consumption oldConsumption = consumptionOfSevenMonthsAgo();
    // willReturn(oldConsumption).given(prescription).lastConsumption();
    //
    // boolean isObsolete = prescription.isObsolete();
    //
    // assertTrue(isObsolete);
    // }
    //
    // @Test
    // public void
    // givenPrescriptionWithOneRecentConsumptionShouldNotBeObsolete() {
    // setupPrescriptionWithFiveRenewals();
    // Consumption recentConsumption = consumptionOfOneMonthAgo();
    // willReturn(recentConsumption).given(prescription).lastConsumption();
    //
    // boolean isObsolete = prescription.isObsolete();
    //
    // assertFalse(isObsolete);
    // }
    //
    // private void setupPrescriptionWithFiveRenewals() {
    // prescription = mock(Prescription.class, CALLS_REAL_METHODS);
    // willReturn(FIVE_RENEWALS).given(prescription).getRenewals();
    // }
    //
    // private void setupPrescriptionWithNoRenewals() {
    // prescription = mock(Prescription.class, CALLS_REAL_METHODS);
    // willReturn(ZERO_RENEWALS).given(prescription).getRenewals();
    // }
    //
    // private Consumption consumptionOfSevenMonthsAgo() {
    // Date sevenMonthsAgo = mock(Date.class);
    // willReturn(false).given(sevenMonthsAgo).after(any(Date.class));
    //
    // return new Consumption(sevenMonthsAgo, PHARMACY, 1);
    // }
    //
    // private Consumption consumptionOfOneMonthAgo() {
    // Date oneMonthsAgo = mock(Date.class);
    // willReturn(true).given(oneMonthsAgo).after(any(Date.class));
    //
    // return new Consumption(oneMonthsAgo, PHARMACY, 1);
    // }

}
