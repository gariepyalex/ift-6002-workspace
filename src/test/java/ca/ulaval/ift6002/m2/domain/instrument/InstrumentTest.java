package ca.ulaval.ift6002.m2.domain.instrument;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

public class InstrumentTest {

    private static final Serial SERIAL = new Serial("23562543-3635345");
    private static final Serial AN_OTHER_SERIAL = new Serial("ABCDEFGH");
    private static final Serial ANONYMOUS_SERIAL = new Serial("");

    private Instrument instrument;

    @Before
    public void setUp() {
        instrument = mock(Instrument.class, CALLS_REAL_METHODS);

        willReturn(SERIAL).given(instrument).getSerial();
    }

    @Test
    public void givenInstrumentWhenCheckingForSerialShouldReturnTrue() {
        assertTrue(instrument.hasASerial());
    }

    @Test
    public void givenAnonymousInstrumentWhenCheckingForSerialShouldReturnFalse() {
        willReturn(ANONYMOUS_SERIAL).given(instrument).getSerial();

        assertFalse(instrument.hasASerial());
    }

    @Test
    public void whenComparingSerialWithSameInstrumentShouldReturnTrue() {
        assertTrue(instrument.hasSameSerial(instrument));
    }

    @Test
    public void whenComparingSerialWithOtherInstrumentShouldReturnFalse() {
        Instrument other = setUpOtherInstrument();

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
    public void whenSerialIsNotEmptyThenIsAnonymousShouldReturnFalse() {
        assertFalse(instrument.isAnonymous());
    }

    private Instrument setUpOtherInstrument() {
        Instrument other = mock(Instrument.class, CALLS_REAL_METHODS);
        willReturn(AN_OTHER_SERIAL).given(other).getSerial();

        return other;
    }

}
