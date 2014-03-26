package ca.ulaval.ift6002.m2.domain.drug;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class DrugTest {

    private Drug drug;

    @Before
    public void setUp() {
        drug = mock(Drug.class, CALLS_REAL_METHODS);
    }

    @Test
    public void givenAnInteractingDrugShouldReturnTrueWhenInteractingWithIt() {
        Drug interactingDrug = mock(Drug.class);
        Collection<Drug> interactingDrugs = Arrays.asList(interactingDrug);
        willReturn(interactingDrugs).given(drug).getInteractingDrugs();

        boolean isInteractingWith = drug.isInteractingWith(interactingDrug);

        assertTrue(isInteractingWith);
    }

    @Test
    public void givenAnUninteractingDrugShouldReturnTrueWhenInteractingWithIt() {
        willReturn(Collections.emptyList()).given(drug).getInteractingDrugs();
        Drug uninteractingDrug = mock(Drug.class);

        boolean isInteractingWith = drug.isInteractingWith(uninteractingDrug);

        assertFalse(isInteractingWith);
    }
}
