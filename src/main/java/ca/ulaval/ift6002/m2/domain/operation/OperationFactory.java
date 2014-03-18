package ca.ulaval.ift6002.m2.domain.operation;

import java.util.Date;

import ca.ulaval.ift6002.m2.domain.operation.regular.OncologicalOperation;
import ca.ulaval.ift6002.m2.domain.operation.restricted.EyeOperation;
import ca.ulaval.ift6002.m2.domain.operation.restricted.HeartOperation;
import ca.ulaval.ift6002.m2.domain.operation.restricted.MarrowOperation;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.operation.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.patient.Patient;

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
        }

        throw new IllegalArgumentException("Operation type is not defined");
    }

    public abstract Operation create(OperationType type, String description, Surgeon surgeon, Date date, Room room,
            OperationStatus status, Patient patient);
}
