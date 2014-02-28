package ca.ulaval.ift6002.m2.domain.instrument;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

public class SerialTest {

    @Test
    public void verifyEqualsAndHashCode() {
        EqualsVerifier.forClass(Serial.class).suppress(Warning.STRICT_INHERITANCE).allFieldsShouldBeUsed().verify();
    }
}
