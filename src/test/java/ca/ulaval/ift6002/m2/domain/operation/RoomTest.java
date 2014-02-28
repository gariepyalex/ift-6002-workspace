package ca.ulaval.ift6002.m2.domain.operation;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

public class RoomTest {

    @Test
    public void verifyEqualsAndHashCode() {
        EqualsVerifier.forClass(Room.class).suppress(Warning.STRICT_INHERITANCE).allFieldsShouldBeUsed().verify();
    }
}
