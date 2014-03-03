package ca.ulaval.ift6002.m2.domain.operation.restricted;

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
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.operation.restricted.RestrictedOperation;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;

@RunWith(MockitoJUnitRunner.class)
public class RestrictedOperationTest {

    private static final OperationStatus PLANNED = OperationStatus.PLANNED;
    private static final String DESCRIPTION = "description";
    private static final Surgeon SURGEON = new Surgeon(12345);
    private static final Room ROOM = new Room("room");
    private static final Patient PATIENT = new Patient(12345);
    private static final Date DATE = new Date();

    @Mock
    private Instrument instrument;

    @Mock
    private Instrument anonymousInstrument;

    private RestrictedOperation restrictedOperation;

    @Before
    public void setUp() {
        restrictedOperation = createRestrictedOperation();

        willReturn(false).given(instrument).isAnonymous();
        willReturn(true).given(anonymousInstrument).isAnonymous();
    }

    private RestrictedOperation createRestrictedOperation() {
        RestrictedOperation restrictedOperation;

        restrictedOperation = new RestrictedOperation(DESCRIPTION, SURGEON, DATE, ROOM, PLANNED, PATIENT) {

            @Override
            public OperationType getType() {
                return OperationType.OTHER;
            }

        };

        return restrictedOperation;
    }

    @Test(expected = InvalidInstrumentException.class)
    public void whenAddingAnInvalidInstrumentShouldThrowInvalidInstrumentException() {
        restrictedOperation.add(anonymousInstrument);
    }

    @Test
    public void whenAddingAnInstrumentCountShouldIncrease() {
        addInstrument();

        assertEquals(1, restrictedOperation.countInstruments());
    }

    @Test
    public void whenAddingAnInstrumentOperationShouldHaveInstrument() {
        addInstrument();

        assertTrue(restrictedOperation.has(instrument));
    }

    private void addInstrument() {
        restrictedOperation.add(instrument);
    }
}
