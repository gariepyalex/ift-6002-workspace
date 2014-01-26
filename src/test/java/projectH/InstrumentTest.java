package projectH;

import static org.junit.Assert.*;

import org.junit.Test;

public class InstrumentTest {

	private static final Instrument.Status GIVEN_STATUS = Instrument.Status.UNUSED;
	private static final Instrument.Status NEW_STATUS = Instrument.Status.SOILED;
	private static final String GIVEN_SERIAL_NUMBER = "23562543-3635345";
	private static final String EMPTY_SERIAL_NUMBER = "";
	private static final String GIVEN_TYPECODE = "IT72353";
	private Instrument anonymousInstrument;// with typecode, status and no
											// serial number
	private Instrument instrument;// with typecode, status and serial
									// number

	public void createAnonymousInstrument() {
		anonymousInstrument = new Instrument(GIVEN_TYPECODE, GIVEN_STATUS);
	}

	public void createInstrument() {
		instrument = new Instrument(GIVEN_TYPECODE, GIVEN_STATUS,
				GIVEN_SERIAL_NUMBER);
	}

	@Test
	public void creatingAnonymousInstrumenTShouldHaveGivenStatus() {
		createAnonymousInstrument();
		assertEquals(GIVEN_STATUS, anonymousInstrument.getStatus());
	}

	@Test
	public void creatingAnonymousInstrumentShouldHaveGivenTypecode() {
		createAnonymousInstrument();
		assertEquals(GIVEN_TYPECODE, anonymousInstrument.getTypecode());
	}

	@Test
	public void creatingAnonymousInstrumentShouldHaveEmptySerialNumber() {
		createAnonymousInstrument();
		assertEquals(EMPTY_SERIAL_NUMBER, anonymousInstrument.getSerial());
	}

	@Test
	public void creatingAnonymousInstrumentShouldBeAnonymous() {
		createAnonymousInstrument();
		assertTrue(anonymousInstrument.isAnonymous());
	}

	@Test
	public void modifyingStatusOfAnonymousInstrumentShouldReturnGivenNewStatus() {
		createAnonymousInstrument();
		anonymousInstrument.setStatus(NEW_STATUS);
		assertEquals(NEW_STATUS, anonymousInstrument.getStatus());
	}

	@Test
	public void creatingInstrumentShouldHaveGivenStatus() {
		createInstrument();
		assertEquals(GIVEN_STATUS, instrument.getStatus());
	}

	@Test
	public void creatingInstrumentShouldHaveGivenTypecode() {
		createInstrument();
		assertEquals(GIVEN_TYPECODE, instrument.getTypecode());
	}

	@Test
	public void creatingInstrumentShouldHaveGivenSerialNumber() {
		createInstrument();
		assertEquals(GIVEN_SERIAL_NUMBER, instrument.getSerial());
	}

	@Test
	public void creatingInstrumentShouldNotBeAnonymous() {
		createInstrument();
		assertFalse(instrument.isAnonymous());
	}

	@Test
	public void modifyingStatusOfInstrumentShouldReturnGivenNewStatus() {
		createInstrument();
		instrument.setStatus(NEW_STATUS);
		assertEquals(NEW_STATUS, instrument.getStatus());
	}
}
