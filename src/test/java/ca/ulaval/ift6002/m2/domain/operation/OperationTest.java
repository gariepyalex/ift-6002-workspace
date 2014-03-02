package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.operation.dangerous.DangerousOperation;
import ca.ulaval.ift6002.m2.domain.operation.regular.RegularOperation;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.room.Room;
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

    @Before
    public void setupInstrument() {
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

    private void assertRegularOperation(Operation operation) {
        assertEquals(RegularOperation.class, operation.getClass());
    }

    private void assertDangerousOperation(Operation operation) {
        assertEquals(DangerousOperation.class, operation.getClass());
    }

    private void buildAnOperation() {
        // It could be any type of operation
        operation = createEligibleOperation();
    }

    private Operation createEligibleOperation() {
        Operation eligibleOperation;

        eligibleOperation = new Operation(DESCRIPTION, surgeon, date, room, OPERATION_STATUS, patient) {

            @Override
            public void add(Instrument instrument) {
            }
        };

        return eligibleOperation;
    }

}
