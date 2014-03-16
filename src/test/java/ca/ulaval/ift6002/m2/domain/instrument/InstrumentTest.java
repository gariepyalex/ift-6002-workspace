package ca.ulaval.ift6002.m2.domain.instrument;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class InstrumentTest {

    private static final Typecode TYPECODE = new Typecode("IT72353");

    private static final Serial SERIAL = new Serial("23562543-3635345");
    private static final Serial AN_OTHER_SERIAL = new Serial("ABCDEFGH");
    private static final Serial ANONYMOUS_SERIAL = new Serial("");

    private static final InstrumentStatus STATUS = InstrumentStatus.UNUSED;

    private Instrument instrument;

    private class TestInstrument extends Instrument {

        private InstrumentStatus status;
        private Serial serial;
        private Typecode typecode;

        public TestInstrument(Typecode typecode, InstrumentStatus status, Serial serialNumber) {
            this.status = status;
            this.typecode = typecode;
            this.serial = serialNumber;
        }

        @Override
        public InstrumentStatus getStatus() {
            return status;
        }

        @Override
        public Typecode getTypecode() {
            return typecode;
        }

        @Override
        public Serial getSerial() {
            return serial;
        }

        @Override
        protected void setStatus(InstrumentStatus status) {
            this.status = status;
        }
    }

    @Before
    public void setUp() {
        instrument = new TestInstrument(TYPECODE, STATUS, SERIAL);
    }

    @Test
    public void givenInstrumentWhenCheckingForSerialShouldReturnTrue() {
        assertTrue(instrument.hasASerial());
    }

    @Test
    public void givenAnonymousInstrumentWhenCheckingForSerialShouldReturnFalse() {
        Instrument anonymous = new TestInstrument(TYPECODE, STATUS, ANONYMOUS_SERIAL);

        assertFalse(anonymous.hasASerial());
    }

    @Test
    public void whenComparingSerialWithSameInstrumentShouldReturnTrue() {
        assertTrue(instrument.hasSameSerial(instrument));
    }

    @Test
    public void whenComparingSerialWithOtherInstrumentShouldReturnFalse() {
        Instrument other = new TestInstrument(TYPECODE, STATUS, AN_OTHER_SERIAL);

        assertFalse(instrument.hasSameSerial(other));
    }

    @Test
    public void whenCheckingSerialWithSameSerialShouldReturnTrue() {
        assertTrue(instrument.hasSerial(SERIAL));
    }

    @Test
    public void whenCheckSerialWithOtherSerialShouldReturnFalse() {
        assertFalse(instrument.hasSerial(AN_OTHER_SERIAL));
    }

    @Test
    public void whenChangingStatusShouldHaveNewStatus() {
        instrument.changeTo(STATUS);

        assertEquals(STATUS, instrument.getStatus());
    }

    @Test
    public void whenSerialIsNotEmptyThenIsAnonymousShouldReturnFalse() {
        assertFalse(instrument.isAnonymous());
    }

}
