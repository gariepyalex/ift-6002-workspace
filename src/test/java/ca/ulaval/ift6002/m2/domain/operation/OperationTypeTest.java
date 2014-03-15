package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OperationTypeTest {

    private static final String UNEXISTING_TYPE = "invalid";
    private static final String EYE_STRING = OperationType.EYE.toString();
    private static final String HEART_STRING = OperationType.HEART.toString();
    private static final String MARROW_STRING = OperationType.MARROW.toString();
    private static final String ONCOLOGY_STRING = OperationType.ONCOLOGY.toString();
    private static final String OTHER_STRING = OperationType.OTHER.toString();

    @Test
    public void givenEyeStringWhenDetermineFromShouldReturnEyeType() {
        OperationType typeFound = OperationType.determineFrom(EYE_STRING);

        assertEquals(OperationType.EYE, typeFound);
    }

    @Test
    public void givenHeartStringWhenDetermineFromShouldReturnHeartType() {
        OperationType typeFound = OperationType.determineFrom(HEART_STRING);

        assertEquals(OperationType.HEART, typeFound);
    }

    @Test
    public void givenMarrowStringWhenDetermineFromShouldReturnMarrowType() {
        OperationType typeFound = OperationType.determineFrom(MARROW_STRING);

        assertEquals(OperationType.MARROW, typeFound);
    }

    @Test
    public void givenOncologyStringWhenDetermineFromShouldReturnOncologyType() {
        OperationType typeFound = OperationType.determineFrom(ONCOLOGY_STRING);

        assertEquals(OperationType.ONCOLOGY, typeFound);
    }

    @Test
    public void givenOtherStringWhenDetermineFromShouldReturnOtherType() {
        OperationType typeFound = OperationType.determineFrom(OTHER_STRING);

        assertEquals(OperationType.OTHER, typeFound);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenUnexistingTypeStringWhenDetermineFromShouldThrowException() {
        OperationType.determineFrom(UNEXISTING_TYPE);
    }

    @Test
    public void givenEyeStringWhenIsValidShouldReturnTrue() {
        assertTrue(OperationType.isValid(EYE_STRING));
    }

    @Test
    public void givenHeartStringWhenIsValidShouldReturnTrue() {
        assertTrue(OperationType.isValid(HEART_STRING));
    }

    @Test
    public void givenMarrowStringWhenIsValidShouldReturnTrue() {
        assertTrue(OperationType.isValid(MARROW_STRING));
    }

    @Test
    public void givenOncologyStringWhenIsValidShouldReturnTrue() {
        assertTrue(OperationType.isValid(ONCOLOGY_STRING));
    }

    @Test
    public void givenOtherStringWhenIsValidShouldReturnTrue() {
        assertTrue(OperationType.isValid(OTHER_STRING));
    }

    @Test
    public void givenUnexistingTypeStringWhenIsValidShouldReturnFalse() {
        assertFalse(OperationType.isValid(UNEXISTING_TYPE));
    }

    @Test
    public void givenEyeTypeWhenIsRestrictedShouldReturnTrue() {
        assertTrue(OperationType.EYE.isRestricted());
    }

    @Test
    public void givenHeartTypeWhenIsRestrictedShouldReturnTrue() {
        assertTrue(OperationType.MARROW.isRestricted());
    }

    @Test
    public void givenMarrowTypeWhenIsRestrictedShouldReturnTrue() {
        assertTrue(OperationType.HEART.isRestricted());
    }

    @Test
    public void givenOncologyTypeWhenIsRestrictedShouldReturnFalse() {
        assertFalse(OperationType.ONCOLOGY.isRestricted());
    }

    @Test
    public void givenOtherTypeWhenIsRestrictedShouldReturnFalse() {
        assertFalse(OperationType.OTHER.isRestricted());
    }
}
