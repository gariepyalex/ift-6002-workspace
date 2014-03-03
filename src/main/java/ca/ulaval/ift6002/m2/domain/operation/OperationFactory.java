package ca.ulaval.ift6002.m2.domain.operation;

import java.util.Date;

import ca.ulaval.ift6002.m2.domain.operation.dangerous.EyeOperation;
import ca.ulaval.ift6002.m2.domain.operation.dangerous.HeartOperation;
import ca.ulaval.ift6002.m2.domain.operation.dangerous.MarrowOperation;
import ca.ulaval.ift6002.m2.domain.operation.regular.OncologicalOperation;
import ca.ulaval.ift6002.m2.domain.operation.regular.RegularOperation;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;

public class OperationFactory {

    public Operation create(OperationType type, String description, Surgeon surgeon, Date date, Room room,
            OperationStatus status, Patient patient) {
        if (type == OperationType.EYE) {
            return new EyeOperation(description, surgeon, date, room, status, patient);
        } else if (type == OperationType.HEART) {
            return new HeartOperation(description, surgeon, date, room, status, patient);
        } else if (type == OperationType.MARROW) {
            return new MarrowOperation(description, surgeon, date, room, status, patient);
        } else if (type == OperationType.ONCOLOGY) {
            return new OncologicalOperation(description, surgeon, date, room, status, patient);
        } else if (type == OperationType.OTHER) {
            return new RegularOperation(description, surgeon, date, room, status, patient);
        }

        throw new IllegalArgumentException("Operation type is not defined");
    }
}
