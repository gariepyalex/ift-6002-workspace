package ca.ulaval.ift6002.m2.domain.surgery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.surgery.SurgeryStatus;

public class SurgeryStatusTest {

    private static final String UNEXISTING_STATUS = "invalid";

    private static final String CANCELLED_STRING = SurgeryStatus.CANCELLED.toString();
    private static final String FINISH_STRING = SurgeryStatus.FINISH.toString();
    private static final String IN_PROGRESS_STRING = SurgeryStatus.IN_PROGRESS.toString();
    private static final String PLANNED_STRING = SurgeryStatus.PLANNED.toString();
    private static final String POSTPONED_STRING = SurgeryStatus.POSTPONED.toString();

    @Test
    public void givenEmptyStringWhenDetermineFromShouldReturnPlannedStatus() {
        SurgeryStatus statusFound = SurgeryStatus.determineFrom("");

        assertEquals(SurgeryStatus.PLANNED, statusFound);
    }

    @Test
    public void givenCancelledStringWhenDetermineFromShouldReturnCancelledStatus() {
        SurgeryStatus statusFound = SurgeryStatus.determineFrom(CANCELLED_STRING);

        assertEquals(SurgeryStatus.CANCELLED, statusFound);
    }

    @Test
    public void givenFinishStringWhenDetermineFromShouldReturnFinishStatus() {
        SurgeryStatus statusFound = SurgeryStatus.determineFrom(FINISH_STRING);

        assertEquals(SurgeryStatus.FINISH, statusFound);
    }

    @Test
    public void givenInProgressStringWhenDetermineFromShouldReturnInProgressStatus() {
        SurgeryStatus statusFound = SurgeryStatus.determineFrom(IN_PROGRESS_STRING);

        assertEquals(SurgeryStatus.IN_PROGRESS, statusFound);
    }

    @Test
    public void givenPlannedStringWhenDetermineFromShouldReturnPlannedStatus() {
        SurgeryStatus statusFound = SurgeryStatus.determineFrom(PLANNED_STRING);

        assertEquals(SurgeryStatus.PLANNED, statusFound);
    }

    @Test
    public void givenPostponedStringWhenDetermineFromShouldReturnPostponedStatus() {
        SurgeryStatus statusFound = SurgeryStatus.determineFrom(POSTPONED_STRING);

        assertEquals(SurgeryStatus.POSTPONED, statusFound);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenUnexistingStatusStringWhenDetermineFromShouldThrowIllegalArgumentException() {
        SurgeryStatus.determineFrom(UNEXISTING_STATUS);
    }

    @Test
    public void givenEmptyStringWhenIsValidShouldReturnTrue() {
        assertTrue(SurgeryStatus.isValid(""));
    }

    @Test
    public void givenCancelledStringWhenIsValidShouldReturnTrue() {
        assertTrue(SurgeryStatus.isValid(CANCELLED_STRING));
    }

    @Test
    public void givenFinishStringWhenIsValidShouldReturnTrue() {
        assertTrue(SurgeryStatus.isValid(FINISH_STRING));
    }

    @Test
    public void givenInProgressStringWhenIsValidShouldReturnTrue() {
        assertTrue(SurgeryStatus.isValid(IN_PROGRESS_STRING));
    }

    @Test
    public void givenPlannedStringWhenIsValidShouldReturnTrue() {
        assertTrue(SurgeryStatus.isValid(PLANNED_STRING));
    }

    @Test
    public void givenPostponedStringWhenIsValidShouldReturnTrue() {
        assertTrue(SurgeryStatus.isValid(POSTPONED_STRING));
    }

    @Test
    public void givenUnexistingStatusStringWhenIsValidShouldReturnFalse() {
        assertFalse(SurgeryStatus.isValid(UNEXISTING_STATUS));
    }

}
