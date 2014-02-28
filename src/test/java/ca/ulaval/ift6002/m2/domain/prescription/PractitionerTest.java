package ca.ulaval.ift6002.m2.domain.prescription;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

public class PractitionerTest {

    @Test
    public void verifyEqualsAndHashCode() {
        EqualsVerifier.forClass(Practitioner.class).suppress(Warning.STRICT_INHERITANCE).allFieldsShouldBeUsed()
                .verify();
    }
}
