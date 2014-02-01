package projectH.domain.instrument;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projectH.domain.instrument.Instrument.Status;

public class InstrumentTest {

	private static final String GIVEN_TYPECODE = "IT72353";
	private static final String DIFFERENT_TYPECODE = "IT12345";
	private static final String GIVEN_SERIAL_NUMBER = "23562543-3635345";
	private static final String DIFFERENT_SERIAL_NUMBER = null;
	private static final String EMPTY_SERIAL_NUMBER = "";
	private static final Status GIVEN_STATUS = Instrument.Status.UNUSED;
	private static final Status DIFFERENT_STATUS = Instrument.Status.SOILED;

	private Instrument anonymousInstrument;
	private Instrument instrument;
	private Instrument secondInstrument;
	private Instrument secondAnonymousInstrument;

	@Before
	public void createdInstruments() {
		instrument = new Instrument(GIVEN_TYPECODE, GIVEN_STATUS,
				GIVEN_SERIAL_NUMBER);
		secondInstrument = new Instrument(DIFFERENT_TYPECODE, DIFFERENT_STATUS,
				GIVEN_SERIAL_NUMBER);
		anonymousInstrument = new Instrument(GIVEN_TYPECODE, GIVEN_STATUS);
		secondAnonymousInstrument = new Instrument(DIFFERENT_TYPECODE,
				DIFFERENT_STATUS);
	}

	@Test
	public void createdAnonymousInstrumenTShouldHaveGivenStatus() {
		assertEquals(GIVEN_STATUS, anonymousInstrument.getStatus());
	}

	@Test
	public void createdAnonymousInstrumentShouldHaveGivenTypecode() {
		assertEquals(GIVEN_TYPECODE, anonymousInstrument.getTypecode());
	}

	@Test
	public void createdAnonymousInstrumentShouldHaveEmptySerialNumber() {
		assertEquals(EMPTY_SERIAL_NUMBER, anonymousInstrument.getSerial());
	}

	@Test
	public void createdAnonymousInstrumentShouldBeAnonymous() {
		assertTrue(anonymousInstrument.isAnonymous());
	}

	@Test
	public void modifyingStatusOfAnonymousInstrumentShouldReturnGivenDifferentStatus() {
		anonymousInstrument.setStatus(DIFFERENT_STATUS);
		assertEquals(DIFFERENT_STATUS, anonymousInstrument.getStatus());
	}

	@Test
	public void createdInstrumentShouldHaveGivenStatus() {
		assertEquals(GIVEN_STATUS, instrument.getStatus());
	}

	@Test
	public void createdInstrumentShouldHaveGivenTypecode() {
		assertEquals(GIVEN_TYPECODE, instrument.getTypecode());
	}

	@Test
	public void createdInstrumentShouldHaveGivenSerialNumber() {
		assertEquals(GIVEN_SERIAL_NUMBER, instrument.getSerial());
	}

	@Test
	public void createdInstrumentShouldNotBeAnonymous() {
		assertFalse(instrument.isAnonymous());
	}

	@Test
	public void modifyingStatusOfInstrumentShouldReturnGivenNewStatus() {
		instrument.setStatus(DIFFERENT_STATUS);
		assertEquals(DIFFERENT_STATUS, instrument.getStatus());
	}

	@Test
	public void twoInstrumentsWithSameSerialButDifferentAttributsShouldBeEqual() {
		assertEquals(instrument, secondInstrument);
	}

	@Test
	public void twoIdenticalInstrumentsShouldBeEqual() {
		Instrument sameInstrument = new Instrument(GIVEN_TYPECODE,
				GIVEN_STATUS, GIVEN_SERIAL_NUMBER);
		assertEquals(sameInstrument, sameInstrument);
	}

	@Test
	public void twoInstrumentsWithSameAttributsButDifferentSerialShouldNotBeEqual() {
		Instrument instrumentWithDifferentSerial = new Instrument(
				GIVEN_TYPECODE, GIVEN_STATUS, DIFFERENT_SERIAL_NUMBER);
		assertNotEquals(instrument, instrumentWithDifferentSerial);
	}

	@Test
	public void twoIdenticalAnonymousInstrumentsShouldNotBeEqual() {
		Instrument sameAnonymousInstrument = new Instrument(GIVEN_TYPECODE,
				GIVEN_STATUS);
		assertNotEquals(sameAnonymousInstrument, anonymousInstrument);
	}

	@Test
	public void twoDifferentAnonymousInstrumentsShouldNotBeEqual() {
		assertNotEquals(anonymousInstrument, secondAnonymousInstrument);
	}

}
