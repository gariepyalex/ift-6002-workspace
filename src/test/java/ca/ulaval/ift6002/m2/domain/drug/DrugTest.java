package ca.ulaval.ift6002.m2.domain.drug;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class DrugTest {

    private static final String A_NAME = "A name";

    @Test
    public void whenGetDrugFromNameShouldHaveName() {
        Drug drug = Drug.fromName(A_NAME);
        String nameRetrieved = drug.getBrandName();
        assertEquals(A_NAME, nameRetrieved);
    }

    @Test
    public void whenGetDrugFromNameShouldNotHaveDin() {
        Drug drug = Drug.fromName(A_NAME);
        boolean hasDin = drug.hasDin();
        assertFalse(hasDin);
    }
}
