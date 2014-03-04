package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.operation.regular.OncologicalOperation;
import ca.ulaval.ift6002.m2.domain.operation.restricted.EyeOperation;
import ca.ulaval.ift6002.m2.domain.operation.restricted.HeartOperation;
import ca.ulaval.ift6002.m2.domain.operation.restricted.MarrowOperation;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.operation.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.patient.Patient;

@RunWith(MockitoJUnitRunner.class)
public class OperationFactoryTest {

    private static final OperationStatus STATUS = OperationStatus.PLANNED;
    private static final String DESCRIPTION = "description";
    private static final Surgeon SURGEON = new Surgeon(12345);
    private static final Room ROOM = new Room("room");
    private static final Patient PATIENT = new Patient(12345);
    private static final Date DATE = new Date();

    private OperationFactory factory;

    @Before
    public void setUp() {
        factory = new OperationFactory();
    }

    @Test
    public void whenTypeIsEyeShouldReturnEyeOperation() {
        Operation operation = factory.create(OperationType.EYE, DESCRIPTION, SURGEON, DATE, ROOM, STATUS, PATIENT);

        assertTrue(operation instanceof EyeOperation);
    }

    @Test
    public void whenTypeIsHeartShouldReturnHeartOperation() {
        Operation operation = factory.create(OperationType.HEART, DESCRIPTION, SURGEON, DATE, ROOM, STATUS, PATIENT);

        assertTrue(operation instanceof HeartOperation);
    }

    @Test
    public void whenTypeIsMarrowShouldReturnMarrowOperation() {
        Operation operation = factory.create(OperationType.MARROW, DESCRIPTION, SURGEON, DATE, ROOM, STATUS, PATIENT);

        assertTrue(operation instanceof MarrowOperation);
    }

    @Test
    public void whenTypeIsOncolyShouldReturnOncologicalOperation() {
        Operation operation = factory.create(OperationType.ONCOLOGY, DESCRIPTION, SURGEON, DATE, ROOM, STATUS, PATIENT);

        assertTrue(operation instanceof OncologicalOperation);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateWithUnknownTypeShouldThrowException() {
        factory.create(OperationType.OTHER, DESCRIPTION, SURGEON, DATE, ROOM, STATUS, PATIENT);
    }

}
