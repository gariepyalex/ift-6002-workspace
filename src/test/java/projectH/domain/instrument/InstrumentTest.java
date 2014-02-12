package projectH.domain.instrument;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InstrumentTest {

    private static final String A_TYPECODE = "IT72353";
    private static final String DIFFERENT_TYPECODE = "IT12345";
    private static final String A_SERIAL_NUMBER = "23562543-3635345";
    private static final String DIFFERENT_SERIAL_NUMBER = "231313131-1313131";
    private static final String NULL_SERIAL_NUMBER = null;
    private static final String EMPTY_TYPECODE = "";
    private static final InstrumentStatus A_STATUS = InstrumentStatus.UNUSED;
    private static final InstrumentStatus DIFFERENT_STATUS = InstrumentStatus.SOILED;

    private Instrument anonymousInstrument;
    private Instrument instrument;
    private Instrument secondInstrument;
    private Instrument secondAnonymousInstrument;

    @Before
    public void createdInstruments() {
        instrument = new Instrument(A_TYPECODE, A_STATUS, A_SERIAL_NUMBER);
        secondInstrument = new Instrument(DIFFERENT_TYPECODE, DIFFERENT_STATUS, A_SERIAL_NUMBER);
        anonymousInstrument = new Instrument(A_TYPECODE, A_STATUS);
        secondAnonymousInstrument = new Instrument(DIFFERENT_TYPECODE, DIFFERENT_STATUS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingaAnonymousInstrumentWithEmptyTypeCodeShouldThrowInvalidArgumentException() {
        new Instrument(EMPTY_TYPECODE, A_STATUS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingInstrumentWithEmptyTypeCodeShouldThrowInvalidArgumentException() {
        new Instrument(EMPTY_TYPECODE, A_STATUS, A_SERIAL_NUMBER);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingInstrumentWithNullSerialNumberShouldThrowInvalidArgumentException() {
        new Instrument(EMPTY_TYPECODE, A_STATUS, NULL_SERIAL_NUMBER);
    }

    @Test
    public void createdAnonymousInstrumentShouldBeAnonymous() {
        assertTrue(anonymousInstrument.isAnonymous());
    }

    @Test
    public void createdInstrumentShouldNotBeAnonymous() {
        assertFalse(instrument.isAnonymous());
    }

    @Test
    public void twoInstrumentsWithSameSerialButDifferentAttributsShouldBeEqual() {
        assertEquals(instrument, secondInstrument);
    }

    @Test
    public void twoIdenticalInstrumentsShouldBeEqual() {
        Instrument sameInstrument = new Instrument(A_TYPECODE, A_STATUS, A_SERIAL_NUMBER);
        assertEquals(sameInstrument, sameInstrument);
    }

    @Test
    public void twoInstrumentsWithSameAttributsButDifferentSerialShouldNotBeEqual() {
        Instrument instrumentWithDifferentSerial = new Instrument(A_TYPECODE, A_STATUS, DIFFERENT_SERIAL_NUMBER);
        assertNotEquals(instrument, instrumentWithDifferentSerial);
    }

    @Test
    public void twoIdenticalAnonymousInstrumentsShouldNotBeEqual() {
        Instrument sameAnonymousInstrument = new Instrument(A_TYPECODE, A_STATUS);
        assertNotEquals(sameAnonymousInstrument, anonymousInstrument);
    }

    @Test
    public void twoDifferentAnonymousInstrumentsShouldNotBeEqual() {
        assertNotEquals(anonymousInstrument, secondAnonymousInstrument);
    }
}
