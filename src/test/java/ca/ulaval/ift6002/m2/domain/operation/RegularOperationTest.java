package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;

@RunWith(MockitoJUnitRunner.class)
public class RegularOperationTest {

    private static final int EXPECTED_INSTRUMENT_COUNT = 1;

    @Mock
    private Operation.Builder builder;

    @InjectMocks
    private RegularOperation regularOperation;

    @Mock
    private Instrument instrument;

    @Mock
    private Instrument anonymousInstrument;

    @Before
    public void setUp() {
        willReturn(false).given(instrument).isAnonymous();
        willReturn(true).given(anonymousInstrument).isAnonymous();
    }

    @Test
    public void whenAddingAnAnonymousInstrumentCountShouldIncrease() {
        addAnonymousInstrument();

        assertEquals(EXPECTED_INSTRUMENT_COUNT, regularOperation.countInstruments());
    }

    @Test
    public void whenAddingAnAnonymousInstrumentOperationShouldHaveInstrument() {
        addAnonymousInstrument();

        assertTrue(regularOperation.has(anonymousInstrument));
    }

    @Test
    public void whenAddingAnInstrumentCountShouldIncrease() {
        addInstrument();

        assertEquals(EXPECTED_INSTRUMENT_COUNT, regularOperation.countInstruments());
    }

    @Test
    public void whenAddingAnInstrumentOperationShouldHaveInstrument() {
        addInstrument();

        assertTrue(regularOperation.has(instrument));
    }

    private void addInstrument() {
        regularOperation.add(instrument);
    }

    private void addAnonymousInstrument() {
        regularOperation.add(anonymousInstrument);
    }

}
