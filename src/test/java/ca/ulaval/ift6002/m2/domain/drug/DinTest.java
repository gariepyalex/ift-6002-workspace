package ca.ulaval.ift6002.m2.domain.drug;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DinTest {

    private static final String EMPTY_NAME = "";
    private static final String NON_EMPTY_NAME = "123456";

    @Test
    public void givenEmptyNameWhenIsEmptyShouldReturnTrue() {
        Din din = new Din(EMPTY_NAME);
        boolean isEmpty = din.isEmpty();
        assertTrue(isEmpty);
    }

    @Test
    public void givenNonEmptyNameWhenIsEmptyShouldReturnFalse() {
        Din din = new Din(NON_EMPTY_NAME);
        boolean isEmpty = din.isEmpty();
        assertFalse(isEmpty);
    }
}
