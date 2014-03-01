package ca.ulaval.ift6002.m2.domain.instrument;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

public class TypecodeTest {

	@Test
	public void verifyEqualsAndHashCode() {
		EqualsVerifier.forClass(Typecode.class)
				.suppress(Warning.STRICT_INHERITANCE).allFieldsShouldBeUsed()
				.verify();
	}
}
