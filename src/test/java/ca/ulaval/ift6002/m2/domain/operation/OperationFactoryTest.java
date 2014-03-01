package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;

@RunWith(MockitoJUnitRunner.class)
public class OperationFactoryTest {

    private static final String DESCRIPTION = "Description";
    private static final OperationStatus STATUS = OperationStatus.PLANNED;

    private OperationFactory factory;

    @Mock
    Surgeon surgeon;

    @Mock
    Date date;

    @Mock
    Room room;

    @Mock
    Patient patient;

    @Before
    public void setUp() {
        factory = new OperationFactory();
    }

    @Test
    public void whenTypeIsEyeShouldReturnEyeOperation() {
        Operation operation = factory.create(OperationType.EYE, DESCRIPTION, surgeon, date, room, STATUS, patient);
        assertTrue(operation instanceof EyeOperation);
    }

    @Test
    public void whenTypeIsHeartShouldReturnHeartOperation() {
        Operation operation = factory.create(OperationType.HEART, DESCRIPTION, surgeon, date, room, STATUS, patient);
        assertTrue(operation instanceof HeartOperation);
    }

    @Test
    public void whenTypeIsMarrowShouldReturnMarrowOperation() {
        Operation operation = factory.create(OperationType.MARROW, DESCRIPTION, surgeon, date, room, STATUS, patient);
        assertTrue(operation instanceof MarrowOperation);
    }

    @Test
    public void whenTypeIsOncolyShouldReturnOncologicalOperation() {
        Operation operation = factory.create(OperationType.ONCOLOGY, DESCRIPTION, surgeon, date, room, STATUS, patient);
        assertTrue(operation instanceof OncologicalOperation);
    }

    @Test
    public void whenTypeIsOtherShouldReturnRegularOperation() {
        Operation operation = factory.create(OperationType.OTHER, DESCRIPTION, surgeon, date, room, STATUS, patient);
        assertTrue(operation instanceof RegularOperation);
    }

}
