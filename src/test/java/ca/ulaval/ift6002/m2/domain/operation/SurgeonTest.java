package ca.ulaval.ift6002.m2.domain.operation;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

public class SurgeonTest {

    @Test
    public void verifyEqualsAndHashCode() {
        EqualsVerifier.forClass(Surgeon.class).suppress(Warning.STRICT_INHERITANCE).allFieldsShouldBeUsed().verify();
    }
}
