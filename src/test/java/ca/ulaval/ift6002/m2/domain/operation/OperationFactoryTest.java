package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.operation.regular.OncologicalOperation;
import ca.ulaval.ift6002.m2.domain.operation.restricted.EyeOperation;
import ca.ulaval.ift6002.m2.domain.operation.restricted.HeartOperation;
import ca.ulaval.ift6002.m2.domain.operation.restricted.MarrowOperation;
import ca.ulaval.ift6002.m2.domain.operation.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.room.Room;

@RunWith(MockitoJUnitRunner.class)
public class OperationFactoryTest {

    private static final OperationType A_TYPE = OperationType.EYE;
    private static final String A_DESCRIPTION = "description";
    private static final Surgeon A_SURGEON = new Surgeon(12345);
    private static final Date A_DATE = new Date();
    private static final Room A_ROOM = new Room("room");
    private static final OperationStatus A_STATUS = OperationStatus.IN_PROGRESS;
    private static final Patient A_PATIENT = null;

    @Mock
    private OperationData operationData;

    private OperationFactory operationFactory;

    @Before
    public void setUp() {
        operationFactory = mock(OperationFactory.class, CALLS_REAL_METHODS);
    }

    @Test
    public void whenTypeIsEyeShouldReturnEyeOperation() {
        Operation operation = operationFactory.create(OperationType.EYE, operationData);

        assertEquals(EyeOperation.class, operation.getClass());
    }

    @Test
    public void whenTypeIsHeartShouldReturnHeartOperation() {
        Operation operation = operationFactory.create(OperationType.HEART, operationData);

        assertEquals(HeartOperation.class, operation.getClass());
    }

    @Test
    public void whenTypeIsMarrowShouldReturnMarrowOperation() {
        Operation operation = operationFactory.create(OperationType.MARROW, operationData);

        assertEquals(MarrowOperation.class, operation.getClass());
    }

    @Test
    public void whenTypeIsOncolyShouldReturnOncologicalOperation() {
        Operation operation = operationFactory.create(OperationType.ONCOLOGY, operationData);

        assertEquals(OncologicalOperation.class, operation.getClass());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateWithUnknownTypeShouldThrowException() {
        operationFactory.create(OperationType.OTHER, operationData);
    }

    @Test
    public void whenCreateWithManyArgumentsShouldCreateAnOperationData() {
        willReturn(operationData).given(operationFactory).createOperationData(A_TYPE, A_DESCRIPTION, A_SURGEON, A_DATE,
                A_ROOM, A_STATUS, A_PATIENT);
        operationFactory.create(A_TYPE, A_DESCRIPTION, A_SURGEON, A_DATE, A_ROOM, A_STATUS, A_PATIENT);
        verify(operationFactory).createOperationData(A_TYPE, A_DESCRIPTION, A_SURGEON, A_DATE, A_ROOM, A_STATUS,
                A_PATIENT);
    }

}
