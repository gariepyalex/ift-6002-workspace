package ca.ulaval.ift6002.m2.domain.instrument;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InstrumentTest {

    private static final Typecode TYPECODE = new Typecode("IT72353");
    private static final Typecode ANOTHER_TYPECODE = new Typecode("IT12345");
    private static final Serial SERIAL_NUMBER = new Serial("23562543-3635345");
    private static final Serial ANOTHER_SERIAL_NUMBER = new Serial("231313131-1313131");
    private static final InstrumentStatus STATUS = InstrumentStatus.UNUSED;
    private static final InstrumentStatus ANOTHER_STATUS = InstrumentStatus.SOILED;

    private Instrument anonymousInstrument;
    private Instrument instrument;
    private Instrument secondInstrument;
    private Instrument secondAnonymousInstrument;

    @Before
    public void createdInstruments() {
        instrument = new Instrument(TYPECODE, STATUS, SERIAL_NUMBER);
        secondInstrument = new Instrument(ANOTHER_TYPECODE, ANOTHER_STATUS, SERIAL_NUMBER);
        anonymousInstrument = new Instrument(TYPECODE, STATUS);
        secondAnonymousInstrument = new Instrument(ANOTHER_TYPECODE, ANOTHER_STATUS);
    }

    /*
     * @Test(expected = IllegalArgumentException.class) public void
     * creatingaAnonymousInstrumentWithEmptyTypeCodeShouldThrowInvalidArgumentException
     * () { new Instrument(EMPTY_TYPECODE, A_STATUS); }
     * 
     * @Test(expected = IllegalArgumentException.class) public void
     * creatingInstrumentWithEmptyTypeCodeShouldThrowInvalidArgumentException()
     * { new Instrument(EMPTY_TYPECODE, A_STATUS, A_SERIAL_NUMBER); }
     * 
     * @Test(expected = IllegalArgumentException.class) public void
     * creatingInstrumentWithNullSerialNumberShouldThrowInvalidArgumentException
     * () { new Instrument(EMPTY_TYPECODE, A_STATUS, NULL_SERIAL_NUMBER); }
     */

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
        Instrument sameInstrument = new Instrument(TYPECODE, STATUS, SERIAL_NUMBER);
        assertEquals(sameInstrument, sameInstrument);
    }

    @Test
    public void twoInstrumentsWithSameAttributsButDifferentSerialShouldNotBeEqual() {
        Instrument instrumentWithDifferentSerial = new Instrument(TYPECODE, STATUS, ANOTHER_SERIAL_NUMBER);
        assertNotEquals(instrument, instrumentWithDifferentSerial);
    }

    @Test
    public void twoIdenticalAnonymousInstrumentsShouldNotBeEqual() {
        Instrument sameAnonymousInstrument = new Instrument(TYPECODE, STATUS);
        assertNotEquals(sameAnonymousInstrument, anonymousInstrument);
    }

    @Test
    public void twoDifferentAnonymousInstrumentsShouldNotBeEqual() {
        assertNotEquals(anonymousInstrument, secondAnonymousInstrument);
    }
}
