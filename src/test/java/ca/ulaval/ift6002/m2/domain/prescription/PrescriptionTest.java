package ca.ulaval.ift6002.m2.domain.prescription;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

public class PrescriptionTest {

    @Test
    public void verifyEqualsAndHashCode() {
        EqualsVerifier.forClass(Prescription.class).suppress(Warning.STRICT_INHERITANCE).allFieldsShouldBeUsed()
                .verify();
    }
}
