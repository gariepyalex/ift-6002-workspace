package ca.ulaval.ift6002.m2.domain.operation.regular;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.operation.patient.Patient;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.operation.surgeon.Surgeon;

@RunWith(MockitoJUnitRunner.class)
public class RegularOperationTest {

    private static final OperationStatus OPERATION_STATUS = OperationStatus.PLANNED;
    private static final String DESCRIPTION = "description";
    private static final Room ROOM = new Room("room");
    private static final Surgeon SURGEON = new Surgeon(12345);
    private static final Date DATE = new Date();
    private static final Patient PATIENT = new Patient(12345);

    @Mock
    private Instrument instrument;

    private RegularOperation regularOperation;

    @Before
    public void setUp() {
        buildAnOperation();
    }

    @Test
    public void whenCheckingForInstrumentElligibilityShouldAlwaysReturnTrue() {
        assertTrue(regularOperation.isInstrumentElligible(instrument));
    }

    private void buildAnOperation() {
        regularOperation = new RegularOperation(DESCRIPTION, SURGEON, DATE, ROOM, OPERATION_STATUS, PATIENT) {

            @Override
            public OperationType getType() {
                return OperationType.OTHER;
            }

        };
    }

}
