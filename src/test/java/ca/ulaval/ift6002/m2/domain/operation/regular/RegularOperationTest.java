package ca.ulaval.ift6002.m2.domain.operation.regular;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;

@RunWith(MockitoJUnitRunner.class)
public class RegularOperationTest {

    private static final int EXPECTED_INSTRUMENT_COUNT = 1;

    @Mock
    private Surgeon surgeon;

    @Mock
    private Date date;

    @Mock
    private Room room;

    @Mock
    private Patient patient;

    @Mock
    private Instrument instrument;

    @Mock
    private Instrument anonymousInstrument;

    @InjectMocks
    private RegularOperation regularOperation;

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

    @Test
    public void getTypeShouldReturnOther() {
        OperationType result = regularOperation.getType();

        assertEquals(OperationType.OTHER, result);
    }

    private void addInstrument() {
        regularOperation.add(instrument);
    }

    private void addAnonymousInstrument() {
        regularOperation.add(anonymousInstrument);
    }

}
