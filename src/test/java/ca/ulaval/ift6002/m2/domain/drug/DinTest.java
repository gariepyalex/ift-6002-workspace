package ca.ulaval.ift6002.m2.domain.drug;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DinTest {

    private static final String EMPTY_NAME = "";

    @Test
    public void givenEmptyNameWhenIsEmptyShouldReturnTrue() {
        Din din = new Din(EMPTY_NAME);
        boolean isEmpty = din.isEmpty();
        assertTrue(isEmpty);
    }
}
