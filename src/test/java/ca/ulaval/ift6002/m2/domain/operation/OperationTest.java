package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InvalidInstrumentException;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;

@RunWith(MockitoJUnitRunner.class)
public class OperationTest {

    private static final OperationStatus OPERATION_STATUS = OperationStatus.PLANNED;
    private static final String DESCRIPTION = "description";
    private static final Room ROOM = new Room("room");
    private static final Surgeon SURGEON = new Surgeon(12345);
    private static final Date DATE = new Date();
    private static final Patient PATIENT = new Patient(12345);

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
        buildAnOperationWithNoAddingOfInstrument();

        assertEquals(0, operation.countInstruments());
    }

    @Test
    public void givenOperationShouldNotHaveAnyInstrument() {
        buildAnOperationWithNoAddingOfInstrument();
        boolean hasInstrument = operation.has(instrument);
        assertFalse(hasInstrument);
    }

    @Test
    public void givenNewStatusWhenUpdatingInstrumentShouldCallSetNewStatus() {
        buildAnOperation();
        operation.add(instrument);

        operation.updateInstrumentStatus(instrument, "SOILED");

        verify(instrument).setStatus("SOILED");

    }

    @Test(expected = InvalidInstrumentException.class)
    public void givenInstrumentWithExistingSerialNumberWhenAddingInstrumentShouldThrowException() {
        buildAnOperation();
        operation.add(instrument);
        operation.add(instrument);
    }

    private void buildAnOperationWithNoAddingOfInstrument() {
        // It could be any type of operation
        createEligibleOperation();
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

    private void createEligibleOperation() {
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
