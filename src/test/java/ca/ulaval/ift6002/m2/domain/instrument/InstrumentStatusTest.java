package ca.ulaval.ift6002.m2.domain.instrument;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class InstrumentStatusTest {

    private static final String SOILED_STRING = InstrumentStatus.SOILED.toString();
    private static final String USED_STRING = InstrumentStatus.USED_PATIENT.toString();
    private static final String UNUSED_STRING = InstrumentStatus.UNUSED.toString();
    private static final String INVALID_STRING = "invalid";

    @Test
    public void givenSoiledStringWhenDetermineFromShouldReturnSoiledStatus() {
        InstrumentStatus statusFound = InstrumentStatus.determineFrom(SOILED_STRING);

        assertEquals(InstrumentStatus.SOILED, statusFound);
    }

    @Test
    public void givenUsedStringWhenDetermineFromShouldReturnUsedPatientStatus() {
        InstrumentStatus statusFound = InstrumentStatus.determineFrom(USED_STRING);

        assertEquals(InstrumentStatus.USED_PATIENT, statusFound);
    }

    @Test
    public void givenUnusedStringWhenDetermineFromShouldReturnUnusedStatus() {
        InstrumentStatus statusFound = InstrumentStatus.determineFrom(UNUSED_STRING);

        assertEquals(InstrumentStatus.UNUSED, statusFound);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidStringWhenDetermineFromShouldThrowException() {
        InstrumentStatus.determineFrom(INVALID_STRING);
    }

    @Test
    public void givenSoiledStringWhenIsValidShouldReturnTrue() {
        assertTrue(InstrumentStatus.isValid(SOILED_STRING));
    }

    @Test
    public void givenUsedStringWhenIsValidShouldReturnTrue() {
        assertTrue(InstrumentStatus.isValid(USED_STRING));
    }

    @Test
    public void givenUnusedStringWhenIsValidShouldReturnTrue() {
        assertTrue(InstrumentStatus.isValid(UNUSED_STRING));
    }

    @Test
    public void givenInvalidStringWhenIsValidShouldReturnFalse() {
        assertFalse(InstrumentStatus.isValid(INVALID_STRING));
    }
}
