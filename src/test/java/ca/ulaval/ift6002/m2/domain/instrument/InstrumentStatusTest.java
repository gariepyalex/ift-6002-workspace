package ca.ulaval.ift6002.m2.domain.instrument;

import static org.junit.Assert.*;

import org.junit.Test;

public class InstrumentStatusTest {

    private static final String SOILED_STRING = "SOUILLÉ";
    private static final String USED_STRING = "UTILISE_PATIENT";
    private static final String UNUSED_STRING = "INUTILISE";
    private static final String INVALID_STRING = "invalid";

    @Test
    public void givenSoiledStringWhenDetermineFromShouldReturnSoiledStatus() {
        InstrumentStatus returnStatus = InstrumentStatus.determineFrom(SOILED_STRING);
        assertEquals(InstrumentStatus.SOILED, returnStatus);
    }

    @Test
    public void givenUsedStringWhenDetermineFromShouldReturnUsedPatientStatus() {
        InstrumentStatus returnStatus = InstrumentStatus.determineFrom(USED_STRING);
        assertEquals(InstrumentStatus.USED_PATIENT, returnStatus);
    }

    @Test
    public void givenUnusedStringWhenDetermineFromShouldReturnUnusedStatus() {
        InstrumentStatus returnStatus = InstrumentStatus.determineFrom(UNUSED_STRING);
        assertEquals(InstrumentStatus.UNUSED, returnStatus);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidStringWhenDetermineFromShouldThrowException() {
        InstrumentStatus.determineFrom(INVALID_STRING);
    }

    @Test
    public void givenSoiledStringWhenIsValidShouldReturnTrue() {
        boolean returnValue = InstrumentStatus.isValid(SOILED_STRING);
        assertTrue(returnValue);
    }

    @Test
    public void givenUsedStringWhenIsValidShouldReturnTrue() {
        boolean returnValue = InstrumentStatus.isValid(USED_STRING);
        assertTrue(returnValue);
    }

    @Test
    public void givenUnusedStringWhenIsValidShouldReturnTrue() {
        boolean returnValue = InstrumentStatus.isValid(UNUSED_STRING);
        assertTrue(returnValue);
    }

    @Test
    public void givenInvalidStringWhenIsValidShouldReturnFalse() {
        boolean returnValue = InstrumentStatus.isValid(INVALID_STRING);
        assertFalse(returnValue);
    }
}
