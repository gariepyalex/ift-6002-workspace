package ca.ulaval.ift6002.m2.domain.operation.dangerous;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InvalidInstrumentException;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;

@RunWith(MockitoJUnitRunner.class)
public class DangerousOperationTest {

    private static final OperationStatus PLANNED = OperationStatus.PLANNED;
    private static final String DESCRIPTION = "description";
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

    private DangerousOperation dangerousOperation;

    @Before
    public void setUp() {
        dangerousOperation = new DangerousOperation(DESCRIPTION, surgeon, date, room, PLANNED, patient) {
        };
        willReturn(false).given(instrument).isAnonymous();
        willReturn(true).given(anonymousInstrument).isAnonymous();
    }

    @Test(expected = InvalidInstrumentException.class)
    public void whenAddingAnInvalidInstrumentShouldThrowInvalidInstrumentException() {
        dangerousOperation.add(anonymousInstrument);
    }

    @Test
    public void whenAddingAnInstrumentCountShouldIncrease() {
        addInstrument();

        assertEquals(EXPECTED_INSTRUMENT_COUNT, dangerousOperation.countInstruments());
    }

    @Test
    public void whenAddingAnInstrumentOperationShouldHaveInstrument() {
        addInstrument();

        assertTrue(dangerousOperation.has(instrument));
    }

    private void addInstrument() {
        dangerousOperation.add(instrument);
    }
}
