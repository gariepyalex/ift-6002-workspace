package ca.ulaval.ift6002.m2.domain.surgery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.surgery.SurgeryType;

public class SurgeryTypeTest {

    private static final String UNEXISTING_TYPE = "invalid";
    private static final String EYE_STRING = SurgeryType.EYE.toString();
    private static final String HEART_STRING = SurgeryType.HEART.toString();
    private static final String MARROW_STRING = SurgeryType.MARROW.toString();
    private static final String ONCOLOGY_STRING = SurgeryType.ONCOLOGY.toString();
    private static final String OTHER_STRING = SurgeryType.OTHER.toString();

    @Test
    public void givenEyeStringWhenDetermineFromShouldReturnEyeType() {
        SurgeryType typeFound = SurgeryType.determineFrom(EYE_STRING);

        assertEquals(SurgeryType.EYE, typeFound);
    }

    @Test
    public void givenHeartStringWhenDetermineFromShouldReturnHeartType() {
        SurgeryType typeFound = SurgeryType.determineFrom(HEART_STRING);

        assertEquals(SurgeryType.HEART, typeFound);
    }

    @Test
    public void givenMarrowStringWhenDetermineFromShouldReturnMarrowType() {
        SurgeryType typeFound = SurgeryType.determineFrom(MARROW_STRING);

        assertEquals(SurgeryType.MARROW, typeFound);
    }

    @Test
    public void givenOncologyStringWhenDetermineFromShouldReturnOncologyType() {
        SurgeryType typeFound = SurgeryType.determineFrom(ONCOLOGY_STRING);

        assertEquals(SurgeryType.ONCOLOGY, typeFound);
    }

    @Test
    public void givenOtherStringWhenDetermineFromShouldReturnOtherType() {
        SurgeryType typeFound = SurgeryType.determineFrom(OTHER_STRING);

        assertEquals(SurgeryType.OTHER, typeFound);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenUnexistingTypeStringWhenDetermineFromShouldThrowException() {
        SurgeryType.determineFrom(UNEXISTING_TYPE);
    }

    @Test
    public void givenEyeStringWhenIsValidShouldReturnTrue() {
        assertTrue(SurgeryType.isValid(EYE_STRING));
    }

    @Test
    public void givenHeartStringWhenIsValidShouldReturnTrue() {
        assertTrue(SurgeryType.isValid(HEART_STRING));
    }

    @Test
    public void givenMarrowStringWhenIsValidShouldReturnTrue() {
        assertTrue(SurgeryType.isValid(MARROW_STRING));
    }

    @Test
    public void givenOncologyStringWhenIsValidShouldReturnTrue() {
        assertTrue(SurgeryType.isValid(ONCOLOGY_STRING));
    }

    @Test
    public void givenOtherStringWhenIsValidShouldReturnTrue() {
        assertTrue(SurgeryType.isValid(OTHER_STRING));
    }

    @Test
    public void givenUnexistingTypeStringWhenIsValidShouldReturnFalse() {
        assertFalse(SurgeryType.isValid(UNEXISTING_TYPE));
    }

}
