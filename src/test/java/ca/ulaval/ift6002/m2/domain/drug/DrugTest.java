package ca.ulaval.ift6002.m2.domain.drug;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

public class DrugTest {

    @Test
    public void verifyEqualsAndHashCode() {
        EqualsVerifier.forClass(Drug.class).suppress(Warning.STRICT_INHERITANCE).allFieldsShouldBeUsed().verify();
    }
}
