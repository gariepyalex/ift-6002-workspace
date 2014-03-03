package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

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

    @Mock
    private Instrument instrument;

    @Mock
    private Instrument anonymousInstrument;

    @Mock
    private Surgeon surgeon;

    @Mock
    private Date date;

    @Mock
    private Room room;

    @Mock
    private Patient patient;

    private Operation operation;

    private OperationType OPERATION_TYPE_ONCOLOGY;

    @Before
    public void setupOperationTest() {
        willReturn(true).given(anonymousInstrument).isAnonymous();
        willReturn(false).given(instrument).isAnonymous();
        OPERATION_TYPE_ONCOLOGY = OperationType.ONCOLOGY;
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
    public void givenNewStatuswhenUpdatingInstrumentShouldCallSetNewStatus() {
        buildAnOperation();
        operation.add(instrument);

        operation.updateInstrumentStatus(instrument, "SOILED");

        verify(instrument).setStatus("SOILED");

    }

    @Test(expected = InvalidInstrumentException.class)
    public void givenInstrumentWithExistingSerialNumberWhenAddingInstrumentShouldThrowInvalidInstrumentException() {
        buildAnOperation();
        operation.add(instrument);
        operation.add(instrument);
    }

    private void buildAnOperation() {
        OperationFactory operationFactory = new OperationFactory();
        operation = operationFactory
                .create(OPERATION_TYPE_ONCOLOGY, "", surgeon, date, room, OPERATION_STATUS, patient);
    }

    private void buildAnOperationWithNoAddingOfInstrument() {
        // It could be any type of operation
        operation = createEligibleOperation();
    }

    private Operation createEligibleOperation() {
        Operation eligibleOperation;

        eligibleOperation = new Operation(DESCRIPTION, surgeon, date, room, OPERATION_STATUS, patient) {

            @Override
            public void add(Instrument instrument) {
            }

            @Override
            public OperationType getType() {
                return null;
            }

        };

        return eligibleOperation;
    }

}
