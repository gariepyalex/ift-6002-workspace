package ca.ulaval.ift6002.m2.domain.operation.restricted;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.operation.restricted.MarrowOperation;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;

public class MarrowOperationTest {
    private static final String DESCRIPTIONS = "Descriptions";

    private static final OperationStatus STATUS = OperationStatus.FINISH;

    private Operation marrowOperation;

    @Mock
    private Surgeon surgeon;
    @Mock
    private Date date;
    @Mock
    private Room room;

    @Mock
    private Patient patient;

    @Before
    public void createOperation() {
        marrowOperation = new MarrowOperation(DESCRIPTIONS, surgeon, date, room, STATUS, patient);
    }

    @Test
    public void getTypeShouldReturnOperationMarrowType() {

        OperationType resultOperationType = marrowOperation.getType();
        assertEquals(OperationType.MARROW, resultOperationType);
    }
}
