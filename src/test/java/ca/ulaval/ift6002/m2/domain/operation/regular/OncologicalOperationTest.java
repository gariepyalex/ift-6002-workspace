package ca.ulaval.ift6002.m2.domain.operation.regular;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;

public class OncologicalOperationTest {
    private static final String DESCRIPTIONS = "Descriptions";

    private static final OperationStatus STATUS = OperationStatus.FINISH;

    private Operation oncologicalOperation;

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
        oncologicalOperation = new OncologicalOperation(DESCRIPTIONS, surgeon, date, room, STATUS, patient);
    }

    @Test
    public void getTypeShouldReturnOperationOncologicalType() {

        OperationType resultOperationType = oncologicalOperation.getType();
        assertEquals(OperationType.ONCOLOGY, resultOperationType);
    }
}
