package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.operation.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.patient.Patient;

@RunWith(MockitoJUnitRunner.class)
public class OperationTest {

    private static final OperationStatus OPERATION_STATUS = OperationStatus.PLANNED;
    private static final String DESCRIPTION = "description";
    private static final Room ROOM = new Room("room");
    private static final Surgeon SURGEON = new Surgeon(12345);
    private static final Date DATE = new Date();
    private static final Patient PATIENT = new Patient(12345);

    private static final InstrumentStatus AN_INSTRUMENT_STATUS = InstrumentStatus.SOILED;

    @Mock
    private Instrument instrument;

    @Mock
    private Instrument anonymousInstrument;

    private Operation operation;

    @Before
    public void setupOperationTest() {
        willReturn(true).given(anonymousInstrument).isAnonymous();
        willReturn(false).given(instrument).isAnonymous();
    }

    @Test
    public void givenOperationShouldHaveZeroInstrument() {
        buildAnOperation();

        assertEquals(0, operation.countInstruments());
    }

    @Test
    public void givenOperationShouldNotHaveAnyInstrument() {
        buildAnOperation();
        boolean hasInstrument = operation.has(instrument);
        assertFalse(hasInstrument);
    }

    @Test
    public void givenNewStatusWhenBookmarkInstrumentShouldCallSetNewStatus() {
        buildAnOperation();
        operation.add(instrument);
        willReturn(true).given(instrument).hasSerial(any(Serial.class));

        operation.bookmarkInstrumentToStatus(new Serial("abc"), AN_INSTRUMENT_STATUS);

        verify(instrument).changeTo(AN_INSTRUMENT_STATUS);
    }

    @Test(expected = IllegalStateException.class)
    public void givenInstrumentWithExistingSerialNumberWhenAddingInstrumentShouldThrowException() {
        buildAnOperation();
        operation.add(instrument);
        operation.add(instrument);
    }

    private void buildAnOperation() {
        operation = new Operation(DESCRIPTION, SURGEON, DATE, ROOM, OPERATION_STATUS, PATIENT) {

            @Override
            protected boolean isInstrumentElligible(Instrument instrument) {
                return true;
            }

            @Override
            public OperationType getType() {
                return OperationType.OTHER;
            }

        };
    }

}
