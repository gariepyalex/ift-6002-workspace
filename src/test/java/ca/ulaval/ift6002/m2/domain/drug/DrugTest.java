package ca.ulaval.ift6002.m2.domain.drug;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

public class DrugTest {

    private static final Din A_DIN = new Din("A din");
    private static final Din AN_EMPTY_DIN = new Din("");

    private Drug drug;

    @Before
    public void setUp() {
        drug = mock(Drug.class, CALLS_REAL_METHODS);
    }

    @Test
    public void givenDrugWithDinWhenCallingHasDinShouldReturnTrue() {
        willReturn(A_DIN).given(drug).getDin();

        boolean hasDin = drug.hasDin();

        assertTrue(hasDin);
    }

    @Test
    public void givenDrugWithEmptyDinWhenCallingHasDinShouldReturnFalse() {
        willReturn(AN_EMPTY_DIN).given(drug).getDin();

        boolean hasDin = drug.hasDin();

        assertFalse(hasDin);
    }
}
