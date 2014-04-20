package ca.ulaval.ift6002.m2.domain.operation;

import java.util.Date;

import ca.ulaval.ift6002.m2.domain.operation.regular.OncologicalOperation;
import ca.ulaval.ift6002.m2.domain.operation.regular.OtherOperation;
import ca.ulaval.ift6002.m2.domain.operation.restricted.EyeOperation;
import ca.ulaval.ift6002.m2.domain.operation.restricted.HeartOperation;
import ca.ulaval.ift6002.m2.domain.operation.restricted.MarrowOperation;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;

public abstract class OperationFactory {

    public Operation create(OperationType type, OperationData operationData) {
        if (type == OperationType.EYE) {
            return new EyeOperation(operationData);
        } else if (type == OperationType.HEART) {
            return new HeartOperation(operationData);
        } else if (type == OperationType.MARROW) {
            return new MarrowOperation(operationData);
        } else if (type == OperationType.ONCOLOGY) {
            return new OncologicalOperation(operationData);
        } else if (type == OperationType.OTHER) {
            return new OtherOperation(operationData);
        }

        throw new IllegalArgumentException("Operation type is not defined");
    }

    public Operation create(OperationType type, String description, Surgeon surgeon, Date date, Room room,
            OperationStatus status, Patient patient) {
        OperationData operationData = createOperationData(type, description, surgeon, date, room, status, patient);
        return create(type, operationData);
    }

    public abstract OperationData createOperationData(OperationType type, String description, Surgeon surgeon,
            Date date, Room room, OperationStatus status, Patient patient);
}
