package ca.ulaval.ift6002.m2.domain.operation;

import java.security.InvalidParameterException;
import java.util.Date;

import ca.ulaval.ift6002.m2.domain.operation.dangerous.EyeOperation;
import ca.ulaval.ift6002.m2.domain.operation.dangerous.HeartOperation;
import ca.ulaval.ift6002.m2.domain.operation.dangerous.MarrowOperation;
import ca.ulaval.ift6002.m2.domain.operation.regular.OncologicalOperation;
import ca.ulaval.ift6002.m2.domain.operation.regular.RegularOperation;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;

public class OperationFactory {

    public Operation create(OperationType type, Description description, Surgeon surgeon, Date date, Room room,
            OperationStatus status, Patient patient) {
        Operation operation;

        if (type == OperationType.EYE) {
            operation = new EyeOperation(description, surgeon, date, room, status, patient);
        } else if (type == OperationType.HEART) {
            operation = new HeartOperation(description, surgeon, date, room, status, patient);
        } else if (type == OperationType.MARROW) {
            operation = new MarrowOperation(description, surgeon, date, room, status, patient);
        } else if (type == OperationType.ONCOLOGY) {
            operation = new OncologicalOperation(description, surgeon, date, room, status, patient);
        } else if (type == OperationType.OTHER) {
            operation = new RegularOperation(description, surgeon, date, room, status, patient);
        } else {
            throw new InvalidParameterException("operation type is not defined");
        }

        return operation;
    }
}
