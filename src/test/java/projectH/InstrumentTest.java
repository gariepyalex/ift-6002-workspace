package projectH;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InstrumentTest {

	private static final Instrument.Status A_STATUS = Instrument.Status.UNUSED;
	private static final Instrument.Status ANOTHER_STATUS = Instrument.Status.SOILED;
	private static final String A_SERIAL_NUMBER = "23562543-3635345";
	private static final Object ANONYMOUS_SERIAL_NUMBER = "";
	private Instrument anonymousInstrument;
	private Instrument instrumentWithSerial;

	@Before
	public void createAnonymousIntrument() {
		anonymousInstrument = new Instrument(A_STATUS);
		instrumentWithSerial = new Instrument(A_STATUS, A_SERIAL_NUMBER);
	}

	@Test
	public void creatingAnonymousInstrumentWithStatusShouldHaveGivenStatus() {
		assertEquals(A_STATUS, anonymousInstrument.getStatus());
	}

	@Test
	public void creatingAnonymousInstrumentWithStatusShouldHaveAnonymousSerialNumber() {
		assertEquals(ANONYMOUS_SERIAL_NUMBER, anonymousInstrument.getSerial());
	}

	@Test
	public void modifyingStatusOfInstrumentShouldReturnGivenNewStatus() {
		anonymousInstrument.setStatus(ANOTHER_STATUS);
		assertEquals(ANOTHER_STATUS, anonymousInstrument.getStatus());
	}

	@Test
	public void creatingInstrumentWithStatusAndSerialNumberShouldHaveGivenStatus() {
		assertEquals(A_STATUS, instrumentWithSerial.getStatus());
	}

	@Test
	public void creatingInstrumentWithStatusAndSerialNumberShouldHaveGivenSerialNumber() {
		assertEquals(A_SERIAL_NUMBER, instrumentWithSerial.getSerial());
	}
}
