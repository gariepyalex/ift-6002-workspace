package ca.ulaval.ift6002.m2.factory.hibernate;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.operation.OperationData;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.operation.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.OperationHibernateData;

public class OperationHibernateFactoryTest {

    private static final OperationType TYPE = OperationType.EYE;
    private static final OperationStatus STATUS = OperationStatus.PLANNED;
    private static final String DESCRIPTION = "description";
    private static final Surgeon SURGEON = new Surgeon(12345);
    private static final Room ROOM = new Room("room");
    private static final Patient PATIENT = new Patient(12345, "ABCD 1234 5678");
    private static final Date DATE = new Date();

    private OperationFactory operationHibernateFactory;

    @Before
    public void setUp() {
        operationHibernateFactory = new OperationHibernateFactory();
    }

    @Test
    public void whenCreatingOperationDataShouldReturnAnHibernateInstance() {
        OperationData operationData = operationHibernateFactory.createOperationData(TYPE, DESCRIPTION, SURGEON, DATE,
                ROOM, STATUS, PATIENT);

        assertEquals(OperationHibernateData.class, operationData.getClass());
    }
}
