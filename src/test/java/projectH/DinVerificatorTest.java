package projectH;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

public class DinVerificatorTest {
	private final String A_VALID_DIN = "02236577";
	private final String AN_INVALID_DIN = "00000000";

	public void ifGivenValidDinShouldReturnTrue() {
		DinVerificator verificator = createVerificator();
		assertTrue(verificator.isDinValid(A_VALID_DIN));

	}

	public void ifGivenInvalidDinShouldReturnFalse() {
		DinVerificator verificator = createVerificator();
		assertFalse(verificator.isDinValid(AN_INVALID_DIN));
	}

	private DinVerificator createVerificator() {
		DinVerificator verificator = null;
		try {
			verificator = new DinVerificator();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return verificator;
	}

	public void doestNotThrowFileNotFoundException() {
		try {
			@SuppressWarnings("unused")
			DinVerificator verificator = new DinVerificator();
		} catch (FileNotFoundException e) {
			fail();
		}
	}

}
