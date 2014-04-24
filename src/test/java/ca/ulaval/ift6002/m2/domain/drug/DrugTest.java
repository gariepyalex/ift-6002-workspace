package ca.ulaval.ift6002.m2.domain.drug;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class DrugTest {

    private static final Din A_DIN = new Din("A din");
    private static final Din AN_EMPTY_DIN = new Din("");

    private Drug drug;

    private Drug interactingDrug;

    @Before
    public void setUp() {
        drug = mock(Drug.class, CALLS_REAL_METHODS);
        interactingDrug = mock(Drug.class);
    }

    @Test
    public void givenInteractingDrugsWhenVerifyingInteractionShouldReturnTrue() {
        doNothing().when(drug).interactWith(getInteractingDrugs());
        willReturn(getInteractingDrugs()).given(drug).getInteractingDrugs();

        drug.interactWith(getInteractingDrugs());
        boolean isInteracting = drug.isInteractingWith(interactingDrug);
        assertTrue(isInteracting);
    }

    @Test
    public void givenNoInteractingDrugsWhenVerifyingInteractionShouldReturnFalse() {
        willReturn(Collections.emptyList()).given(drug).getInteractingDrugs();
        boolean isInteracting = drug.isInteractingWith(interactingDrug);
        assertFalse(isInteracting);
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

    private Collection<Drug> getInteractingDrugs() {
        return Arrays.asList(interactingDrug);
    }
}
