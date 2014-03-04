package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OperationStatusTest {
    private static final String CANCELLED_STRING = OperationStatus.CANCELLED.toString();
    private static final String FINISH_STRING = OperationStatus.FINISH.toString();
    private static final String IN_PROGRESS_STRING = OperationStatus.IN_PROGRESS.toString();
    private static final String PLANNED_STRING = OperationStatus.PLANNED.toString();
    private static final String POSTPONED_STRING = OperationStatus.POSTPONED.toString();

    @Test
    public void givenEmptyStringWhenDetermineFromShouldReturnPlannedStatus() {
        OperationStatus statusFound = OperationStatus.determineFrom("");

        assertEquals(OperationStatus.PLANNED, statusFound);
    }

    @Test
    public void givenCancelledStringWhenDetermineFromShouldReturnCancelledStatus() {
        OperationStatus statusFound = OperationStatus.determineFrom(CANCELLED_STRING);

        assertEquals(OperationStatus.CANCELLED, statusFound);
    }

    @Test
    public void givenFinishStringWhenDetermineFromShouldReturnFinishStatus() {
        OperationStatus statusFound = OperationStatus.determineFrom(FINISH_STRING);

        assertEquals(OperationStatus.FINISH, statusFound);
    }

    @Test
    public void givenInProgressStringWhenDetermineFromShouldReturnInProgressStatus() {
        OperationStatus statusFound = OperationStatus.determineFrom(IN_PROGRESS_STRING);

        assertEquals(OperationStatus.IN_PROGRESS, statusFound);
    }

    @Test
    public void givenPlannedStringWhenDetermineFromShouldReturnPlannedStatus() {
        OperationStatus statusFound = OperationStatus.determineFrom(PLANNED_STRING);

        assertEquals(OperationStatus.PLANNED, statusFound);
    }

    @Test
    public void givenPostponedStringWhenDetermineFromShouldReturnPostponedStatus() {
        OperationStatus statusFound = OperationStatus.determineFrom(POSTPONED_STRING);

        assertEquals(OperationStatus.POSTPONED, statusFound);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenUnexistingStatusStringWhenDetermineFromShouldThrowIllegalArgumentException() {
        OperationStatus.determineFrom("UNEXISTING STATUS");
    }

    @Test
    public void givenEmptyStringWhenIsValidShouldReturnTrue() {
        boolean isValid = OperationStatus.isValid("");

        assertTrue(isValid);
    }

    @Test
    public void givenCancelledStringWhenIsValidShouldReturnTrue() {
        boolean isValid = OperationStatus.isValid(CANCELLED_STRING);

        assertTrue(isValid);
    }

    @Test
    public void givenFinishStringWhenIsValidShouldReturnTrue() {
        boolean isValid = OperationStatus.isValid(FINISH_STRING);

        assertTrue(isValid);
    }

    @Test
    public void givenInProgressStringWhenIsValidShouldReturnTrue() {
        boolean isValid = OperationStatus.isValid(IN_PROGRESS_STRING);

        assertTrue(isValid);
    }

    @Test
    public void givenPlannedStringWhenIsValidShouldReturnTrue() {
        boolean isValid = OperationStatus.isValid(PLANNED_STRING);

        assertTrue(isValid);
    }

    @Test
    public void givenPostponedStringWhenIsValidShouldReturnTrue() {
        boolean isValid = OperationStatus.isValid(POSTPONED_STRING);

        assertTrue(isValid);
    }

    @Test
    public void givenUnexistingStatusStringWhenIsValidShouldReturnFalse() {
        boolean isValid = OperationStatus.isValid("UNEXISTING STATUS");

        assertFalse(isValid);
    }

}
