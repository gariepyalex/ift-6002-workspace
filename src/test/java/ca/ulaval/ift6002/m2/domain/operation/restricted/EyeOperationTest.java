package ca.ulaval.ift6002.m2.domain.operation.restricted;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.mockito.Mock;

import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.operation.restricted.EyeOperation;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;

public class EyeOperationTest {
    private static final String DESCRIPTIONS = "Descriptions";

    private static final OperationStatus STATUS = OperationStatus.FINISH;

    private Operation eyeOperation;

    @Mock
    private Surgeon surgeon;
    @Mock
    private Date date;
    @Mock
    private Room room;

    @Mock
    private Patient patient;

    @Test
    public void getTypeShouldReturnOperationEyeType() {
        eyeOperation = new EyeOperation(DESCRIPTIONS, surgeon, date, room, STATUS, patient);

        OperationType resultOperationType = eyeOperation.getType();
        assertEquals(OperationType.EYE, resultOperationType);
    }

}
