package ca.ulaval.ift6002.m2.factory.hibernate;

import java.util.Date;

import ca.ulaval.ift6002.m2.domain.operation.OperationData;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.OperationHibernateData;

public class OperationHibernateFactory extends OperationFactory {

    @Override
    public OperationData createOperationData(OperationType type, String description, Surgeon surgeon, Date date,
            Room room, OperationStatus status, Patient patient) {
        return new OperationHibernateData(description, surgeon, date, room, status, patient, type);
    }

}
